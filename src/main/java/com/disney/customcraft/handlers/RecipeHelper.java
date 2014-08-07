package com.disney.customcraft.handlers;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.BlockMultiOre;
import com.disney.customcraft.block.BlockMultiStorage;
import com.disney.customcraft.item.ItemMultiIngot;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHelper {
	
	public static void addMetals() {
		for(int i = 0; i < BlockMultiOre.NAMES.length; i++) {
			//add ore to ingot smelting
			if(!(BlockMultiOre.NAMES[i].equals("oreBronze") || BlockMultiOre.NAMES[i].equals("oreSteel"))) {
				GameRegistry.addSmelting(new ItemStack(CustomItems.oreMulti, 1, i), new ItemStack(CustomItems.ingotMulti, 1, i), getSmeltingXP(BlockMultiOre.NAMES[i]));
			}
			
			//add ingot to storage crafting
			RecipeHelper.addUnstorageRecipe(new ItemStack(CustomItems.ingotMulti, 9, i), BlockMultiStorage.NAMES[i]);
			
			//add storage to ingot crafting
			RecipeHelper.addRecipeStorage(new ItemStack(CustomItems.storageMulti, 1, i), ItemMultiIngot.NAMES[i]);
		}
	}
	
	public static boolean addBronzeCrafting() {
		int meta = ItemMultiIngot.getMeta("ingotBronze");
		if(meta > -1) {
			if(ItemHelper.dictExists("ingotCopper") && ItemHelper.dictExists("ingotTin")) {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(CustomItems.ingotMulti, 2, meta), "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin" ));
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean addSteelCrafting() {
		int meta = ItemMultiIngot.getMeta("ingotSteel");
		if(meta > -1) {
			GameRegistry.addSmelting(new ItemStack(Blocks.iron_block), new ItemStack(CustomItems.ingotMulti, 2, meta), 0.0f);
			
			return true;
		}
		return false;
	}
	
	private static float getSmeltingXP(String name) {
		if(name.contains("copper")) {
			return 0.4F;
		} else if(name.contains("tin")) {
			return 0.8F;
		} else if(name.contains("bronze")) {
			return 1.0F;
		} else if(name.contains("steel")) {
			return 1.0F;
		}
		return 0.0F;
	}
	
	private static boolean addRecipeStorage(ItemStack storage, String ingotName) {
		if(storage != null && ItemHelper.dictExists(ingotName)) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(storage, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName, ingotName));
			
			return true;
		}
		return false;
	}
	
	private static boolean addUnstorageRecipe(ItemStack ingot, String storageName) {
	    if(ingot != null && ItemHelper.dictExists(storageName)) {
	    	GameRegistry.addRecipe(new ShapelessOreRecipe(ingot, new Object[] { storageName }));
	    	
	    	return true;
	    }
	    return false;
	}
	
	private static boolean addClubRecipe(ItemStack club, String ingotName) {
		if(club != null && ItemHelper.dictExists(ingotName)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(club, new Object[] { "isi", " s ", " s ", 'i', ingotName, 's', "stickWood" }));
			
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * REMOVE RECIPES
	 * @param resultItem
	 */
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
