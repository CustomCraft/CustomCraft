package com.disney.customcraft.block.tile;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import com.disney.customcraft.block.BlockTorchOn;
import com.disney.customcraft.handlers.ItemHelper;

public class TileTorch extends TileEntity {
	
	public int maxDura = 0;
	public int currentDura = 0;
	
	public TileTorch(int maxDura) {
		this.maxDura = maxDura;
	}
	
	@Override
	public void updateEntity() {
		if(currentDura >= maxDura) {
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(block instanceof BlockTorchOn) {
				Block torchOff = ItemHelper.getTorchOff((BlockTorchOn)block);
				if(torchOff != null) {
					if(worldObj.setBlock(xCoord, yCoord, zCoord, torchOff)) {
						worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, block.getDamageValue(worldObj, xCoord, yCoord, zCoord), 2);
					}
				}
			}
		}
		currentDura++;
	}

}
