package com.disney.customcraft.handlers.wgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOil extends WorldGenerator {
	
	private Block block;
	
	public WorldGenOil(Block block) {
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		//check that the block below is sand
		if(world.getBlock(x, y - 1, z) == Blocks.sand) {
			//build the oil up 5 spaces
			for(int i = 0; i < 5; i++) {
				if(world.getBlock(x, y + i, z) == Blocks.air) {
					world.setBlock(x, y + i, z, block, 0, 2);
				}
			}
			
			//build the oil down through any sand
			int offset = 1;
			while(world.getBlock(x, y - offset, z) == Blocks.sand) {
				world.setBlock(x, y - offset, z, block, 0, 2);
				offset++;
			}
			
			return true;
		}
		
		return false;
	}
	
	

}
