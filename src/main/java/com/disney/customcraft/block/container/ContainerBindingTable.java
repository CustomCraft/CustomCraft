package com.disney.customcraft.block.container;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.IShaftPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryParts;
import com.disney.customcraft.block.tile.TileBindingTable;
import com.disney.customcraft.item.part.ItemHead;
import com.disney.customcraft.item.part.ItemShaft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ContainerBindingTable extends ContainerAbstract {
	
	private final int INPUTA = 36;
	private final int INPUTB = 37;
	private final int OUTPUT = 38;
	
	private TileBindingTable tileEntity;
	
	private IInventory craftResult = new InventoryCraftResult();

	public ContainerBindingTable(InventoryPlayer inventory, TileBindingTable tileEntity) {
		super(inventory);
		
		this.tileEntity = tileEntity;
		tileEntity.container = this;
		addSlots();
		
		onCraftMatrixChanged(tileEntity);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	public void addSlots() {
		//inputA
	    addSlotToContainer(new Slot(tileEntity, tileEntity.INPUTA, 48, 17) {
	    	@Override
		    public boolean isItemValid(ItemStack itemStack) {
		        return tileEntity.isItemValidForSlot(tileEntity.INPUTA, itemStack);
		    }
		});
		
		//inputB
	    addSlotToContainer(new Slot(tileEntity, tileEntity.INPUTB, 48, 53) {
	    	@Override
		    public boolean isItemValid(ItemStack itemStack) {
		        return tileEntity.isItemValidForSlot(tileEntity.INPUTB, itemStack);
		    }
		});
	    
	    //output
	    addSlotToContainer(new SlotBinding(tileEntity, tileEntity.OUTPUT, 124, 35) {
	    	@Override
		    public boolean isItemValid(ItemStack itemStack) {
		        return tileEntity.isItemValidForSlot(tileEntity.OUTPUT, itemStack);
		    }
		});
	}
	
	public void onCraftMatrixChanged(IInventory inventory) {
		int blade = -1;
		int hilt = -1;
		
		ItemStack stackA = tileEntity.getStackInSlot(tileEntity.INPUTA);
		ItemStack stackB = tileEntity.getStackInSlot(tileEntity.INPUTB);
		
		if(stackA != null && stackB != null) {
			
			
			NBTTagCompound tags = new NBTTagCompound();
			if(stackA.getItem() instanceof ItemHead && stackB.getItem() instanceof ItemShaft) {
				IHeadPart headPart = ((ItemHead)stackA.getItem()).headPart;
				IShaftPart shaftPart = ((ItemShaft)stackB.getItem()).shaftPart;
				
				for(int i = 0; i < RegistryParts.TOOLS.size(); i++) {
					if(RegistryParts.TOOLS.get(i).isValidInputs(headPart, shaftPart)) {
						tags.setInteger(headPart.getItemName(), stackA.getItemDamage());
						tags.setInteger(shaftPart.getItemName(), stackB.getItemDamage());
						
						tags.setInteger("dura", 0);
						tags.setInteger("maxdura", RegistryParts.MATERIALS_HEAD.get(stackA.getItemDamage()).getDura() + RegistryParts.MATERIALS_SHAFT.get(stackB.getItemDamage()).getDura());
						
						ItemStack output = new ItemStack(CustomItems.customTool);
						output.setItemDamage(i);
						output.setTagCompound(tags);
						tileEntity.setInventorySlotContents(tileEntity.OUTPUT, output);
					}
				}
			}
		} else {
			tileEntity.setInventorySlotContents(tileEntity.OUTPUT, null);
		}
    }

}
