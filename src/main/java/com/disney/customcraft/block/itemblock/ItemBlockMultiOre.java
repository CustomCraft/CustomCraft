package com.disney.customcraft.block.itemblock;

import com.disney.customcraft.block.BlockMultiOre;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMultiOre extends ItemBlock {

	public ItemBlockMultiOre(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockMultiOre.NAMES[itemstack.getItemDamage()];
	}
	
}
