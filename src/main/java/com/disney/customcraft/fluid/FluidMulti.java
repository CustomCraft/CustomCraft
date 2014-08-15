package com.disney.customcraft.fluid;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import com.disney.customcraft.block.BlockCustomFluid;
import com.disney.customcraft.event.EventBucketFill;
import com.disney.customcraft.item.ItemCustomBucket;
import com.disney.customcraft.materials.FluidMaterial;

public class FluidMulti {
	
	public Fluid fluid;
	public BlockCustomFluid block;
	public ItemCustomBucket bucket;
	
	public FluidMulti(String name, FluidMaterial material) {
		fluid = new Fluid(name);
		fluid.setDensity(material.getDensity());
		fluid.setViscosity(material.getViscosity());
		fluid.setLuminosity(material.getLuminosity());
		FluidRegistry.registerFluid(fluid);
		
		block = new BlockCustomFluid(fluid, material);
		block.setQuantaPerBlock(material.getQuanta());
		
		bucket = new ItemCustomBucket(fluid, block);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucket), new ItemStack(Items.bucket));
		EventBucketFill.buckets.put(block, bucket);
	}

}
