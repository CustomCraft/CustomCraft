package com.disney.customcraft.modules.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPiston;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import codechicken.nei.ItemStackMap;
import codechicken.nei.ItemStackSet;
import codechicken.nei.SubsetWidget.SubsetTag;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.wgen.CoreWorldGenerator;
import com.disney.customcraft.modules.IModule;
import com.disney.customcraft.modules.core.block.BlockCoreOre;
import com.disney.customcraft.modules.core.block.BlockCoreStorage;
import com.disney.customcraft.modules.core.item.ItemCoreArmor;
import com.disney.customcraft.modules.core.item.ItemCoreAxe;
import com.disney.customcraft.modules.core.item.ItemCoreDust;
import com.disney.customcraft.modules.core.item.ItemCoreHoe;
import com.disney.customcraft.modules.core.item.ItemCoreIngot;
import com.disney.customcraft.modules.core.item.ItemCorePickaxe;
import com.disney.customcraft.modules.core.item.ItemCoreShovel;
import com.disney.customcraft.modules.core.item.ItemCoreSword;
import com.disney.customcraft.plugins.nei.PluginNEI;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleCore implements IModule {
	
	IWorldGenerator worldGenerator;
	
	public static BlockCoreOre blockOre;
	public static BlockCoreStorage blockStorage;
	
	public static Item itemIngot;
	public static Item itemDust;
	
	public static Item[] itemSword;
	public static Item[] itemPickaxe;
	public static Item[] itemAxe;
	public static Item[] itemShovel;
	public static Item[] itemHoe;
	
	public static Item[] itemHelmet;
	public static Item[] itemChest;
	public static Item[] itemLegs;
	public static Item[] itemBoots;
	
	@Override
	public void pre() {
		
	}

	@Override
	public void init() {
		initItems();
	}

	@Override
	public void post() {
		initRecipes();
		configNEI();
		
		if(Config.coreRecolorIron) recolorIron();
		
		MinecraftForge.ORE_GEN_BUS.register(new CoreWorldGenerator());
		
		worldGenerator = new CoreWorldGenerator();
		GameRegistry.registerWorldGenerator(worldGenerator, 1);
	}

	private void initItems() {
		blockOre = new BlockCoreOre();
		blockStorage = new BlockCoreStorage();
		
		itemIngot = new ItemCoreIngot();
		itemDust = new ItemCoreDust();
		
		itemSword = new Item[ModInfo.CORE_SWORD_NAMES.length];
		for(int i = 0; i < itemSword.length; i++) itemSword[i] = new ItemCoreSword(ModInfo.TOOL_MATERIALS[i], ModInfo.CORE_SWORD_NAMES[i]);
		
		itemPickaxe = new Item[ModInfo.CORE_PICKAXE_NAMES.length];
		for(int i = 0; i < itemPickaxe.length; i++) itemPickaxe[i] = new ItemCorePickaxe(ModInfo.TOOL_MATERIALS[i], ModInfo.CORE_PICKAXE_NAMES[i]);
		
		itemAxe = new Item[ModInfo.CORE_AXE_NAMES.length];
		for(int i = 0; i < itemAxe.length; i++) itemAxe[i] = new ItemCoreAxe(ModInfo.TOOL_MATERIALS[i], ModInfo.CORE_AXE_NAMES[i]);
		
		itemShovel = new Item[ModInfo.CORE_SHOVEL_NAMES.length];
		for(int i = 0; i < itemShovel.length; i++) itemShovel[i] = new ItemCoreShovel(ModInfo.TOOL_MATERIALS[i], ModInfo.CORE_SHOVEL_NAMES[i]);
		
		itemHoe = new Item[ModInfo.CORE_HOE_NAMES.length];
		for(int i = 0; i < itemHoe.length; i++) itemHoe[i] = new ItemCoreHoe(ModInfo.TOOL_MATERIALS[i], ModInfo.CORE_HOE_NAMES[i]);
		
		itemHelmet = new Item[ModInfo.CORE_HELMET_NAMES.length];
		for(int i = 0; i < itemHelmet.length; i++) itemHelmet[i] = new ItemCoreArmor(ModInfo.ARMOR_MATERIALS[i], 0, ModInfo.CORE_HELMET_NAMES[i]);
		
		itemChest = new Item[ModInfo.CORE_CHEST_NAMES.length];
		for(int i = 0; i < itemChest.length; i++) itemChest[i] = new ItemCoreArmor(ModInfo.ARMOR_MATERIALS[i], 1, ModInfo.CORE_CHEST_NAMES[i]);
		
		itemLegs = new Item[ModInfo.CORE_LEGS_NAMES.length];
		for(int i = 0; i < itemLegs.length; i++) itemLegs[i] = new ItemCoreArmor(ModInfo.ARMOR_MATERIALS[i], 2, ModInfo.CORE_LEGS_NAMES[i]);
		
		itemBoots = new Item[ModInfo.CORE_BOOTS_NAMES.length];
		for(int i = 0; i < itemBoots.length; i++) itemBoots[i] = new ItemCoreArmor(ModInfo.ARMOR_MATERIALS[i], 3, ModInfo.CORE_BOOTS_NAMES[i]);
		
	}
	
	private void initRecipes() {
		if(Config.coreCopper) {
			ItemStack ingot = new ItemStack(itemIngot, 1, 0);
			
			GameRegistry.addSmelting(new ItemStack(blockOre, 1, 0), ingot, 0.4f);
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngot, 9, 0), new ItemStack(blockStorage, 1, 0));
			GameRegistry.addShapedRecipe(new ItemStack(blockStorage, 1, 0), "aaa", "aaa", "aaa", 'a', ingot);
			
			if(Config.coreCopperItems) {
				ItemStack stick = new ItemStack(Items.stick);
				
				GameRegistry.addShapedRecipe(new ItemStack(itemSword[0]), " a ", " a ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemPickaxe[0]), "aaa", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemAxe[0]), "aa ", "ab ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemShovel[0]), " a ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHoe[0]), "aa ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHelmet[0]), "aaa", "a a", "   ", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemChest[0]), "a a", "aaa", "aaa", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemLegs[0]), "aaa", "a a", "a a", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemBoots[0]), "   ", "a a", "a a", 'a', ingot);
			}
		}
		
		if(Config.coreTin) {
			ItemStack ingot = new ItemStack(itemIngot, 1, 1);
			
			GameRegistry.addSmelting(new ItemStack(blockOre, 1, 1), ingot, 0.8f);
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngot, 9, 1), new ItemStack(blockStorage, 1, 1));
			GameRegistry.addShapedRecipe(new ItemStack(blockStorage, 1, 1), "aaa", "aaa", "aaa", 'a', ingot);
			
			if(Config.coreTinItems) {
				ItemStack stick = new ItemStack(Items.stick);
				
				GameRegistry.addShapedRecipe(new ItemStack(itemSword[1]), " a ", " a ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemPickaxe[1]), "aaa", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemAxe[1]), "aa ", "ab ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemShovel[1]), " a ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHoe[1]), "aa ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHelmet[1]), "aaa", "a a", "   ", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemChest[1]), "a a", "aaa", "aaa", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemLegs[1]), "aaa", "a a", "a a", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemBoots[1]), "   ", "a a", "a a", 'a', ingot);
			}
		}
		
		if(Config.coreBronze) {
			ItemStack ingot = new ItemStack(itemIngot, 1, 2);
			
			//GameRegistry.addSmelting(new ItemStack(blockOre, 1, 2), ingot, 1.0f);
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngot, 9, 2), new ItemStack(blockStorage, 1, 2));
			GameRegistry.addShapedRecipe(new ItemStack(blockStorage, 1, 2), "aaa", "aaa", "aaa", 'a', ingot);
			
			if(Config.coreBronzeItems) {
				ItemStack stick = new ItemStack(Items.stick);	
				
				GameRegistry.addShapedRecipe(new ItemStack(itemSword[2]), " a ", " a ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemPickaxe[2]), "aaa", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemAxe[2]), "aa ", "ab ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemShovel[2]), " a ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHoe[2]), "aa ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHelmet[2]), "aaa", "a a", "   ", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemChest[2]), "a a", "aaa", "aaa", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemLegs[2]), "aaa", "a a", "a a", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemBoots[2]), "   ", "a a", "a a", 'a', ingot);
			}
		}
		
		if(Config.coreSteel) {
			ItemStack ingot = new ItemStack(itemIngot, 1, 3);
			
			//GameRegistry.addSmelting(new ItemStack(blockOre, 1, 3), ingot, 1.0f);
			
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngot, 9, 3), new ItemStack(blockStorage, 1, 3));
			GameRegistry.addShapedRecipe(new ItemStack(blockStorage, 1, 3), "aaa", "aaa", "aaa", 'a', ingot);
			
			if(Config.coreSteelItems) {
				ItemStack stick = new ItemStack(Items.stick);	
				
				GameRegistry.addShapedRecipe(new ItemStack(itemSword[3]), " a ", " a ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemPickaxe[3]), "aaa", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemAxe[3]), "aa ", "ab ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemShovel[3]), " a ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHoe[3]), "aa ", " b ", " b ", 'a', ingot, 'b', stick);
				GameRegistry.addShapedRecipe(new ItemStack(itemHelmet[3]), "aaa", "a a", "   ", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemChest[3]), "a a", "aaa", "aaa", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemLegs[3]), "aaa", "a a", "a a", 'a', ingot);
				GameRegistry.addShapedRecipe(new ItemStack(itemBoots[3]), "   ", "a a", "a a", 'a', ingot);
			}
		}
		
		if(Config.coreBronzeCrafting) {
			GameRegistry.addShapelessRecipe(new ItemStack(itemIngot, 1, 3), new ItemStack(itemIngot, 1, 0), new ItemStack(itemIngot, 1, 1));
		}
		
		if(Config.coreSteelCrafting) {
			GameRegistry.addSmelting(new ItemStack(Blocks.iron_block), new ItemStack(itemIngot, 2, 3), 0.0f);
		}	
	}
	
	private void recolorIron() {
		Blocks.iron_block.setBlockTextureName("customcraft:core/" + "blockIron");
		Items.iron_ingot.setTextureName("customcraft:core/" + "ingotIron");
		
		Items.iron_sword.setTextureName("customcraft:core/" + "swordIron");
		Items.iron_pickaxe.setTextureName("customcraft:core/" + "pickaxeIron");
		Items.iron_axe.setTextureName("customcraft:core/" + "axeIron");
		Items.iron_shovel.setTextureName("customcraft:core/" + "shovelIron");
		Items.iron_hoe.setTextureName("customcraft:core/" + "hoeIron");
		
		Items.iron_helmet.setTextureName("customcraft:core/" + "helmetIron");
		Items.iron_chestplate.setTextureName("customcraft:core/" + "chestIron");
		Items.iron_leggings.setTextureName("customcraft:core/" + "legsIron");
		Items.iron_boots.setTextureName("customcraft:core/" + "bootsIron");
	}
	
	private void configNEI() {
		if(PluginNEI.pluginLoaded) {
			ItemStackSet items;
			
			items = new ItemStackSet();
			items.with(blockOre);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Ores", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(blockStorage);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Storage", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(itemIngot);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Materials", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(itemDust);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Dusts", items));
			items.clear();
			
			items = new ItemStackSet();
			for(int i = 0; i < itemHelmet.length; i++) items.with(itemHelmet[i]);
			for(int i = 0; i < itemChest.length; i++) items.with(itemChest[i]);
			for(int i = 0; i < itemLegs.length; i++) items.with(itemLegs[i]);
			for(int i = 0; i < itemBoots.length; i++) items.with(itemBoots[i]);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Armor", items));
			items.clear();
			
			items = new ItemStackSet();
			for(int i = 0; i < itemHelmet.length; i++) items.with(itemSword[i]);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Weapons", items));
			items.clear();
			
			items = new ItemStackSet();
			for(int i = 0; i < itemPickaxe.length; i++) items.with(itemPickaxe[i]);
			for(int i = 0; i < itemAxe.length; i++) items.with(itemAxe[i]);
			for(int i = 0; i < itemShovel.length; i++) items.with(itemShovel[i]);
			for(int i = 0; i < itemHoe.length; i++) items.with(itemHoe[i]);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Tools", items));
			items.clear();
		}
	}
}
