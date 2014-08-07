package com.disney.customcraft.item.part;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PartHilt extends Item {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[ItemCustomSword.HILT_NAMES.length];
	
	public PartHilt() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".hilt");
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "hilt" + ItemCustomSword.HILT_NAMES[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < ItemCustomSword.HILT_NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < ItemCustomSword.HILT_NAMES.length; i++) { 
			icons[i] = register.registerIcon("customcraft:" + "hilt" + ItemCustomSword.HILT_NAMES[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

}
