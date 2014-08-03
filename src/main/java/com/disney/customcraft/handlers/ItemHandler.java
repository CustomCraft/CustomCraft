package com.disney.customcraft.handlers;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemHandler {
	
	public static boolean dictExists(String oreName) {
		return !OreDictionary.getOres(oreName).isEmpty();
	}

	public static boolean addRecipeStorage(ItemStack storage, String ingotName) {
		if(storage != null && !dictExists(ingotName)) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(storage, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName));
			return true;
		}
		return false;
	}
	
	public static void removeRecipe(ItemStack resultItem) {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for(int i = 0; i < recipes.size(); i++) {
            IRecipe tmpRecipe = recipes.get(i);
            ItemStack recipeResult = tmpRecipe.getRecipeOutput();
            
            if(ItemStack.areItemStacksEqual(resultItem, recipeResult)) {
                recipes.remove(i--);
            }
        }
    }
	
	public static void removeDictRecipe(ItemStack resultItem) {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for(int i = 0; i < recipes.size(); i++) {
            IRecipe tmpRecipe = recipes.get(i);
            ItemStack recipeResult = tmpRecipe.getRecipeOutput();
            
            if(ItemStack.areItemStacksEqual(resultItem, recipeResult)) {
                recipes.remove(i--);
            }
        }
    }
	
}
