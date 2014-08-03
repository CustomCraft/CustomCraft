package com.disney.customcraft.modules.core.block.itemblock;

import com.disney.customcraft.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCoreStorage extends ItemBlock {

	public ItemBlockCoreStorage(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + ModInfo.CORE_STORAGE_NAMES[itemstack.getItemDamage()];
	}
	
}
