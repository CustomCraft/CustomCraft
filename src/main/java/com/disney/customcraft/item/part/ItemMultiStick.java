package com.disney.customcraft.item.part;

import java.util.List;

import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;
import com.disney.customcraft.api.RegistryTools.ShaftMaterial;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMultiStick extends Item {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[RegistryTools.MATERIALS_SHAFT.size()];
	
	public ItemMultiStick() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + "." + "shaft");
		
		for(int i = 0; i < RegistryTools.MATERIALS_SHAFT.size(); i++) {
			if(RegistryTools.MATERIALS_SHAFT.get(i).create) {
				OreDictionary.registerOre(RegistryTools.MATERIALS_SHAFT.get(i).itemName, new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + RegistryTools.MATERIALS_SHAFT.get(itemstack.getItemDamage()).itemName;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < RegistryTools.MATERIALS_SHAFT.size(); i++) {
			if(RegistryTools.MATERIALS_SHAFT.get(i).create) {
				subItems.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < RegistryTools.MATERIALS_SHAFT.size(); i++) { 
			icons[i] = register.registerIcon("customcraft:" + RegistryTools.MATERIALS_SHAFT.get(i).itemName);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

}
