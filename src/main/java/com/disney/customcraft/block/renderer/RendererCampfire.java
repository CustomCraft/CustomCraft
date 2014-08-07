package com.disney.customcraft.block.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.disney.customcraft.block.model.ModelCampfire;
import com.disney.customcraft.block.tile.TileCampfire;

public class RendererCampfire extends TileEntitySpecialRenderer implements IItemRenderer {

	private ModelCampfire model = new ModelCampfire();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		renderAModelAt((TileCampfire) tile, x, y, z, tick);
		
		if(tile instanceof TileCampfire) {
			if(((TileCampfire)tile).isBurning()) {
				bindTexture(TextureMap.locationBlocksTexture);
		
				Tessellator tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
				//set icons
		        IIcon iicon = Blocks.fire.getFireIcon(0);
		        IIcon iicon1 = Blocks.fire.getFireIcon(1);
		        
		        //set flame height
		        float height = 1.0F;
		        
		        //set vertical texture
		        double uMin = (double)iicon.getMinU();
		        double vMin = (double)iicon.getMinV();
		        double uMax = (double)iicon.getMaxU();
		        double vMax = (double)iicon.getMaxV();
		        
		        //vertical-back
		        double top = (double)x + 0.5D - 0.1D;
		        double bot = (double)x + 0.5D + 0.2D; //bottom edge
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.75), uMax, vMin);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.75), uMax, vMax);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.25), uMin, vMax);
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.25), uMin, vMin);
		        
		        top = (double)x + 0.5D + 0.1D;
		        bot = (double)x + 0.5D - 0.2D; //bottom edge
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.25), uMax, vMin);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.25), uMax, vMax);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.75), uMin, vMax);
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.75), uMin, vMin);
		        
		        //vertical-front
		        top = (double)z + 0.5D + 0.1D;
		        bot = (double)z + 0.5D + 0.3D;
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)((float)y + height), top, uMin, vMin);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)(y + 0), bot, uMin, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)(y + 0), bot, uMax, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)((float)y + height), top, uMax, vMin);
		        
		        top = (double)z + 0.5D - 0.1D;
		        bot = (double)z + 0.5D - 0.3D;
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)((float)y + height), top, uMin, vMin);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)(y + 0), bot, uMin, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)(y + 0), bot, uMax, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)((float)y + height), top, uMax, vMin);
		
		        //set horizontal texture
		        uMin = (double)iicon1.getMinU();
		        vMin = (double)iicon1.getMinV();
		        uMax = (double)iicon1.getMaxU();
		        vMax = (double)iicon1.getMaxV();
		
		        //horizontal-back
		        top = (double)z + 0.5D + 0.1D;
		        bot = (double)z + 0.5D - 0.2D;
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)((float)y + height), top, uMax, vMin);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)(y + 0), bot, uMax, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)(y + 0), bot, uMin, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)((float)y + height), top, uMin, vMin);
		
		        top = (double)z + 0.5D - 0.1D;
		        bot = (double)z + 0.5D + 0.2D;
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)((float)y + height), top, uMax, vMin);
		        tessellator.addVertexWithUV((double)(x + 0.25), (double)(y + 0), bot, uMax, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)(y + 0), bot, uMin, vMax);
		        tessellator.addVertexWithUV((double)(x + 0.75), (double)((float)y + height), top, uMin, vMin);
		
		        //horizontal-front
		        top = (double)x + 0.5D - 0.1D;
		        bot = (double)x + 0.5D - 0.3D; //bottom edge
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.25), uMin, vMin);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.25), uMin, vMax);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.75), uMax, vMax);
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.75), uMax, vMin);
		        
		        top = (double)x + 0.5D + 0.1D;
		        bot = (double)x + 0.5D + 0.3D; //bottom edge
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.75), uMin, vMin);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.75), uMin, vMax);
		        tessellator.addVertexWithUV(bot, (double)(y + 0), (double)(z + 0.25), uMax, vMax);
		        tessellator.addVertexWithUV(top, (double)((float)y + height), (double)(z + 0.25), uMax, vMin);
		        
		        tessellator.draw();
			}
		}
	}
	
	private void renderAModelAt(TileCampfire tileEntity, double x, double y, double z, float tick)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		
		bindTexture(new ResourceLocation("customcraft", "render/campfire.png"));
			    
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(0.0625F, tileEntity.getNumSticks());
		GL11.glPopMatrix();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
        	//GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
		
		GL11.glTranslatef(0.5F, -1.4F, 0.5F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("customcraft", "render/campfire.png"));
		model.render(0.0625F, 8);
	}
	
}
