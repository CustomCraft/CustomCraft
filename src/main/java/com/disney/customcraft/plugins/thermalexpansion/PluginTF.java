package com.disney.customcraft.plugins.thermalexpansion;

import com.disney.customcraft.plugins.IPlugin;

import cpw.mods.fml.common.Loader;

public class PluginTF implements IPlugin {

	public static boolean pluginLoaded = false;
	
	@Override
	public void pre() {
		pluginLoaded = Loader.isModLoaded("ThermalFoundation");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void post() {
		// TODO Auto-generated method stub
		
	}

}
