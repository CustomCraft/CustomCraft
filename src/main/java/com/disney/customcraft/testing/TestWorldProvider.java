package com.disney.customcraft.testing;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.ChunkProviderFlat;

public class TestWorldProvider extends WorldProviderSurface {

	@Override
	public String getDimensionName() {
		return "TestWorld";
	}

}
