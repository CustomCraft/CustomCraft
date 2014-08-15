package com.disney.customcraft.event;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHarvest {
	
	@SubscribeEvent
	public void handleHarvest(HarvestCheck event) {
		if(event.entityPlayer != null && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() == Items.stick) {
			if(event.block == Blocks.stone || event.block == Blocks.cobblestone) {
				event.success = true;
			}
		}
	}

}
