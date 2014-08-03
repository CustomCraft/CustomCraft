package com.disney.customcraft.api;

import java.util.HashSet;
import java.util.Set;

import com.disney.customcraft.plugins.nei.PluginNEI;

import codechicken.nei.ItemStackSet;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;

public class RegistryNEI {
	
	private static Set<NEISet> neiList = new HashSet<NEISet>();
	
	public static Set<NEISet> getNEIList() {
		return neiList;
	}
	
	public static void addNEISet(NEISet neiSet) {
		if(PluginNEI.pluginLoaded) {
			//first check if the set already exists
			for(NEISet existingSet : neiList) {
				if(existingSet.name.equals(neiSet.name)) {
					for(ItemStack item : neiSet.items.values()) {
						existingSet.addToList(item);
					}
					
					return;
				}
			}
			
			//if not add a new set
			neiList.add(neiSet);
		}
	}
	
	public static class NEISet {
		private String name = "";
		private ItemStackSet items = new ItemStackSet();
		
		public NEISet(String name, ItemStackSet items) {
			this.name = name;
			
			for(ItemStack item : items.values()) {
				addToList(item);
			}
		}
		
		public String getName() {
			return name;
		}
		
		public ItemStackSet getList() {
			return items;
		}
		
		public void addToList(ItemStack item) {
			this.items.add(item);
		}
	}

}
