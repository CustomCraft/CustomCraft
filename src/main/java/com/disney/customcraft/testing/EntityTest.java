package com.disney.customcraft.testing;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityTest extends Entity {

	public EntityTest(World world) {
		super(world);
	}
	
	public EntityTest(World world, double x, double y, double z) {
		super(world);
		
		setPosition(x, y, z);
		setSize(0.98F, 4F);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub
		
	}
	
	public AxisAlignedBB getBoundingBox()
    {
		return boundingBox;
    }

}
