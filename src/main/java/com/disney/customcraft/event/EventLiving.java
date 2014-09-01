package com.disney.customcraft.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class EventLiving {
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayerMP) {
			this.onPlayerUpdate((EntityPlayerMP) event.entityLiving);
			event.entityLiving.setDead();
		}
	}
	
	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event) {
		//event.distance *= -0.2F;
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		//event.player.motionX *= 0.2F;
		//event.player.motionY *= -1.8F;
		//event.player.motionY += -0.1F;
		//event.player.motionZ *= 0.2F;
		if(!event.player.capabilities.isFlying) {
			event.player.motionY += 0.036F;
		}
		
		if(event.side == Side.SERVER) {
			//System.out.println(event.player.motionY);
		}
	}
	
	private void onPlayerUpdate(EntityPlayerMP player) {
		//System.out.println(player.motionY);
		//player.fallDistance = 0.0F;
		//player.motionY = 0.0F;
		//player.posY = 100.0F; 
		
		//player.motionY = -0.0D;
		//player.motionY *= -0.1F;
		//player.motionX *= 0.1F;
		//player.motionZ *= 0.1F;
		
		//player.posX = player.posX + 10.0F;
		
		//System.out.println(player.motionY);

		if (player.onGround) {
			//player.setUsingParachute(false);
		}
	}

}
