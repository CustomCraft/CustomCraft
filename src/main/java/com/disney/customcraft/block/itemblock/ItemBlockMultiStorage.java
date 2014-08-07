package com.disney.customcraft.block.itemblock;

import com.disney.customcraft.block.BlockMultiStorage;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMultiStorage extends ItemBlock {

	public ItemBlockMultiStorage(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockMultiStorage.NAMES[itemstack.getItemDamage()];
	}
	
}
