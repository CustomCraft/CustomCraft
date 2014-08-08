package com.disney.customcraft.block.tile;

import com.disney.customcraft.CustomItems;

import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class TileBindingTable extends TileInventory {
	
	public Container container;
	
	public static final int INPUTA = 0;
	public static final int INPUTB = 1;
	public static final int OUTPUT = 2;
	
	public TileBindingTable() {
		super();
		
		items = new ItemStack[3];
	}
	
	@Override
	public String getInventoryName() {
		return "BindingTable";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch(side) {
			case 0: //bottom
				return new int[] {INPUTB, OUTPUT};
			case 1: //top
				return new int[] {INPUTA, OUTPUT};
			default: //sides
				return new int[] {OUTPUT};
	}
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return slot == OUTPUT ? true : false;
	}
	
	@Override
	public void closeInventory() {
		container = null;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		switch(slot) {
			case INPUTA:
				if(itemStack.getItem() == CustomItems.partBlade) {
					return true;
				}
				return false;
			case INPUTB:
				if(itemStack.getItem() == CustomItems.partHilt) {
					return true;
				}
				return false;
			default:
				return false;
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.items[slot] != null) {
			ItemStack itemstack;

			if (this.items[slot].stackSize <= amount) {
				itemstack = this.items[slot];
				this.items[slot] = null;
				if(container != null && slot != OUTPUT) container.onCraftMatrixChanged(this);
				return itemstack;
			} else {
				itemstack = this.items[slot].splitStack(amount);

				if (this.items[slot].stackSize == 0) {
					this.items[slot] = null;
				}
				if(container != null && slot != OUTPUT) container.onCraftMatrixChanged(this);
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.items[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
		if(container != null && slot != OUTPUT) container.onCraftMatrixChanged(this);
	}

}
