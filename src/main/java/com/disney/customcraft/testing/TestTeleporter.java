package com.disney.customcraft.testing;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TestTeleporter extends Teleporter {

	public TestTeleporter(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
	}
	
	@Override
	public void placeInPortal(Entity p_77185_1_, double p_77185_2_,	double p_77185_4_, double p_77185_6_, float p_77185_8_) {
		//don't do anything!
		p_77185_1_.setLocationAndAngles(p_77185_2_, p_77185_4_ + 100.0, p_77185_6_, p_77185_1_.rotationYaw, p_77185_1_.rotationPitch);
	}

}
