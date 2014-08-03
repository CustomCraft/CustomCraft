package com.disney.customcraft.modules.core.block;

import java.util.List;
import java.util.Random;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.modules.core.block.itemblock.ItemBlockCoreOre;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCoreOre extends Block {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[ModInfo.CORE_ORE_NAMES.length];

	public BlockCoreOre() {
		super(Material.rock);
        
		setProperties();
		
		register();
	}
	
	private void setProperties() {
		setBlockName("core");
		setCreativeTab(CreativeTabs.tabBlock);
		
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypePiston);
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockCoreOre.class, getUnlocalizedName() + ".ore");
		
		for(int i = 0; i < ModInfo.CORE_ORE_NAMES.length; i++) {
			OreDictionary.registerOre(ModInfo.CORE_ORE_NAMES[i], new ItemStack(this, 1, i));
		}
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
		for (int i = 0; i < ModInfo.CORE_ORE_NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for(int i = 0; i < ModInfo.CORE_ORE_NAMES.length; i++) { 
    		icons[i] = register.registerIcon("customcraft:core/" + ModInfo.CORE_ORE_NAMES[i]);
    	}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		if(meta > 0) {
			return icons[meta];
		}
		return icons[meta];
    }
	
}
