package com.disney.customcraft.api;

import com.disney.customcraft.api.RegistryTools.HeadMaterial;
import com.disney.customcraft.api.RegistryTools.ShaftMaterial;

import net.minecraft.item.ItemStack;

public interface ITool {
	
	public String getPartName();
	
	public void createRecipes();
	public ItemStack createOutput(HeadMaterial headMaterial, ShaftMaterial shaftMaterial);

}
