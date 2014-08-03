package com.disney.customcraft.modules.core.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemCorePickaxe extends ItemPickaxe {
	
	private String itemName;
	
	public ItemCorePickaxe(ToolMaterial material, String itemName) {
		super(material);
		
		this.itemName = itemName;
		
		setUnlocalizedName("core");
		setCreativeTab(CreativeTabs.tabTools);
		
		register();
	}
	
	public void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + "." + itemName);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + itemName;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("customcraft:core/" + itemName);
	}

}
