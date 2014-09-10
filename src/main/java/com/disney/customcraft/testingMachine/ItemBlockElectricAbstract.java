package com.disney.customcraft.testingMachine;

import com.disney.customcraft.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockElectricAbstract extends ItemBlock {

	public ItemBlockElectricAbstract(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockElectricAbstract.NAMES[itemstack.getItemDamage()];
	}
	
}
