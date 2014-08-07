package com.disney.customcraft.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.disney.customcraft.handlers.ItemHelper;
import com.disney.customcraft.materials.FluidMaterial;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustomFluid extends BlockFluidClassic {
		
	@SideOnly(Side.CLIENT) protected IIcon stillIcon;
    @SideOnly(Side.CLIENT) protected IIcon flowingIcon;
	
	public BlockCustomFluid(Fluid fluid, FluidMaterial material) {
		super(fluid, material.getMaterial());
		
		setBlockName("customcraft." + fluid.getName());
		
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, getUnlocalizedName());
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
   
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
    	stillIcon = register.registerIcon(ItemHelper.getIconLoc(getUnlocalizedName()) + "Still");
    	flowingIcon = register.registerIcon(ItemHelper.getIconLoc(getUnlocalizedName()) + "Flowing");
    }
   
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.displaceIfPossible(world, x, y, z);
	}

}
