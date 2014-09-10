package com.disney.customcraft.testingMachine.te;

import cofh.api.tileentity.IReconfigurableSides;
import cofh.api.tileentity.IRedstoneControl;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileElectricTE extends TileEntity implements ISidedInventory, IRedstoneControl {
	
	protected ItemStack[] items = new ItemStack[2];
	
	protected boolean isPowered = false;
	protected IRedstoneControl.ControlMode rsMode = IRedstoneControl.ControlMode.DISABLED;

	public static final int INPUT = 0;
	public static final int OUTPUT = 1;
	
	public TileElectricTE() {
		super();
		
		//TODO energyStorage = new EnergyStorage(400000);
	}
	
	@Override
	public void updateEntity() {
		if(isPowered) {
			runProcess();
		}
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public boolean canProcess() {
        if(items[INPUT] == null) {
            return false;
        } 
        else {
        	//check there is a valid recipe
        	if(items[INPUT].getItem() == Items.apple) {
        		ItemStack output = new ItemStack(Items.golden_apple);
        		
            	//check there is room for the output
            	if(items[OUTPUT] == null) return true;
            	if(!items[OUTPUT].isItemEqual(output)) return false;
            	int result = items[OUTPUT].stackSize + output.stackSize;

            	return (result <= getInventoryStackLimit() && result <= output.getMaxStackSize());
        	}
        	
        	return false;
        }
    }
	
	public void runProcess() {
        if(canProcess()) {
        	//get the valid recipe and output
        	ItemStack output = new ItemStack(Items.golden_apple);

            //reduce the inputs stack size
            items[INPUT].stackSize -= 1;

            //if the stack size is 0 set the inputs to null
            if(items[INPUT].stackSize <= 0) items[INPUT] = null;

        	//if the output slot is empty place a copy of the output in there
            if(items[OUTPUT] == null) {
                items[OUTPUT] = output.copy();
            }
            //otherwise if the output slot contains the same item, increase the stack size
            else if(items[OUTPUT].isItemEqual(output)) {
                items[OUTPUT].stackSize += output.stackSize;
            }
        }
    }
	
	/******************************************************************************
	 * ISidedInventory
	 *****************************************************************************/

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.items[slot] != null) {
			ItemStack itemstack;

			if (this.items[slot].stackSize <= amount) {
				itemstack = this.items[slot];
				this.items[slot] = null;
				return itemstack;
			} else {
				itemstack = this.items[slot].splitStack(amount);

				if (this.items[slot].stackSize == 0) {
					this.items[slot] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.items[slot] != null) {
			ItemStack itemstack = this.items[slot];
			this.items[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.items[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "electricTE";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		switch(slot) {
			case INPUT:
				return true;
			default:
				return false;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch(side) {
			default: //any
				return new int[] {INPUT, OUTPUT};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
		return isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return slot == OUTPUT ? true : false;
	}
	
	/******************************************************************************
	 * IRedstoneControl
	 *****************************************************************************/
	
	@Override
	public boolean isPowered() {
		return isPowered;
	}

	@Override
	public void setPowered(boolean paramBoolean) {
	    this.isPowered = paramBoolean;
	}

	@Override
	public void setControl(ControlMode paramControlMode) {
		this.rsMode = paramControlMode;
		
		if(rsMode == ControlMode.DISABLED) {
			setPowered(true);
		} else {
			setPowered(false);
		}
	}

	@Override
	public ControlMode getControl() {
		return this.rsMode;
	}

	
	
}
