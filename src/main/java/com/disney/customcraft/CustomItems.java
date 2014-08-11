package com.disney.customcraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.IShaftPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryParts;
import com.disney.customcraft.api.RegistryParts.ShaftMaterial;
import com.disney.customcraft.api.RegistryTorch;
import com.disney.customcraft.api.RegistryParts.HeadMaterial;
import com.disney.customcraft.api.RegistryTorch.TorchMaterial;
import com.disney.customcraft.block.BlockCampfire;
import com.disney.customcraft.block.BlockMultiOre;
import com.disney.customcraft.block.BlockMultiStorage;
import com.disney.customcraft.block.BlockMultiTable;
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
import com.disney.customcraft.item.part.ItemCustomTool;
import com.disney.customcraft.item.part.ItemHead;
import com.disney.customcraft.item.part.ItemHeadBlade;
import com.disney.customcraft.item.part.ItemShaft;
import com.disney.customcraft.item.part.ItemShaftStick;
import com.disney.customcraft.materials.FluidMaterial;

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
	
	public static Block bindingTable;
	
	public static Item customHead;
	public static Item customShaft;
	public static Item customTool;
	
	public void pre() {
		RegistryTorch.addTorch(TorchMaterial.WOOD);
		RegistryTorch.addTorch(TorchMaterial.COAL);		
	}
	
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
		
		bindingTable = new BlockMultiTable();
		
		RegistryParts.addMaterialHead(HeadMaterial.COPPER);
		RegistryParts.addMaterialHead(HeadMaterial.TIN);
		RegistryParts.addMaterialHead(HeadMaterial.IRON);
		RegistryParts.addMaterialShaft(ShaftMaterial.WOOD);
		
		IHeadPart headPart = new ItemHeadBlade();
		IShaftPart shaftPart = new ItemShaftStick();
		RegistryParts.addPartHead(headPart);
		RegistryParts.addPartShaft(shaftPart);
		
		ITool tool = new ItemCustomSword(headPart, shaftPart);
		RegistryParts.addTool(tool);
		
		customHead = new ItemHead(headPart);
		customShaft = new ItemShaft(shaftPart);
		customTool = new ItemCustomTool();
		
		RegistryTorch.initTorches();
		
		ItemHelper.recolorIron();
	}
	
	public void post() {
		RecipeHelper.addMetals();
		
		if(Config.coreBronzeCrafting) RecipeHelper.addBronzeCrafting();
		if(Config.coreSteelCrafting) RecipeHelper.addSteelCrafting();
	}

}
