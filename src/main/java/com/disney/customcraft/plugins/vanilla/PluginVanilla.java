package com.disney.customcraft.plugins.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import codechicken.nei.ItemStackSet;

import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.event.EventNEI;
import com.disney.customcraft.plugins.IPlugin;
import com.disney.customcraft.plugins.nei.PluginNEI;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class PluginVanilla implements IPlugin {
	
	@Override
	public void pre() {

	}

	@Override
	public void init() {
		
	}

	@Override
	public void post() {
		configNEI();
	}
	
	private void configNEI() {
		if(PluginNEI.pluginLoaded) {
			ItemStackSet items;
			
			items = new ItemStackSet();
			items.with(Blocks.stone);
			items.with(Blocks.grass);
			items.with(Blocks.dirt);
			items.with(Blocks.cobblestone);
			items.with(Blocks.planks);
			items.with(Blocks.sand);
			items.with(Blocks.gravel);
			items.with(Blocks.sandstone);
			items.with(Blocks.brick_block);
			items.with(Blocks.mossy_cobblestone);
			items.with(Blocks.obsidian);
			items.with(Blocks.snow);
			items.with(Blocks.ice);
			items.with(Blocks.netherrack);
			items.with(Blocks.soul_sand);
			items.with(Blocks.stonebrick);
			items.with(Blocks.mycelium);
			items.with(Blocks.nether_brick);
			items.with(Blocks.end_stone);
			items.with(Blocks.quartz_block);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Blocks", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.oak_stairs);
			items.with(Blocks.spruce_stairs);
			items.with(Blocks.birch_stairs);
			items.with(Blocks.jungle_stairs);
			items.with(Blocks.stone_stairs);
			items.with(Blocks.brick_stairs);
			items.with(Blocks.stone_brick_stairs);
			items.with(Blocks.nether_brick_stairs);
			items.with(Blocks.sandstone_stairs);
			items.with(Blocks.quartz_stairs);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Stairs", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.stone_slab);
			items.with(Blocks.wooden_slab);
			items.with(Blocks.snow_layer);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Slabs", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.fence);
			items.with(Blocks.fence_gate);
			items.with(Blocks.nether_brick_fence);
			items.with(Blocks.iron_bars);
			items.with(Blocks.cobblestone_wall);
			items.with(Blocks.mossy_cobblestone);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Fences", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.glass);
			items.with(Blocks.stained_glass);
			items.with(Blocks.glass_pane);
			items.with(Blocks.stained_glass_pane);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Glass", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.bookshelf);
			items.with(Blocks.chest);
			items.with(Blocks.trapped_chest);
			items.with(Blocks.crafting_table);
			items.with(Blocks.ladder);
			items.with(Blocks.lit_pumpkin);
			items.with(Blocks.trapdoor);
			items.with(Blocks.redstone_lamp);
			items.with(Items.bowl);
			items.with(Items.painting);
			items.with(Items.sign);
			items.with(Items.wooden_door);
			items.with(Items.iron_door);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Construction.Furniture", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.sapling);
			items.with(Blocks.log);
			items.with(Blocks.leaves);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Plants.Trees", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.red_flower);
			items.with(Blocks.yellow_flower);
			items.with(Blocks.tallgrass);
			items.with(Blocks.deadbush);
			items.with(Blocks.vine);
			items.with(Blocks.waterlily);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Plants.Flowers", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.red_mushroom);
			items.with(Blocks.brown_mushroom);
			items.with(Blocks.red_mushroom_block);
			items.with(Blocks.brown_mushroom_block);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Plants.Mushrooms", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.cactus);
			items.with(Blocks.pumpkin);
			items.with(Blocks.melon_block);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Plants.Growables", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.coal);
			items.with(Items.diamond);
			items.with(Items.iron_ingot);
			items.with(Items.gold_ingot);
			items.with(Items.redstone);
			items.with(Items.glowstone_dust);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Materials", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.gold_ore);
			items.with(Blocks.iron_ore);
			items.with(Blocks.coal_ore);
			items.with(Blocks.lapis_ore);
			items.with(Blocks.diamond_ore);
			items.with(Blocks.redstone_ore);
			items.with(Blocks.emerald_ore);
			items.with(Blocks.quartz_ore);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Ores", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.gold_block);
			items.with(Blocks.iron_block);
			items.with(Blocks.coal_block);
			items.with(Blocks.lapis_block);
			items.with(Blocks.diamond_block);
			items.with(Blocks.redstone_block);
			items.with(Blocks.emerald_block);
			items.with(Blocks.quartz_block);
			items.with(Blocks.glowstone);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Storage", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.wool);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Cloth.Wool", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.carpet);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Cloth.Carpet", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.clay);
			items.with(Blocks.hardened_clay);
			items.with(Blocks.stained_hardened_clay);
			items.with(Items.clay_ball);
			items.with(Items.brick);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Clay", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.dye);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Dye", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.bucket);
			items.with(Items.water_bucket);
			items.with(Items.lava_bucket);
			items.with(Items.milk_bucket);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Liquids", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.dragon_egg);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Drops", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.web);
			items.with(Blocks.hay_block);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Misc", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.dispenser);
			items.with(Blocks.piston);
			items.with(Blocks.sticky_piston);
			items.with(Blocks.furnace);
			items.with(Blocks.anvil);
			items.with(Blocks.hopper);
			items.with(Blocks.brewing_stand);
			items.with(Blocks.cauldron);
			items.with(Blocks.dropper);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Advanced.Basic", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.lever);
			items.with(Blocks.stone_pressure_plate);
			items.with(Blocks.wooden_pressure_plate);
			items.with(Blocks.heavy_weighted_pressure_plate);
			items.with(Blocks.light_weighted_pressure_plate);
			items.with(Blocks.redstone_torch);
			items.with(Blocks.stone_button);
			items.with(Blocks.wooden_button);
			items.with(Blocks.daylight_detector);
			items.with(Blocks.unpowered_repeater);
			items.with(Blocks.unpowered_comparator);
			items.with(Blocks.detector_rail);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Advanced.Redstone", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.rail);
			items.with(Blocks.golden_rail);
			items.with(Blocks.detector_rail);
			items.with(Blocks.activator_rail);
			items.with(Items.minecart);
			items.with(Items.chest_minecart);
			items.with(Items.furnace_minecart);
			items.with(Items.tnt_minecart);
			items.with(Items.hopper_minecart);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Advanced.Rail", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.noteblock);
			items.with(Blocks.jukebox);
			items.with(Blocks.enchanting_table);
			items.with(Blocks.ender_chest);
			items.with(Blocks.tripwire_hook);
			items.with(Blocks.beacon);
			items.with(Items.boat);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Advanced.Misc", items));
			items.clear();

			items = new ItemStackSet();
			items.with(Items.bow);
			items.with(Items.arrow);
			items.with(Items.iron_sword);
			items.with(Items.wooden_sword);
			items.with(Items.stone_sword);
			items.with(Items.diamond_sword);
			items.with(Items.golden_sword);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Weapons", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.iron_shovel);
			items.with(Items.iron_pickaxe);
			items.with(Items.iron_axe);
			items.with(Items.iron_hoe);
			items.with(Items.wooden_shovel);
			items.with(Items.wooden_pickaxe);
			items.with(Items.wooden_axe);
			items.with(Items.wooden_hoe);
			items.with(Items.stone_shovel);
			items.with(Items.stone_pickaxe);
			items.with(Items.stone_axe);
			items.with(Items.stone_hoe);
			items.with(Items.diamond_shovel);
			items.with(Items.diamond_pickaxe);
			items.with(Items.diamond_axe);
			items.with(Items.diamond_hoe);
			items.with(Items.golden_shovel);
			items.with(Items.golden_pickaxe);
			items.with(Items.golden_axe);
			items.with(Items.golden_hoe);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Tools", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.leather_helmet);
			items.with(Items.leather_chestplate);
			items.with(Items.leather_leggings);
			items.with(Items.leather_boots);
			items.with(Items.iron_helmet);
			items.with(Items.iron_chestplate);
			items.with(Items.iron_leggings);
			items.with(Items.iron_boots);
			items.with(Items.diamond_helmet);
			items.with(Items.diamond_chestplate);
			items.with(Items.diamond_leggings);
			items.with(Items.diamond_boots);
			items.with(Items.golden_helmet);
			items.with(Items.golden_chestplate);
			items.with(Items.golden_leggings);
			items.with(Items.golden_boots);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Armor", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Blocks.tnt);
			items.with(Blocks.torch);
			items.with(Items.flint_and_steel);
			items.with(Items.compass);
			items.with(Items.fishing_rod);
			items.with(Items.clock);
			items.with(Items.shears);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Misc", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.apple);
			items.with(Items.mushroom_stew);
			items.with(Items.bread);
			items.with(Items.cooked_porkchop);
			items.with(Items.golden_apple);
			items.with(Items.cooked_fished);
			items.with(Items.cake);
			items.with(Items.cookie);
			items.with(Items.melon);
			items.with(Items.cooked_beef);
			items.with(Items.cooked_chicken);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Consumables.Food", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.porkchop);
			items.with(Items.egg);
			items.with(Items.fish);
			items.with(Items.sugar);
			items.with(Items.beef);
			items.with(Items.chicken);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Consumables.Ingredients", items));
			items.clear();
			
			items = new ItemStackSet();
			items.with(Items.potionitem);
			items.with(Items.experience_bottle);
			items.with(Items.glass_bottle);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Consumables.Potions", items));
			items.clear();
		}
	}
}
