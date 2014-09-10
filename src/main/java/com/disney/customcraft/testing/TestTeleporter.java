package com.disney.customcraft.testing;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TestTeleporter extends Teleporter {

	WorldServer world;
	
	public TestTeleporter(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
		
		this.world = p_i1963_1_;
	}
	
	@Override
	public void placeInPortal(Entity p_77185_1_, double p_77185_2_,	double p_77185_4_, double p_77185_6_, float p_77185_8_) {
		
		int height = world.getHeightValue((int) p_77185_2_, (int)p_77185_6_);
		
		BlockChest chestBlock = Blocks.chest;
		
		world.setBlock((int) p_77185_2_, height, (int)p_77185_6_, Blocks.chest);

		TileEntity tile = world.getTileEntity((int) p_77185_2_, height, (int)p_77185_6_);
		if(tile != null && tile instanceof TileEntityChest) {
			((TileEntityChest)tile).setInventorySlotContents(0, new ItemStack(Items.apple));
		}
		
		//don't do anything!
		p_77185_1_.setLocationAndAngles(p_77185_2_, height + 1, p_77185_6_, p_77185_1_.rotationYaw, p_77185_1_.rotationPitch);

		/*EntityTest newRocket = new EntityTest(world);
		newRocket.setLocationAndAngles(p_77185_2_ + 2, p_77185_4_, p_77185_6_, p_77185_1_.rotationYaw, p_77185_1_.rotationPitch);
		world.spawnEntityInWorld(newRocket);
		world.updateEntityWithOptionalForce(newRocket, true);*/
		
		
	}

}
