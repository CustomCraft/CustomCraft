package com.disney.customcraft.plugins.nei;

import codechicken.nei.ItemStackSet;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.SubsetWidget;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.event.EventNEI;
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
	
	private void configVanilla() {
		
	}
	
	private void configCustomCraft() {
		ItemStackSet items;
		
		items = new ItemStackSet();
		items.with(CustomItems.oreMulti);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Ores", items));
		items.clear();
		
		items = new ItemStackSet();
		items.with(CustomItems.storageMulti);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Storage", items));
		items.clear();
		
		items = new ItemStackSet();
		items.with(CustomItems.ingotMulti);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Materials", items));
		items.clear();
		
		items = new ItemStackSet();
		items.with(CustomItems.dustMulti);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Gatherables.Materials.Dusts", items));
		items.clear();
		
		items = new ItemStackSet();
		items.with(CustomItems.clubStone);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Weapons", items));
		items.clear();
		
		items = new ItemStackSet();
		items.with(CustomItems.clubStone);
		if(!items.isEmpty()) RegistryNEI.addNEISet(new NEISet("Equipment.Tools", items));
		items.clear();
	}
}
