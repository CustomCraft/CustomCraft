package com.disney.customcraft.item.part;

import java.util.List;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.RegistryParts;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemHead extends Item {
	
	public IHeadPart headPart;
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[RegistryParts.MATERIALS_HEAD.size()];
	
	public ItemHead(IHeadPart headPart) {
		super();
		
		this.headPart = headPart;
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + "." + headPart.getItemName());
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + headPart.getItemName() + RegistryParts.MATERIALS_HEAD.get(itemstack.getItemDamage());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < RegistryParts.MATERIALS_HEAD.size(); i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < RegistryParts.MATERIALS_HEAD.size(); i++) { 
			icons[i] = register.registerIcon("customcraft:tool/" + headPart.getItemName() + RegistryParts.MATERIALS_HEAD.get(i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

}
