package com.disney.customcraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import com.disney.customcraft.block.itemblock.ItemBlockMultiOre;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultiOre extends Block {
	
	public static final String[] NAMES = { "oreCopper", "oreTin", "oreBronze", "oreSteel" };
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[NAMES.length];

	public BlockMultiOre() {
		super(Material.rock);
        
        setBlockName("customcraft");
		setCreativeTab(CreativeTabs.tabBlock);
		
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypePiston);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockMultiOre.class, getUnlocalizedName() + ".ore");
		
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
    public int quantityDroppedWithBonus(int fortune, Random random) {
		if(fortune > 0 && Item.getItemFromBlock(this) != getItemDropped(0, random, fortune)) {
			int add = random.nextInt(fortune + 2) - 1;

            if(add < 0) {
            	add = 0;
            }

            return this.quantityDropped(random) * (add + 1);
        } else {
            return this.quantityDropped(random);
        }
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
