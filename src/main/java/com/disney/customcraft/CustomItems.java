package com.disney.customcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

import com.disney.customcraft.block.BlockCampfire;
import com.disney.customcraft.block.BlockMultiOre;
import com.disney.customcraft.block.BlockMultiStorage;
import com.disney.customcraft.block.BlockOilTrail;
import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;
import com.disney.customcraft.fluid.FluidMulti;
import com.disney.customcraft.handlers.ItemHelper;
import com.disney.customcraft.handlers.RecipeHelper;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.item.ItemClub;
import com.disney.customcraft.item.ItemMultiDust;
import com.disney.customcraft.item.ItemMultiIngot;
import com.disney.customcraft.item.part.ItemCustomSword;
import com.disney.customcraft.item.part.PartBlade;
import com.disney.customcraft.item.part.PartHilt;
import com.disney.customcraft.materials.FluidMaterial;
import com.disney.customcraft.materials.TorchMaterial;

public class CustomItems {
	
	//ores
	public static Block oreMulti;
	public static Block storageMulti;
	public static Item ingotMulti;
	public static Item dustMulti;
	
	//tools and armor
	public static Item clubStone;
	
	//fluids
	public static FluidMulti fluidOil;
	public static FluidMulti fluidFuel;
	public static FluidMulti fluidCoolant;
	public static Block oilTrail;
	
	//lights and torches
	public static Block blockCampfire;
	
	public static Block torchWoodOn;
	public static Block torchCoalOn;
	public static Block torchWaxOn;
	public static Block torchOilOn;
	public static Block torchWoodOff;
	public static Block torchCoalOff;
	public static Block torchWaxOff;
	public static Block torchOilOff;
	
	public static Item customSword;
	public static Item partBlade;
	public static Item partHilt;
	
	public void init() {
		oreMulti = new BlockMultiOre();
		storageMulti = new BlockMultiStorage();
		ingotMulti = new ItemMultiIngot();
		dustMulti = new ItemMultiDust();
		
		clubStone = new ItemClub("clubStone", ToolMaterial.STONE);
		
		fluidOil = new FluidMulti("oil", FluidMaterial.OIL);
		fluidFuel = new FluidMulti("fuel", FluidMaterial.FUEL);
		fluidCoolant = new FluidMulti("coolant", FluidMaterial.COOLANT);
		oilTrail = new BlockOilTrail();
		
		blockCampfire = new BlockCampfire("campfire");
		
		torchWoodOn = new BlockTorchOn("torchWood", TorchMaterial.WOOD);
		torchCoalOn = new BlockTorchOn("torchCoal", TorchMaterial.COAL);
		torchWaxOn = new BlockTorchOn("torchWax", TorchMaterial.WAX);
		torchOilOn = new BlockTorchOn("torchOil", TorchMaterial.OIL);
		torchWoodOff = new BlockTorchOff("torchWood", TorchMaterial.WOOD);
		torchCoalOff = new BlockTorchOff("torchCoal", TorchMaterial.COAL);
		torchWaxOff = new BlockTorchOff("torchWax", TorchMaterial.WAX);
		torchOilOff = new BlockTorchOff("torchOil", TorchMaterial.OIL);
		
		customSword = new ItemCustomSword();
		partBlade = new PartBlade();
		partHilt = new PartHilt();
		
		ItemHelper.recolorIron();
	}
	
	public void post() {
		RecipeHelper.addMetals();
		
		if(Config.coreBronzeCrafting) RecipeHelper.addBronzeCrafting();
		if(Config.coreSteelCrafting) RecipeHelper.addSteelCrafting();
	}

}
