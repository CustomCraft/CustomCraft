package com.disney.customcraft.block.itemblock;

import com.disney.customcraft.block.BlockMultiOre;
import com.disney.customcraft.block.BlockMultiTable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemBlockMultiTable extends ItemBlock {
	
	public ItemBlockMultiTable(Block block) {
		super(block);
		
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockMultiTable.NAMES[itemstack.getItemDamage()];
	}
}
