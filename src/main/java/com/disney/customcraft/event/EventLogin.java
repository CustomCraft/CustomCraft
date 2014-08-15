package com.disney.customcraft.event;

import net.minecraft.entity.player.EntityPlayerMP;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.network.packet.PacketSettings;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventLogin {
	
	@SubscribeEvent
	public void handleLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
		LogHandler.log("Player : " + event.player.getDisplayName() + " logged in");
	
		PacketSettings packet = new PacketSettings();
		packet.enabledDayLength = Config.nomadLongerDays;
		packet.valueDayLength = Config.nomadDayLength;
		packet.enabledAdvDarkness = Config.nomadDarkerNights;
		packet.valueMoonlight = Config.nomadMoonlight;
		packet.valueGamma = Config.nomadGamma;
		
		CustomCraft.networkHandler.sendTo(packet, (EntityPlayerMP) event.player);
		
		//update NEI plugin login variables
		EventNEI.initItemPanel = false;
	}

}
