package com.disney.customcraft.block.gui;

import com.disney.customcraft.block.container.ContainerBindingTable;
import com.disney.customcraft.block.tile.TileBindingTable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiBindingTable extends GuiAbstract {

	private TileBindingTable tileEntity;
	
	public GuiBindingTable(InventoryPlayer inventory, TileBindingTable tileEntity) {
		super(new ContainerBindingTable(inventory, tileEntity));

		this.tileEntity = tileEntity;
	}

	@Override
	public void drawBackground(int x, int y) {

	}

	@Override
	public ResourceLocation getResourceLocation() {
		return new ResourceLocation("customcraft", "textures/gui/tableBinding.png");
	}

}
