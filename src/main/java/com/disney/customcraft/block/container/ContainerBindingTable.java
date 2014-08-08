package com.disney.customcraft.block.container;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.tile.TileBindingTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
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
			if(stackA.getItem() == CustomItems.partBlade) {
				tags.setInteger("blade", 0);
			}
			if(stackB.getItem() == CustomItems.partHilt) {
				tags.setInteger("hilt", 0);
			}
			
			ItemStack output = new ItemStack(CustomItems.customSword);
			output.setTagCompound(tags);
			tileEntity.setInventorySlotContents(tileEntity.OUTPUT, output);
		} else {
			craftResult.setInventorySlotContents(tileEntity.OUTPUT, null);
		}
    }

}
