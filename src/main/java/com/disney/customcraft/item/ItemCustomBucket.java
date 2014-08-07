package com.disney.customcraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.handlers.ItemHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCustomBucket extends ItemBucket {

	public ItemCustomBucket(Fluid fluid, Block block) {
		super(block);
		setUnlocalizedName("customcraft.bucket" + fluid.getName());
		setCreativeTab(CreativeTabs.tabTools);
		
		register();
	}
	
	public void register() {
		GameRegistry.registerItem(this, getUnlocalizedName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemHelper.getIconLoc(getUnlocalizedName()));
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float vecX, float vecY, float vecZ) {
		if(getUnlocalizedName().contains("oil") && player.isSneaking()) {
	        if(world.getBlock(x, y, z) != Blocks.snow_layer) {
	            if(side == 0) {
	                --y;
	            }
	            if(side == 1) {
	                ++y;
	            }
	            if(side == 2) {
	                --z;
	            }
	            if(side == 3) {
	                ++z;
	            }
	            if(side == 4) {
	                --x;
	            }
	            if(side == 5) {
	                ++x;
	            }
	            if(!world.isAirBlock(x, y, z)) {
	                return false;
	            }
	        }
	
	        if(!player.canPlayerEdit(x, y, z, side, item)) {
	            return false;
	        } else {
	            if(CustomItems.oilTrail.canPlaceBlockAt(world, x, y, z)) {
	            	world.setBlock(x, y, z, CustomItems.oilTrail);
	            }
	            return true;
	        }
		}
		return false;
    }
}
