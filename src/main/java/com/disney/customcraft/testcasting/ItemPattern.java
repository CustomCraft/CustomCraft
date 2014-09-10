package com.disney.customcraft.testcasting;

import com.disney.customcraft.handlers.ItemHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemPattern extends Item {
	
	public ItemPattern(String itemName) {
		super();
		
		setFull3D();
		setUnlocalizedName("customcraft." + itemName);
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

}
