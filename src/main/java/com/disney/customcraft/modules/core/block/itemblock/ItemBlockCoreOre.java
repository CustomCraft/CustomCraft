package com.disney.customcraft.modules.core.block.itemblock;

import com.disney.customcraft.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCoreOre extends ItemBlock {

	public ItemBlockCoreOre(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + ModInfo.CORE_ORE_NAMES[itemstack.getItemDamage()];
	}
	
}
