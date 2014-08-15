package com.disney.customcraft.testing;

import com.disney.customcraft.CustomCraft;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemRocket extends Item {

	public ItemRocket(String assetName) {
		super();
		
		setUnlocalizedName("customcraft.rocket");
		setTextureName("arrow");
		
		register();
	}
	
	public void register() {
		GameRegistry.registerItem(this, getUnlocalizedName());
		
		EntityRegistry.registerModEntity(EntityTest.class, "rocket", 50, CustomCraft.instance, 64, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new RendererTest());
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		EntityTest entity = new EntityTest(world, player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
		
		if (!world.isRemote)
		{
			world.spawnEntityInWorld(entity);
		}
		
		return stack;
	}
	
}
