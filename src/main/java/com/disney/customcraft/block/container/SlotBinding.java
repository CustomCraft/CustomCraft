package com.disney.customcraft.block.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotBinding extends Slot {

	public SlotBinding(IInventory inventory, int slot, int x, int y) {
		super(inventory, slot, x, y);
	}

}
