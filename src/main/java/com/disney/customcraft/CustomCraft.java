package com.disney.customcraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Level;

import com.disney.customcraft.event.EventAnvil;
import com.disney.customcraft.event.EventBucketFill;
import com.disney.customcraft.event.EventFog;
import com.disney.customcraft.event.EventHarvest;
import com.disney.customcraft.event.EventLogin;
import com.disney.customcraft.event.EventTooltip;
import com.disney.customcraft.handlers.GuiHandler;
import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.handlers.config.ConfigHandler;
import com.disney.customcraft.handlers.network.PacketPipeline;
import com.disney.customcraft.handlers.proxy.CommonProxy;
import com.disney.customcraft.handlers.wgen.CustomGenerator;
import com.disney.customcraft.plugins.IPlugin;
import com.disney.customcraft.plugins.nei.PluginNEI;
import com.disney.customcraft.plugins.vanilla.PluginVanilla;
import com.disney.customcraft.testing.TestWorldProvider;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod( modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "after:*;" )

public class CustomCraft {

	@Instance
	public static CustomCraft instance;
	
	public static PacketPipeline networkHandler = new PacketPipeline();
	
	@SidedProxy( clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.COMMON_PROXY )
	public static CommonProxy proxy;
	
	public static CustomItems customItems = new CustomItems();
	public static CustomGenerator customGenerator = new CustomGenerator();
	
	public static List<IPlugin> plugins = new ArrayList<IPlugin>();

	@EventHandler
	public static void preInit( FMLPreInitializationEvent event ) {
		LogHandler.init(event.getModLog());		
		LogHandler.log(Level.INFO, "Pre-Initializing");
		
		ConfigHandler.init(event.getModConfigurationDirectory());
				
		plugins.add(new PluginVanilla());
		plugins.add(new PluginNEI());
		
		for (IPlugin plugin : plugins) {
			plugin.pre();
		}
		
		customItems.pre();
		
		DimensionManager.registerProviderType(4, TestWorldProvider.class, false);
	    DimensionManager.registerDimension(4, 4);
	}

	@EventHandler
	public static void init( FMLInitializationEvent event ) {
		LogHandler.log(Level.INFO, "Initializing");
		
		networkHandler.initialise();
		
		for (IPlugin plugin : plugins) {
			plugin.init();
		}
		
		customItems.init();
	}

	@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) {
		LogHandler.log(Level.INFO, "Post-Initializing");
		
		networkHandler.postInitialise();
		
		for (IPlugin plugin : plugins) {
			plugin.post();
		}
		
		//Register events
		FMLCommonHandler.instance().bus().register(new EventLogin());
		MinecraftForge.EVENT_BUS.register(new EventTooltip());
		MinecraftForge.EVENT_BUS.register(new EventAnvil());
		MinecraftForge.EVENT_BUS.register(new EventHarvest());
		MinecraftForge.EVENT_BUS.register(new EventBucketFill());
		MinecraftForge.EVENT_BUS.register(new EventFog());
		MinecraftForge.ORE_GEN_BUS.register(new CustomGenerator());
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		//World Generation
		customGenerator.init();
		customItems.post();
		
		LogHandler.log(Level.INFO, "CustomCraft Finished");
	}
}