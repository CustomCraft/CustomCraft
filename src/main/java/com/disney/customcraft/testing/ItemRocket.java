package com.disney.customcraft.testing;

import com.disney.customcraft.CustomCraft;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemRocket extends Item {

	public ItemRocket(String assetName) {
		super();
		
		setCreativeTab(CreativeTabs.tabBlock);
		setUnlocalizedName("customcraft.rocket");
		setTextureName("arrow");
		
		register();
	}
	
	public void register() {
		GameRegistry.registerItem(this, getUnlocalizedName());
		
		EntityRegistry.registerModEntity(EntityTest.class, "rocket", 50, CustomCraft.instance, 64, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new RendererTest());
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		Block block = world.getBlock(x, y, z);
        EntityTest entity = new EntityTest(world, x, y + 1, z);

        if(itemStack.stackSize == 0) {
            return false;
        }
        else if (y >= 250) {
            return false;
        } 
        else if(!world.isRemote) {
        	world.spawnEntityInWorld(entity);
        	
        	return true;
        }
        else {
            return false;
        }
    }
	
}
