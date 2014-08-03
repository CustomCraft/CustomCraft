package com.disney.customcraft.handlers.event;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.api.RegistryNEI;
import com.disney.customcraft.api.RegistryNEI.NEISet;
import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.network.packet.PacketSettings;
import com.disney.customcraft.modules.core.ModuleCore;
import com.disney.customcraft.plugins.nei.PluginNEI;
import com.disney.customcraft.plugins.nei.PluginNEI.CustomSubsetWidget;

import codechicken.nei.ClientHandler;
import codechicken.nei.ItemList;
import codechicken.nei.ItemPanel;
import codechicken.nei.ItemStackMap;
import codechicken.nei.ItemStackSet;
import codechicken.nei.LayoutManager;
import codechicken.nei.SubsetWidget;
import codechicken.nei.SubsetWidget.SubsetTag;
import codechicken.nei.api.API;
import codechicken.nei.api.ItemFilter;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;


public class EventNEI {

	public static boolean initItemPanel = false;
	private CustomSubsetWidget customSubset;
	
	@SubscribeEvent
	public void handleLoginEvent(TickEvent.ClientTickEvent event) {
		if(SubsetWidget.getTag("Mod") != null) {
			customSubset = new CustomSubsetWidget();
			
			SubsetWidget.unhideAll();
			customSubset.unhideAll();
			
			LayoutManager.dropDown = customSubset;
			
			customSubset.getRoot().children.clear();

			for(NEISet neiSet : RegistryNEI.getNEIList()) {
				customSubset.addTag(new SubsetTag(neiSet.getName(), neiSet.getList()));
			}
			
			ItemFilter allFilter = new ItemFilter() {
				@Override
	            public boolean matches(ItemStack item) {
					for(NEISet neiSet : RegistryNEI.getNEIList()) {
						for(ItemStack itemStack : neiSet.getList().values()) {
							if(ItemStackMap.isWildcard(itemStack.getItemDamage())) {
								if(item.getItem() == itemStack.getItem()) {
									return false;
								}
							}else {
								if(item.isItemEqual(itemStack)) {
									return false;
								}
							}
						}
					}
	                return true;
	            }
			};
			customSubset.addTag(new SubsetTag("All", allFilter));
			
			customSubset.update();
		}
		
		if(!initItemPanel && !ItemPanel.items.isEmpty()) {
			for(ItemStack item : ItemList.items) {
				customSubset.setHidden(item, true);
			}
			
			initItemPanel = true;
		}
	}
	
}
