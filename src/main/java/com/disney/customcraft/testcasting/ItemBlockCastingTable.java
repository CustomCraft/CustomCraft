package com.disney.customcraft.testcasting;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

import com.disney.customcraft.block.renderer.RendererCampfire;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ItemBlockCastingTable extends ItemBlock {
	
	public ItemBlockCastingTable(Block block) {
		super(block);
		
		register();
	}
	
	public final void register() {
		RenderingRegistry.registerEntityRenderingHandler(FancyItemEntity.class, new RenderItem());
		
		MinecraftForgeClient.registerItemRenderer(this, new RendererCastingTable());
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return null;
	}

}
