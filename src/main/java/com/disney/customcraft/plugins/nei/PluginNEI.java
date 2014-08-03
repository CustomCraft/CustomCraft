package com.disney.customcraft.plugins.nei;

import net.minecraft.item.ItemStack;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.SubsetWidget;
import codechicken.nei.SubsetWidget.SubsetTag;

import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.event.EventLogin;
import com.disney.customcraft.handlers.event.EventNEI;
import com.disney.customcraft.modules.core.ModuleCore;
import com.disney.customcraft.plugins.IPlugin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class PluginNEI implements IPlugin {
	
	public static boolean pluginLoaded = false;
	
	@Override
	public void pre() {
		pluginLoaded = Loader.isModLoaded("NotEnoughItems");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void post() {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			FMLCommonHandler.instance().bus().register(new EventNEI());
		}
	}

	public static class CustomSubsetWidget extends SubsetWidget {
		
		public CustomSubsetWidget() {
			super();
		}
		
		public SubsetTag getRoot() {
			return root;
		}
		
		private long lastclicktime;
		private short lastclickButton;
		
		@Override
	    public boolean handleClick(int mx, int my, int button) {
			if(root.isVisible() && root.contains(mx, my)) {
	            root.mouseClicked(mx, my, button);
	            return true;
	        }

	        if(button == 0) {
	            if(lastclickButton == 0 && System.currentTimeMillis() - lastclicktime < 500) {
	            	for(SubsetTag tag : root.children.values()) {
	            		setHidden(tag, false);
	            	}
	            }
	            else
	            	root.setVisible();

	            NEIClientUtils.playClickSound();
	            lastclickButton = 0;
	            lastclicktime = System.currentTimeMillis();
	        }
	        else if(button == 1) {
	        	if(lastclickButton == 1 && System.currentTimeMillis() - lastclicktime < 500) {
	        		for(SubsetTag tag : root.children.values()) {
	            		setHidden(tag, true);
	            	}
	            }
	            else
	            	root.setHidden();
	        	
	        	NEIClientUtils.playClickSound();
	        	lastclickButton = 1;
	            lastclicktime = System.currentTimeMillis();
	        }

	        return true;
	    }
	}
}
