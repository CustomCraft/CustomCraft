package com.disney.customcraft.handlers.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.disney.customcraft.ModInfo;

public class ConfigHandler {

	/**
	 * Initialise the mods configuration files.
	 * @param configDir The location of configuration files.
	 */
	public static void init(File configDir) {
		loadMain(new File(configDir, ModInfo.CONFIG_LOC + ModInfo.MAINFILE_LOC));
		
		loadCore(new File(configDir, ModInfo.CONFIG_LOC + ModInfo.MODULE_LOC + ModInfo.MODULE_CORE));
		loadNomad(new File(configDir, ModInfo.CONFIG_LOC + ModInfo.MODULE_LOC + ModInfo.MODULE_NOMAD));
	}
	
	public static void loadMain(File dir) {
		Property p; Configuration c = new Configuration(dir);c.load();
		
		c.addCustomCategoryComment("modules", "enable specific modules");
		Config.moduleNomad = c.get("modules", "enable nomad module", Config.moduleNomad).getBoolean(Config.moduleNomad);
		Config.moduleMachine = c.get("modules", "enable machine module", Config.moduleMachine).getBoolean(Config.moduleMachine);
		Config.moduleMetal = c.get("modules", "enable metal module", Config.moduleMetal).getBoolean(Config.moduleMetal);
		Config.moduleWildlife = c.get("modules", "enable wildlife module", Config.moduleWildlife).getBoolean(Config.moduleWildlife);
		
		c.save();
	}
	
	/**
	 * Loads/Saves the core file
	 * @param dir The core file
	 */
	public static void loadCore(File dir) {
		Property p; Configuration c = new Configuration(dir);c.load();
		
		c.addCustomCategoryComment("ore", "enable ore related blocks/items and related worldgen");
		Config.coreCopper = c.get("ore", "enable Copper", Config.coreCopper).getBoolean(Config.coreCopper);
		Config.coreTin = c.get("ore", "enable Tin", Config.coreTin).getBoolean(Config.coreTin);
		Config.coreBronze = c.get("ore", "enable Bronze", Config.coreBronze).getBoolean(Config.coreBronze);
		Config.coreSteel = c.get("ore", "enable Steel", Config.coreSteel).getBoolean(Config.coreSteel);
		
		Config.coreBronzeCrafting = c.get("ore", "enable crafting of bronze at a crafting table", Config.coreBronzeCrafting).getBoolean(Config.coreBronzeCrafting);
		Config.coreSteelCrafting = c.get("ore", "enable crafting of steel at a furnace", Config.coreSteelCrafting).getBoolean(Config.coreSteelCrafting);
		
		c.addCustomCategoryComment("inventory", "enable items such as tools and armor");
		Config.coreCopperItems = c.get("inventory", "enable copper equipment", Config.coreCopperItems).getBoolean(Config.coreCopperItems);
		Config.coreTinItems = c.get("inventory", "enable tin equipment", Config.coreTinItems).getBoolean(Config.coreTinItems);
		Config.coreBronzeItems = c.get("inventory", "enable bronze equipment", Config.coreBronzeItems).getBoolean(Config.coreBronzeItems);
		Config.coreSteelItems = c.get("inventory", "enable steel equipment", Config.coreSteelItems).getBoolean(Config.coreSteelItems);
		
		c.addCustomCategoryComment("worldgen", "enable modifications to world generation");
		Config.coreModifyOreRates = c.get("worldgen", "balance vanilla ore rates", Config.coreModifyOreRates).getBoolean(Config.coreModifyOreRates);
		Config.coreFlatBedrock = c.get("worldgen", "flatten bedrock", Config.coreFlatBedrock).getBoolean(Config.coreFlatBedrock);
		Config.coreFlatNetherBedrock = c.get("worldgen", "flatten nether bedrock", Config.coreFlatNetherBedrock).getBoolean(Config.coreFlatNetherBedrock);
	
		c.addCustomCategoryComment("misc", "options unrelated to a general category");
		Config.coreRecolorIron = c.get("misc", "recolor iron items", Config.coreRecolorIron).getBoolean(Config.coreRecolorIron);
		
		c.save();
	}
	
	public static void loadNomad(File dir) {
		Property p; Configuration c = new Configuration(dir);c.load();
		
		c.addCustomCategoryComment("daylength", "change the duration of a day");
		Config.nomadLongerDays = c.get("daylength", "enable longer days", Config.nomadLongerDays).getBoolean(Config.nomadLongerDays);
		Config.nomadDayLength = c.get("daylength", "duration of a day (24000 = 1 normal day)", Config.nomadDayLength).getInt(Config.nomadDayLength);
		
		c.addCustomCategoryComment("darkness", "allow advanced darkness settings, allowing for darker nights");
		Config.nomadDarkerNights = c.get("darkness", "enable darker nights", Config.nomadDarkerNights).getBoolean(Config.nomadDarkerNights);
		Config.nomadGamma = (float) c.get("darkness", "forced brightness setting, replaces game menu setting (0.0 - 1.0 recommended)", Config.nomadGamma).getDouble(Config.nomadGamma);
		Config.nomadMoonlight = (float) c.get("darkness", "How much light is provided at full-moon (0.16 default)", Config.nomadMoonlight).getDouble(Config.nomadMoonlight);
		Config.nomadMoonPhases = new float[] { 1.0F * Config.nomadMoonlight + 0.01F, 0.75F * Config.nomadMoonlight + 0.01F, 0.5F * Config.nomadMoonlight + 0.01F, 0.25F * Config.nomadMoonlight + 0.01F, 0.0F * Config.nomadMoonlight + 0.01F, 0.25F * Config.nomadMoonlight + 0.01F, 0.5F * Config.nomadMoonlight + 0.01F,0.75F * Config.nomadMoonlight + 0.01F};
		
		c.save();
	}
	
}
