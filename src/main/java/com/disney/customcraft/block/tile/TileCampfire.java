package com.disney.customcraft.block.tile;

import net.minecraft.tileentity.TileEntity;

public class TileCampfire extends TileEntity {
	
	public int numStick = 0;
	public int countStick = -1;
	public int dura = -1;
	
	@Override
	public void updateEntity() {
		if(countStick > 0) {
			countStick--;
		} else if(countStick == 0) {
			numStick = 8;
			countStick = -1;
		}
		
		if(dura > 0) {
			dura--;
		} else if(dura == 0) {
			numStick = 0;
			dura = -1;
		} 
	}
	
	public boolean addStick() {
		if(!isBurning()) {
			if(numStick < 8) {
				numStick++;
				return true;
			} else if(numStick < 12) {
				countStick = 100;
				numStick++;
			} else if(numStick == 12) {
				countStick = -1;
				dura = 5000;
			}
		}
		return false;
	}
	
	public void setBurning() {
		if(!isBurning()) {
			if(numStick >= 8) {
				countStick = -1;
				dura = 5000;
			}
		}
	}
	
	public boolean isBurning() {
		return dura > 0;
	}
	
	public boolean isSmoking() {
		return countStick > 0;
	}
	
	public int getNumSticks() {
		return Math.min(numStick, 8);
	}

}
