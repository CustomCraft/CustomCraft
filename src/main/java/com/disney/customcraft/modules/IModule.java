package com.disney.customcraft.modules;

public interface IModule {

	/**
	 * FMLPreInitializationEvent : Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry.
	 */
	public void pre();
	
	/**
	 * FMLInitializationEvent : Do your mod setup. Build whatever data structures you care about. 
	 * Register recipes, send FMLInterModComms messages to other mods. 
	 */
	public void init();
	
	/**
	 * FMLPostInitializationEvent : Handle interaction with other mods, complete your setup based on this.
	 */
	public void post();
	
}
