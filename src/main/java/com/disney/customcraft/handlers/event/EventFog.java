package com.disney.customcraft.handlers.event;

import net.minecraftforge.client.event.EntityViewRenderEvent;

import com.disney.customcraft.CustomItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventFog {
	
	@SubscribeEvent
	public void onFogEvent(EntityViewRenderEvent.FogColors event) {
		if(event.block == CustomItems.fluidOil.block) {
            event.red = 0.9F;
            event.green = 0.9F;
            event.blue = 0.9F;
        }
	}

}
