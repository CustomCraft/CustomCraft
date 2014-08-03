package com.disney.customcraft.modules.core.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.modules.core.ModuleCore;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoreIngot extends Item {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[ModInfo.CORE_INGOT_NAMES.length];
	
	public ItemCoreIngot() {
		super();
				
		setUnlocalizedName("core");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".ingot");
		
		for(int i = 0; i < ModInfo.CORE_INGOT_NAMES.length; i++) {
			OreDictionary.registerOre(ModInfo.CORE_INGOT_NAMES[i], new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + ModInfo.CORE_INGOT_NAMES[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < ModInfo.CORE_INGOT_NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < ModInfo.CORE_INGOT_NAMES.length; i++) { 
			icons[i] = register.registerIcon("customcraft:core/" + ModInfo.CORE_INGOT_NAMES[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
}
