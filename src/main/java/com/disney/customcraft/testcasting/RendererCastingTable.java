package com.disney.customcraft.testcasting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import codechicken.core.featurehack.LiquidTextures;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.tile.TileCampfire;

public class RendererCastingTable extends TileEntitySpecialRenderer implements IItemRenderer {
	
	ModelCastingTable model = new ModelCastingTable();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		if(tile instanceof TileCastingTable) {
			GL11.glPushMatrix();
		    GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		    //GL11.glScalef(-0.9F, -1.0F, 0.9F);
		    //GL11.glScalef(0.9F, -1.0F, 0.9F);
		    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		    
		    bindTexture(new ResourceLocation("customcraft", "render/castingBench.png"));
		    model.render(0.0625F);
		    
		    //GL11.glFlush();
		    GL11.glPopMatrix();
		    
		    GL11.glPushMatrix();
	        
		    float var10 = (float) (x - 0.5F);
	        float var11 = (float) (y - 0.5F);
	        float var12 = (float) (z - 0.5F);
	        GL11.glTranslatef(var10, var11, var12);
	        
	        
	        TileCastingTable castingTable = (TileCastingTable) tile;
	        if(castingTable.getOutput() != null) {
	        	renderOutput(castingTable, castingTable.getOutput());
	        }
	        else if(castingTable.getFluid() != null) {
	        	renderLiquid(castingTable, castingTable.getFluid());
	        }
	        
	        if(castingTable.getPattern() != null) {
	        	renderPattern((TileCastingTable) tile, castingTable.getPattern());
	        }
	        
	        GL11.glPopMatrix();
		}
	}
	
	private void renderLiquid(TileCastingTable tile, Fluid fluid) {
		GL11.glPushMatrix();

        GL11.glTranslatef(0.55F, 1.38F + tile.getFluidRender(), 0.55F);
        GL11.glScalef(0.875F, 1.0F, 0.875F);
        GL11.glRotatef(90F, 1, 0F, 0F);

        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(texturemanager.getResourceLocation(fluid.getSpriteNumber()));
        
        IIcon icon = fluid.getIcon();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMinU();
        float f4 = icon.getMaxV();
        
        ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f3, f4, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);

        GL11.glPopMatrix();
	}
	
	private void renderOutput(TileCastingTable tile, Item output) {
		GL11.glPushMatrix();
        
		GL11.glTranslatef(0.55F, 1.44F, 0.55F);
		GL11.glScalef(0.875F, 1.0F, 0.875F);
        GL11.glRotatef(90F, 1, 0F, 0F);

        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(texturemanager.getResourceLocation(output.getSpriteNumber()));
        
        IIcon icon = output.getIconFromDamage(0);
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMinU();
        float f4 = icon.getMaxV();
        ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f3, f4, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);

        GL11.glPopMatrix();
	}
	
	private void renderPattern(TileCastingTable tile, Item pattern) {
		GL11.glPushMatrix();
        
		GL11.glTranslatef(0.55F, 1.44F, 0.55F);
		GL11.glScalef(0.875F, 1.0F, 0.875F);
        GL11.glRotatef(90F, 1, 0F, 0F);

        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        texturemanager.bindTexture(texturemanager.getResourceLocation(pattern.getSpriteNumber()));
        
        IIcon icon = pattern.getIconFromDamage(0);
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMinU();
        float f4 = icon.getMaxV();
        ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f3, f4, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);

        GL11.glPopMatrix();
	}
	
	void renderItem (TileCastingTable tile, Item item)
    {
        //FancyItemEntity entityitem = new FancyItemEntity(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, stack);
        //entityitem.getEntityItem().stackSize = 1;
        //entityitem.hoverStart = 0.0F;
        GL11.glPushMatrix();
        //GL11.glTranslatef(1F, 1.48F, 0.55F);
        //GL11.glRotatef(90F, 1, 0F, 0F);
        //GL11.glScalef(2F, 2F, 2F);
        
        
        //GL11.glRotatef(90F, 1, 0F, 0F);
        //max GL11.glTranslatef(0.55F, 1.43F, 0.55F);
        //min GL11.glTranslatef(0.55F, 1.38F, 0.55F);
        
        GL11.glTranslatef(0.55F, 1.38F + tile.getFluidRender(), 0.55F);
        GL11.glScalef(0.9F, 1.0F, 0.9F);
        GL11.glRotatef(90F, 1, 0F, 0F);
        //GL11.glRotatef(90F, -1, 0F, 0F);

        RenderItem.renderInFrame = true;
        
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        //texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
        //FluidRegistry.LAVA.
        texturemanager.bindTexture(texturemanager.getResourceLocation(FluidRegistry.LAVA.getSpriteNumber()));
        IIcon iicon = FluidRegistry.LAVA.getIcon();
        float f = iicon.getMinU();
        float f1 = iicon.getMaxU();
        float f2 = iicon.getMinV();
        float f3 = iicon.getMaxV();
        ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
        //RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        RenderItem.renderInFrame = false;

        GL11.glPopMatrix();
        
        //FancyItemEntity entityitem2 = new FancyItemEntity(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, stack);
        //entityitem2.getEntityItem().stackSize = 1;
        //entityitem2.hoverStart = 0.0F;
        GL11.glPushMatrix();
        //GL11.glTranslatef(1F, 1.48F, 0.55F);
        //GL11.glRotatef(90F, 1, 0F, 0F);
        //GL11.glScalef(2F, 2F, 2F);
        
        
        //GL11.glRotatef(90F, 1, 0F, 0F);
        GL11.glTranslatef(0.5F, 1.44F, 0.5F);
        GL11.glRotatef(90F, 1, 0F, 0F);
        //GL11.glRotatef(90F, -1, 0F, 0F);

        RenderItem.renderInFrame = true;
        
        TextureManager texturemanager2 = Minecraft.getMinecraft().getTextureManager();
        
        texturemanager2.bindTexture(texturemanager2.getResourceLocation(item.getSpriteNumber()));
        IIcon iicon2 = item.getIconFromDamage(0);
        float f29 = iicon2.getMinU();
        float f12 = iicon2.getMaxU();
        float f22 = iicon2.getMinV();
        float f32 = iicon2.getMaxV();
        ItemRenderer.renderItemIn2D(Tessellator.instance, f12, f22, f29, f32, iicon2.getIconWidth(), iicon2.getIconHeight(), 0.0625F);
        //RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        RenderItem.renderInFrame = false;

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
		
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("customcraft", "render/castingBench.png"));
		model.render(0.0625F);
	}
}
