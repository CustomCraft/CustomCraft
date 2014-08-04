package com.disney.customcraft.plugins.thermalexpansion;

import thermalexpansion.util.crafting.FurnaceManager;
import thermalexpansion.util.crafting.TransposerManager;
import thermalexpansion.util.crafting.FurnaceManager.RecipeFurnace;
import thermalexpansion.util.crafting.SmelterManager;
import thermalexpansion.util.crafting.SmelterManager.RecipeSmelter;
import thermalexpansion.util.crafting.PulverizerManager;
import thermalfoundation.block.TFBlocks;
import thermalfoundation.item.TFItems;

import com.disney.customcraft.handlers.ItemHandler;
import com.disney.customcraft.plugins.IPlugin;

import cpw.mods.fml.common.Loader;

public class PluginTE implements IPlugin {
	
	public static boolean pluginLoaded = false;

	@Override
	public void pre() {
		pluginLoaded = Loader.isModLoaded("ThermalFoundation") && Loader.isModLoaded("ThermalExpansion");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void post() {
		disableRecipes();
	}

	private void disableRecipes() {
		ItemHandler.removeRecipe(TFItems.nuggetCopper);
		ItemHandler.removeRecipe(TFItems.ingotCopper);
		ItemHandler.removeRecipe(TFBlocks.blockStorage.blockCopper);
		
		for(RecipeFurnace recipe : FurnaceManager.getRecipeList()) {
			FurnaceManager.removeRecipe(recipe.getOutput());
		}
	}
	
}
