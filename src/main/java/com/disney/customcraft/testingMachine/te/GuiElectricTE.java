package com.disney.customcraft.testingMachine.te;

import cofh.gui.GuiBase;
import cofh.gui.GuiBaseAdv;
import cofh.gui.element.TabBase;
import cofh.gui.element.TabConfiguration;
import cofh.gui.element.TabInfo;
import cofh.gui.element.TabRedstone;
import cofh.render.IconRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class GuiElectricTE extends GuiBaseAdv {

	public static final ResourceLocation TEXTURE = new ResourceLocation("customcraft:textures/gui/electricFurnace.png");
	
	TileElectricTE tileEntity;
	
	public GuiElectricTE(InventoryPlayer inventory, TileElectricTE tileEntity) {
		super(new ContainerElectricTE(inventory, tileEntity), TEXTURE);
		
		this.tileEntity = tileEntity;
		this.name = tileEntity.getInventoryName();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		addTab(new TabInfo(this, "Test String"));
		addTab(new TabRedstone(this, tileEntity));
	}
	
}