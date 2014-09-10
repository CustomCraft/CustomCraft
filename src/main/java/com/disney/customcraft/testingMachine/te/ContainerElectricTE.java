package com.disney.customcraft.testingMachine.te;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerElectricTE extends Container {

	protected final TileElectricTE tileEntity;
	
	protected final int BAG = 27;
	protected final int INVENTORY = 35;
	private final int INPUT = 36;
	private final int OUTPUT = 37;
	
	public ContainerElectricTE(InventoryPlayer inventory, TileElectricTE te) {
		this.tileEntity = te;
		
		//players inventory
	    for (int y = 0; y < 3; ++y) {
	    	for (int x = 0; x < 9; ++x) {
	    		addSlotToContainer(new Slot(inventory, x + (y * 9) + 9, 8 + x * 18, 84 + y * 18));
	    	}
	    }
	    
	    //bottom bar
	    for (int x = 0; x < 9; ++x) {
	    	addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 142));
	    }
	    
  		//input
  	    addSlotToContainer(new Slot(tileEntity, TileElectricTE.INPUT, 56, 26) {
  	    	@Override
  		    public boolean isItemValid(ItemStack itemStack) {
  		        return tileEntity.isItemValidForSlot(TileElectricTE.INPUT, itemStack);
  		    }
  		});
  	    
  	    //output
  	    addSlotToContainer(new Slot(tileEntity, TileElectricTE.OUTPUT, 116, 35) {
  	    	@Override
  		    public boolean isItemValid(ItemStack itemStack) {
  		        return tileEntity.isItemValidForSlot(TileElectricTE.OUTPUT, itemStack);
  		    }
  		});
	}
	
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotID) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotID);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //if its an active slot then merge with players inventory
            if(slotID > INVENTORY) {
                if(!this.mergeItemStack(itemstack1, 0, INVENTORY + 1, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            // its in the players inventory...
            else if(slotID <= INVENTORY) {
            	//if its a valid input then merge with the input slot
                if(tileEntity.isItemValidForSlot(0, itemstack1)) {
                    if(!this.mergeItemStack(itemstack1, INPUT, INPUT + 1, false)) {
                        return null;
                    }
                }
                //if its a valid energy container put it even in charging/uncharging
                /*TODO else if(itemstack1.getItem() != null && itemstack1.getItem() instanceof IEnergyContainerItem) {
                	//if the energy container has some energy, discharge it
                	if(((IEnergyContainerItem)itemstack1.getItem()).getEnergyStored(itemstack1) > 0) {
                		if(!this.mergeItemStack(itemstack1, POWER_IN, POWER_IN + 1, false)) {
                            return null;
                        }
                		
                	}
                	//otherwise charge it
                	else {
                		if(!this.mergeItemStack(itemstack1, POWER_OUT, POWER_OUT + 1, false)) {
                            return null;
                        }
                	}
                }*/
                //if its in the bag section merge it with the bar section
                else if(slotID < BAG) {
                    if(!this.mergeItemStack(itemstack1, BAG, INVENTORY + 1, false)) {
                        return null;
                    }
                }
                //if its in the bar section merge it with the bag section
                else if(slotID >= BAG && slotID < INVENTORY + 1 && !this.mergeItemStack(itemstack1, 0, BAG, false)) {
                    return null;
                }
            }
            //otherwise try merge it with the players inv
            else if(!this.mergeItemStack(itemstack1, 0, INVENTORY + 1, false)) {
                return null;
            }

            //if the stacksize is now 0 then the item was fully merged, replace it with a null stack
            if(itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            }
            else{
                slot.onSlotChanged();
            }

            //update the new slot amount
            if(itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            //if all else failed try pick up the stack normally
            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileEntity.isUseableByPlayer(entityPlayer);
	}
	
}
