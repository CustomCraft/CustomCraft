package com.disney.customcraft.block.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiAbstract extends GuiContainer {
	
	public GuiAbstract(Container container) {
		super(container);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
	    int y = (height - ySize) / 2;
		
	    //draw GUI
	    Minecraft.getMinecraft().renderEngine.bindTexture(getResourceLocation());
	    drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	    
	    drawBackground(x, y);
	}
	
	public abstract void drawBackground(int x, int y);
	public abstract ResourceLocation getResourceLocation();

}
