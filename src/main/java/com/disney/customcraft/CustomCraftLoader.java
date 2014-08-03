package com.disney.customcraft;

import java.io.File;
import java.util.Map;

import com.disney.customcraft.transform.CustomClassTransformer;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CustomCraftLoader implements IFMLLoadingPlugin
{
	public static boolean obfuscated;
	public static File location;
	
	@Override
	public String[] getASMTransformerClass() {
		CustomClassTransformer.init();
		
		return new String[] { CustomClassTransformer.class.getName() };
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		obfuscated = (Boolean) data.get("runtimeDeobfuscationEnabled");
		location = (File)data.get("coremodLocation");
	}
}