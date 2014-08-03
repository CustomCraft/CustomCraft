package com.disney.customcraft.modules.core.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class ItemCoreArmor extends ItemArmor {
	
	private String itemName;
	
	public ItemCoreArmor(ArmorMaterial material, int armorSlot, String itemName) {
		super(material, 2, armorSlot);
		
		setUnlocalizedName("core");
		setCreativeTab(CreativeTabs.tabCombat);
		
		this.itemName = itemName;
		
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
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,	String type) {
		return "customcraft:textures/armor/" + getArmorMaterial().name() + "_" + (slot == 2 ? 2 : 1) + ".png";
	}

}
