package com.disney.customcraft.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventLiving {
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayerMP) {
			this.onPlayerUpdate((EntityPlayerMP) event.entityLiving);
			event.entityLiving.setDead();
		}
	}
	
	private void onPlayerUpdate(EntityPlayerMP player) {
		System.out.println(player.fallDistance);
		//player.fallDistance = 0.0F;
		//player.motionY = 0.0F;
		//player.posY = 100.0F; 

		if (player.onGround) {
			//player.setUsingParachute(false);
		}
	}

}
