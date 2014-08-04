package com.disney.customcraft.modules.nomad.block.tile;

import com.disney.customcraft.modules.nomad.ModuleNomad;
import com.disney.customcraft.modules.nomad.block.BlockTorchOn;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

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
				int meta = block.getDamageValue(worldObj, xCoord, yCoord, zCoord);
				if(worldObj.setBlock(xCoord, yCoord, zCoord, ModuleNomad.blockTorchOff[((BlockTorchOn)block).material.getMeta()])) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
				}
			}
		}
		currentDura++;
	}

}
