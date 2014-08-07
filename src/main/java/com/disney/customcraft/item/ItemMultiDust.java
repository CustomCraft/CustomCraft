package com.disney.customcraft.item;

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

public class ItemMultiDust extends Item {
	
	public static final String[] NAMES = { "dustCopper", "dustTin", "dustBronze", "dustSteel", "dustIron", "dustGold", "dustDiamond" };

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[NAMES.length];
	
	public ItemMultiDust() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".dust");
		
		for(int i = 0; i < NAMES.length; i++) {
			OreDictionary.registerOre(NAMES[i], new ItemStack(this, 1, i));
		}
	}
	
	public static int getMeta(String name) {
		for(int i = 0; i < NAMES.length; i++) {
			if(NAMES[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + NAMES[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < NAMES.length; i++) { 
			icons[i] = register.registerIcon("customcraft:" + NAMES[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
}
