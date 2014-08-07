package com.disney.customcraft.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import com.disney.customcraft.block.itemblock.ItemBlockMultiStorage;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultiStorage extends Block {
	
	public static final String[] NAMES = { "blockCopper", "blockTin", "blockBronze", "blockSteel" };
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[NAMES.length];

	public BlockMultiStorage() {
		super(Material.iron);
        
		setBlockName("customcraft");
		setCreativeTab(CreativeTabs.tabBlock);
		
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockMultiStorage.class, getUnlocalizedName() + ".storage");
		
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
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int i = 0; i < NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for(int i = 0; i < NAMES.length; i++) { 
    		icons[i] = register.registerIcon("customcraft:" + NAMES[i]);
    	}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		return icons[meta];
    }
	
}
