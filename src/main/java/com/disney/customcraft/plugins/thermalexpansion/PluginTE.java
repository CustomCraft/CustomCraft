package com.disney.customcraft.plugins.thermalexpansion;

import com.disney.customcraft.plugins.IPlugin;

import cpw.mods.fml.common.Loader;

public class PluginTE implements IPlugin {
	
	public static boolean pluginLoaded = false;

	@Override
	public void pre() {
		pluginLoaded = Loader.isModLoaded("ThermalExpansion");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void post() {
		disableRecipes();
	}

	private void disableRecipes() {
		//ItemHelper.removeRecipe(TFItems.nuggetCopper);
		//ItemHelper.removeRecipe(TFItems.ingotCopper);
		//ItemHelper.removeRecipe(TFBlocks.blockStorage.blockCopper);
		
		
	}
	
}
