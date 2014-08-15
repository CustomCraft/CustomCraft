package com.disney.customcraft.item.part;

import java.util.List;

import com.disney.customcraft.api.IPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMultiPart extends Item implements IPart {
	
	private ITool tool;
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[RegistryTools.MATERIALS_HEAD.size()];
	
	public ItemMultiPart(ITool tool) {
		super();
		
		this.tool = tool;
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + "." + tool.getPartName() + "Part");
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName()+ "." + tool.getPartName() + RegistryTools.MATERIALS_HEAD.get(itemstack.getItemDamage()).name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < RegistryTools.MATERIALS_HEAD.size(); i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < RegistryTools.MATERIALS_HEAD.size(); i++) { 
			icons[i] = register.registerIcon("customcraft:" + tool.getPartName() + "/" + "part" + RegistryTools.MATERIALS_HEAD.get(i).name);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public ITool getTool() {
		return tool;
	}

}
