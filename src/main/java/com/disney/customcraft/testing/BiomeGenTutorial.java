package com.disney.customcraft.testing;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenTutorial extends BiomeGenBase {
	
	public final Material blockMaterial;

	public BiomeGenTutorial(int par1) {
		super(par1);
		this.blockMaterial = Material.water;
		setHeight(height_HighPlateaus);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.obsidian;
		this.fillerBlock = Blocks.obsidian;
		this.setBiomeName("Tutorial");

		/**
		 * this changes the water colour, its set to red now but ggole the java
		 * colours
		 **/
		this.waterColorMultiplier = 0xE42D17;
	}
}
