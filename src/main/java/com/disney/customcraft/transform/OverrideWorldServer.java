package com.disney.customcraft.transform;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;

import com.disney.customcraft.handlers.config.Config;

public class OverrideWorldServer extends WorldServer {

	/**
     * Runs a single tick for the world
     */
    public void tick()
    {
    	if (Config.nomadLongerDays) {
    		super.tick();

            if (this.getWorldInfo().isHardcoreModeEnabled() && this.difficultySetting != EnumDifficulty.HARD)
            {
                this.difficultySetting = EnumDifficulty.HARD;
            }

            this.provider.worldChunkMgr.cleanupCache();

            if (this.areAllPlayersAsleep())
            {
                if (this.getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
                {
                    long i = this.worldInfo.getWorldTime() + Config.nomadDayLength;
                    this.worldInfo.setWorldTime(i - i % Config.nomadDayLength);
                }

                this.wakeAllPlayers();
            }

            this.theProfiler.startSection("mobSpawner");

            if (this.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
            {
                this.animalSpawner.findChunksForSpawning(this, this.spawnHostileMobs, this.spawnPeacefulMobs, this.worldInfo.getWorldTotalTime() % 400L == 0L);
            }

            this.theProfiler.endStartSection("chunkSource");
            this.chunkProvider.unloadQueuedChunks();
            int j = this.calculateSkylightSubtracted(1.0F);

            if (j != this.skylightSubtracted)
            {
                this.skylightSubtracted = j;
            }

            this.worldInfo.incrementTotalWorldTime(this.worldInfo.getWorldTotalTime() + 1L);

            if (this.getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
            {
                this.worldInfo.setWorldTime(this.worldInfo.getWorldTime() + 1L);
            }

            this.theProfiler.endStartSection("tickPending");
            this.tickUpdates(false);
            this.theProfiler.endStartSection("tickBlocks");
            this.func_147456_g();
            this.theProfiler.endStartSection("chunkMap");
            this.thePlayerManager.updatePlayerInstances();
            this.theProfiler.endStartSection("village");
            this.villageCollectionObj.tick();
            this.villageSiegeObj.tick();
            this.theProfiler.endStartSection("portalForcer");
            this.worldTeleporter.removeStalePortalLocations(this.getTotalWorldTime());
            for (Teleporter tele : customTeleporters)
            {
                tele.removeStalePortalLocations(getTotalWorldTime());
            }
            this.theProfiler.endSection();
            this.func_147488_Z();
    	} else {
    		super.tick();

            if (this.getWorldInfo().isHardcoreModeEnabled() && this.difficultySetting != EnumDifficulty.HARD)
            {
                this.difficultySetting = EnumDifficulty.HARD;
            }

            this.provider.worldChunkMgr.cleanupCache();

            if (this.areAllPlayersAsleep())
            {
                if (this.getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
                {
                    long i = this.worldInfo.getWorldTime() + 24000L;
                    this.worldInfo.setWorldTime(i - i % 24000L);
                }

                this.wakeAllPlayers();
            }

            this.theProfiler.startSection("mobSpawner");

            if (this.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
            {
                this.animalSpawner.findChunksForSpawning(this, this.spawnHostileMobs, this.spawnPeacefulMobs, this.worldInfo.getWorldTotalTime() % 400L == 0L);
            }

            this.theProfiler.endStartSection("chunkSource");
            this.chunkProvider.unloadQueuedChunks();
            int j = this.calculateSkylightSubtracted(1.0F);

            if (j != this.skylightSubtracted)
            {
                this.skylightSubtracted = j;
            }

            this.worldInfo.incrementTotalWorldTime(this.worldInfo.getWorldTotalTime() + 1L);

            if (this.getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
            {
                this.worldInfo.setWorldTime(this.worldInfo.getWorldTime() + 1L);
            }

            this.theProfiler.endStartSection("tickPending");
            this.tickUpdates(false);
            this.theProfiler.endStartSection("tickBlocks");
            this.func_147456_g();
            this.theProfiler.endStartSection("chunkMap");
            this.thePlayerManager.updatePlayerInstances();
            this.theProfiler.endStartSection("village");
            this.villageCollectionObj.tick();
            this.villageSiegeObj.tick();
            this.theProfiler.endStartSection("portalForcer");
            this.worldTeleporter.removeStalePortalLocations(this.getTotalWorldTime());
            for (Teleporter tele : customTeleporters)
            {
                tele.removeStalePortalLocations(getTotalWorldTime());
            }
            this.theProfiler.endSection();
            this.func_147488_Z();
    	}
    }
	
    //region UNUSED
    public OverrideWorldServer(MinecraftServer p_i45284_1_,
			ISaveHandler p_i45284_2_, String p_i45284_3_, int p_i45284_4_,
			WorldSettings p_i45284_5_, Profiler p_i45284_6_) {
		super(p_i45284_1_, p_i45284_2_, p_i45284_3_, p_i45284_4_, p_i45284_5_,
				p_i45284_6_);
	}
    //end
}
