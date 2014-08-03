package com.disney.customcraft.modules.nomad;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import codechicken.nei.ItemStackSet;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.event.EventHarvest;
import com.disney.customcraft.handlers.event.EventLogin;
import com.disney.customcraft.modules.IModule;
import com.disney.customcraft.modules.core.ModuleCore;
import com.disney.customcraft.modules.core.block.BlockCoreOre;
import com.disney.customcraft.modules.core.block.BlockCoreStorage;
import com.disney.customcraft.modules.core.item.ItemCoreDust;
import com.disney.customcraft.modules.core.item.ItemCoreIngot;
import com.disney.customcraft.modules.core.item.ItemCoreSword;
import com.disney.customcraft.modules.nomad.item.ItemClub;
import com.disney.customcraft.plugins.nei.PluginNEI;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModuleNomad implements IModule {

	public static Item[] itemClub;
	
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
		
		FMLCommonHandler.instance().bus().register(new EventLogin());
		
		if(Config.nomadSticksMineStone) MinecraftForge.EVENT_BUS.register(new EventHarvest());
	}
	
	public static void disable() {
		Config.nomadLongerDays = false;
		Config.nomadDarkerNights = false;
	}
	
	private void initItems() {
		if(Config.nomadClubs) {
			itemClub = new Item[ModInfo.NOMAD_CLUB_NAMES.length];
			for(int i = 0; i < itemClub.length; i++) itemClub[i] = new ItemClub(ModInfo.TOOL_MATERIALS[i], ModInfo.NOMAD_CLUB_NAMES[i]);
		}
	}
	
	private void initRecipes() {
		if(Config.nomadClubs) {
			ItemStack stick = new ItemStack(Items.stick);
			ItemStack stone = new ItemStack(Blocks.cobblestone);
			
			GameRegistry.addShapedRecipe(new ItemStack(itemClub[0]), "aba", " b ", " b ", 'a', stone, 'b', stick);
		}
	}
	
	private void configNEI() {
		if(PluginNEI.pluginLoaded) {
			ItemStackSet items;
			
			items = new ItemStackSet();
			for(int i = 0; i < itemClub.length; i++) items.with(itemClub[i]);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Weapons", items));
			items.clear();
			
			items = new ItemStackSet();
			for(int i = 0; i < itemClub.length; i++) items.with(itemClub[i]);
			if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Tools", items));
			items.clear();
		}
	}
		
}
