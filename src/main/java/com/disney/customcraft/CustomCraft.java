package com.disney.customcraft;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.config.ConfigHandler;
import com.disney.customcraft.handlers.network.PacketPipeline;
import com.disney.customcraft.handlers.proxy.CommonProxy;
import com.disney.customcraft.modules.IModule;
import com.disney.customcraft.modules.core.ModuleCore;
import com.disney.customcraft.modules.nomad.ModuleNomad;
import com.disney.customcraft.plugins.IPlugin;
import com.disney.customcraft.plugins.nei.PluginNEI;
import com.disney.customcraft.plugins.vanilla.PluginVanilla;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod( modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "after:*;" )

public class CustomCraft {

	@Instance
	public static CustomCraft instance;
	
	public static PacketPipeline networkHandler = new PacketPipeline();
	
	@SidedProxy( clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.COMMON_PROXY )
	public static CommonProxy proxy;
	
	public static List<IModule> modules = new ArrayList<IModule>();
	public static List<IPlugin> plugins = new ArrayList<IPlugin>();

	@EventHandler
	public static void preInit( FMLPreInitializationEvent event ) {
		LogHandler.init(event.getModLog());		
		LogHandler.log(Level.INFO, "Pre-Initializing");
		
		ConfigHandler.init(event.getModConfigurationDirectory());
		
		modules.add(new ModuleCore());
		if(Config.moduleNomad) modules.add(new ModuleNomad()); else { ModuleNomad.disable(); }
		
		plugins.add(new PluginVanilla());
		plugins.add(new PluginNEI());
		
		for (IModule module : modules) {
			module.pre();
		}
		for (IPlugin plugin : plugins) {
			plugin.pre();
		}
	}

	@EventHandler
	public static void init( FMLInitializationEvent event ) {
		LogHandler.log(Level.INFO, "Initializing");
		
		networkHandler.initialise();
		
		for (IModule module : modules) {
			module.init();
		}
		for (IPlugin plugin : plugins) {
			plugin.init();
		}
	}

	@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) {
		LogHandler.log(Level.INFO, "Post-Initializing");
		
		networkHandler.postInitialise();
		
		for (IModule module : modules) {
			module.post();
		}
		for (IPlugin plugin : plugins) {
			plugin.post();
		}
		
		LogHandler.log(Level.INFO, "CustomCraft Finished");
	}
}