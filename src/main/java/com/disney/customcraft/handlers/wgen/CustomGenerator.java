package com.disney.customcraft.handlers.wgen;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.BlockMultiOre;
import com.disney.customcraft.fluid.FluidMulti;
import com.disney.customcraft.handlers.config.Config;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CustomGenerator implements IWorldGenerator {

	private static Set<EventType> vanillaGen = new HashSet<EventType>();
	
	WorldGenerator copperGen;
	WorldGenerator tinGen;
	
	WorldGenerator ironGen;
	WorldGenerator goldGen;
	WorldGenerator diamondGen;
	WorldGenerator coalGen;
	WorldGenerator redstoneGen;
	
	WorldGenerator oilGen;
	
	/**
	 * Creates the world generator and initialises the ore lists.
	 */
	public void init() {
		if(Config.coreModifyOreRates) {
			vanillaGen.add(OreGenEvent.GenerateMinable.EventType.IRON);
			vanillaGen.add(OreGenEvent.GenerateMinable.EventType.GOLD);
			vanillaGen.add(OreGenEvent.GenerateMinable.EventType.DIAMOND);
			vanillaGen.add(OreGenEvent.GenerateMinable.EventType.COAL);
			vanillaGen.add(OreGenEvent.GenerateMinable.EventType.REDSTONE);
			
			ironGen = new WorldGenMinable(Blocks.iron_ore, 0, 10, Blocks.stone);
			goldGen = new WorldGenMinable(Blocks.iron_ore, 0, 10, Blocks.stone);
			diamondGen = new WorldGenMinable(Blocks.iron_ore, 0, 10, Blocks.stone);
			coalGen = new WorldGenMinable(Blocks.iron_ore, 0, 15, Blocks.stone);
			redstoneGen = new WorldGenMinable(Blocks.iron_ore, 0, 8, Blocks.stone);
		}
		
		if(Config.coreCopper) {
			int meta = BlockMultiOre.getMeta("oreCopper");
			if(meta > -1) copperGen = new WorldGenMinable(CustomItems.oreMulti, meta, 10, Blocks.stone);
		}
		
		if(Config.coreTin) {
			int meta = BlockMultiOre.getMeta("oreTin");
			if(meta > -1) tinGen = new WorldGenMinable(CustomItems.oreMulti, meta, 10, Blocks.stone);
		}
		
		oilGen = new WorldGenOil(CustomItems.fluidOil.block);
		
		register();
    }
	
	private void register() {
		GameRegistry.registerWorldGenerator(this, 1);
	}
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0) {
			if(copperGen != null)
				genOre(copperGen, random, chunkX, chunkZ, world, 24, 0, 96);
			if(tinGen != null)
				genOre(tinGen, random, chunkX, chunkZ, world, 8, 0, 48);
			
			if(ironGen != null)
				genOre(ironGen, random, chunkX, chunkZ, world, 16, 0, 64);
			if(goldGen != null)
				genOre(goldGen, random, chunkX, chunkZ, world, 4, 0, 32);
			if(diamondGen != null)
				genOre(diamondGen, random, chunkX, chunkZ, world, 1, 0, 16);
			if(coalGen != null)
				genOre(coalGen, random, chunkX, chunkZ, world, 16, 0, 128);
			if(redstoneGen != null)
				genOre(redstoneGen, random, chunkX, chunkZ, world, 10, 0, 16);
			
			if(Config.coreFlatBedrock)
				genBedrock(chunkX, chunkZ, world, Blocks.stone);
			
			genOil(oilGen, random, chunkX, chunkZ, world);
		}
		else if(world.provider.dimensionId == -1) {
			if(Config.coreFlatNetherBedrock)
				genBedrock(chunkX, chunkZ, world, Blocks.netherrack);
		}
	}
	
	/**
	 * Generate a block throughout the world.
	 * @param generator The world generator used.
	 * @param random Random generator.
	 * @param chunkX The xLocation of the block generation.
	 * @param chunkZ The zLocation of the block generation.
	 * @param world The world.
	 * @param attempts The number of times to attempt the generation.
	 * @param min The minimum height that the generation can be at.
	 * @param max The maximum height that the generation can be at.
	 */
	protected void genOre(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world, int attempts, int min, int max) {
        for(int i = 0; i < attempts; ++i) {
            int x = (chunkX * 16) + random.nextInt(16);
            int z = (chunkZ * 16) + random.nextInt(16);
            int y = random.nextInt(max - min) + min;
            
            generator.generate(world, random, x, y, z);
        }
    }
	
	protected void genOil(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world) {
		//if(random.nextInt(32) == 0) {
			int x = (chunkX * 16) + random.nextInt(16);
            int z = (chunkZ * 16) + random.nextInt(16);
            int y = world.getHeightValue(x, z);
            
            BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
            if(biome != null && (biome instanceof BiomeGenDesert || biome instanceof BiomeGenBeach)) {
            	generator.generate(world, random, x, y, z);
            }
        //}
	}
	
	/**
	 * Flattens the bedrock layers by changing any bedrock above the bottom layer to the provided block.
	 * @param chunkX The xLocation of the bedrock generation.
	 * @param chunkZ The zLocation of the bedrock generation.
	 * @param world The world.
	 * @param blockId The blockID used to replace any unwanted bedrock (stone or netherrack).
	 */
	public void genBedrock(int chunkX, int chunkZ, World world, Block block) {
		for(int blockX = 0; blockX < 16; blockX++) {
			for(int blockZ = 0; blockZ < 16; blockZ++) {
				for(int blockY = 5; blockY > 0; blockY--) {
					if(world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock) {
						world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, block, 0, 2);
					}
				}
			}
		}
	}

	/**
	 * Used to override the generation of vanilla ores so that we can use our customized values.
	 * @param event An oregen event that is being caught.
	 */
	@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void handleOreGenEvent(OreGenEvent.GenerateMinable event) {
		if (vanillaGen.contains(event.type)) {
			event.setResult(Result.DENY);
		}
	}
	
}