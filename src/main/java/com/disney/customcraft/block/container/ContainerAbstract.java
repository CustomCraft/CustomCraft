package com.disney.customcraft.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public abstract class ContainerAbstract extends Container {
	
	protected final int BAG = 27;
	protected final int INVENTORY = 35;
	
	public ContainerAbstract(InventoryPlayer inventory) {
	    
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
	}
}
