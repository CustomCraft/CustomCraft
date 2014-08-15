package com.disney.customcraft.event;

import org.lwjgl.input.Keyboard;

import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventTooltip {
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (event.entityPlayer == null) return;
		
		Item item = event.itemStack.getItem();
		if (item instanceof ITool) {
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				NBTTagCompound tags = event.itemStack.getTagCompound().getCompoundTag("cctool");
				if (tags != null) {
					int val;
					
					String name = event.toolTip.get(0);
					event.toolTip.clear();
					event.toolTip.add(name);
					
					event.toolTip.add("Head Material: " + EnumChatFormatting.WHITE + RegistryTools.MATERIALS_HEAD.get(tags.getInteger("head")).name);
					event.toolTip.add("Shaft Material: " + EnumChatFormatting.WHITE + RegistryTools.MATERIALS_SHAFT.get(tags.getInteger("shaft")).name);

					event.toolTip.add("Durability: " + EnumChatFormatting.WHITE + tags.getInteger("dura") + "/" + tags.getInteger("maxdura"));
					
					val = tags.getInteger("damage");
					event.toolTip.add(StatCollector.translateToLocalFormatted("tooltip.damage", EnumChatFormatting.WHITE + "" + val ));
					//if(val > 1) event.toolTip.add("Damage: " + EnumChatFormatting.WHITE + val);
					
					val = tags.getInteger("enchant");
					if(val > 0) event.toolTip.add("Enchantability: " + EnumChatFormatting.WHITE + val);
					
					val = tags.getInteger("harvest");
					if(val > 0) event.toolTip.add("Harvest Level: " + EnumChatFormatting.WHITE + val);
					
					val = tags.getInteger("speed");
					if(val > 1) event.toolTip.add("Harvest Speed: " + EnumChatFormatting.WHITE + val);
					
		            NBTTagList enchantList = event.itemStack.getEnchantmentTagList();
		            if(enchantList != null && enchantList.tagCount() > 0) {
		            	event.toolTip.add("");
		                for(int i = 0; i < enchantList.tagCount(); ++i) {
		                    short short1 = enchantList.getCompoundTagAt(i).getShort("id");
		                    short short2 = enchantList.getCompoundTagAt(i).getShort("lvl");

		                    if (Enchantment.enchantmentsList[short1] != null) {
		                        event.toolTip.add(EnumChatFormatting.AQUA + Enchantment.enchantmentsList[short1].getTranslatedName(short2));
		                    }
		                }
		            }
				}
			} else {
				String name = event.toolTip.get(0);
				event.toolTip.clear();
				event.toolTip.add(name);
				
				event.toolTip.add(StatCollector.translateToLocalFormatted("tooltip.info", EnumChatFormatting.GREEN.toString() + EnumChatFormatting.ITALIC + "Shift" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY));
			}
		}
	}

}
