package com.disney.customcraft.handlers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.oredict.OreDictionary;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;

public class ItemHelper {
	
	public static boolean dictExists(String oreName) {
		return !OreDictionary.getOres(oreName).isEmpty();
	}

	public static String getIconLoc(String name) {
		return name.replace("tile.", "").replace("item.", "").replace("customcraft.", "customcraft:");
	}
	
	/*public static Block getTorchOn(BlockTorchOff torchOff) {
		if(torchOff == CustomItems.torchWoodOff) {
			return CustomItems.torchWoodOn;
		} else if(torchOff == CustomItems.torchCoalOff) {
			return CustomItems.torchCoalOn;
		} else if(torchOff == CustomItems.torchWaxOff) {
			return CustomItems.torchWaxOn;
		} else if(torchOff == CustomItems.torchOilOff) {
			return CustomItems.torchOilOn;
		}
		return CustomItems.torchWoodOn;
	}
	
	public static Block getTorchOff(BlockTorchOn torchOn) {
		if(torchOn == CustomItems.torchWoodOn) {
			return CustomItems.torchWoodOff;
		} else if(torchOn == CustomItems.torchCoalOn) {
			return CustomItems.torchCoalOff;
		} else if(torchOn == CustomItems.torchWaxOn) {
			return CustomItems.torchWaxOff;
		} else if(torchOn == CustomItems.torchOilOn) {
			return CustomItems.torchOilOff;
		}
		return CustomItems.torchWoodOff;
	}*/
	
}
