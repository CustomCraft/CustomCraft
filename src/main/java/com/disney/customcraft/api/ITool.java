package com.disney.customcraft.api;

import net.minecraft.item.ItemStack;

public interface ITool {
	
	public String getPartName();
	
	public void createRecipes();
	public ItemStack createOutput(int headID, int shaftID);

}
