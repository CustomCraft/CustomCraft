package com.disney.customcraft.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

import com.disney.customcraft.block.renderer.RendererCampfire;

public class ItemBlockCampfire extends ItemBlock {
	
	public ItemBlockCampfire(Block block) {
		super(block);
		
		register();
	}
	
	public final void register() {
		MinecraftForgeClient.registerItemRenderer(this, new RendererCampfire());
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return null;
	}

}
