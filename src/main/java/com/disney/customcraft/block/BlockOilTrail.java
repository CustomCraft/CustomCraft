package com.disney.customcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.itemblock.ItemBlockTorchOn;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOilTrail extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon field_150182_M;
    @SideOnly(Side.CLIENT)
    private IIcon field_150183_N;
    @SideOnly(Side.CLIENT)
    private IIcon field_150184_O;
    @SideOnly(Side.CLIENT)
    private IIcon field_150180_P;
    private static final String __OBFID = "CL_00000295";
	
	public BlockOilTrail() {
        super(Material.ground);

        setBlockName("customcraft.oiltrail");
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        
        register();
    }
	
	private void register() {
		GameRegistry.registerBlock(this, getUnlocalizedName());
		
		CustomCraft.proxy.registerSimpleRenderer(this);
	}
	
	public boolean isBurning(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
    	if(meta > 10) {
    		return true;
    	}
    	return false;
	}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
    	return CustomCraft.proxy.getRendererID(this);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemBlockTorchOn) {
    		if(world.getBlockMetadata(x, y, z) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, 15, 3);
				world.scheduleBlockUpdate(x, y, z, this, 10);
			}
    		return true;
    	}
    	return false;
    }
    
    @Override
    public int tickRate(World p_149738_1_) {
    	return super.tickRate(p_149738_1_);
    }
   
    public void updateTick(World world, int x, int y, int z, Random random) {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if(meta > 0) {
    		world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
    		
    		if(meta > 10) {
	    		burnBlock(world, x - 1, y, z);
	    		burnBlock(world, x + 1, y, z);
	    		burnBlock(world, x, y, z - 1);
	    		burnBlock(world, x, y, z + 1);
    		}
    		
    		world.scheduleBlockUpdate(x, y, z, this, 10);
    	}
    }
	
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return World.doesBlockHaveSolidTopSurface(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_) || p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_) == Blocks.glowstone;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    public static boolean isConnectable(IBlockAccess blockAccess, int x, int y, int z) {
        Block block = blockAccess.getBlock(x, y, z);
        
        if (block == CustomItems.oilTrail) {
        	return true;
        }
        else if (block.getFlammability(blockAccess, x, y, z, ForgeDirection.UNKNOWN) > 0) {
        	return true;
        }
        
        return false;
    }
    
    public void burnBlock(World world, int x, int y, int z) {
    	Block block = world.getBlock(x, y, z);
    	
    	if (block == CustomItems.oilTrail) {
    		if(world.getBlockMetadata(x, y, z) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, 15, 2);
				world.scheduleBlockUpdate(x, y, z, this, 10);
			}
        }
        else if (block.getFlammability(world, x, y, z, ForgeDirection.UNKNOWN) > 0) {
        	world.setBlock(x, y, z, Blocks.fire);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
        int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);

        if (l > 0)
        {
            double d0 = (double)p_149734_2_ + 0.5D + ((double)p_149734_5_.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)p_149734_3_ + 0.0625F);
            double d2 = (double)p_149734_4_ + 0.5D + ((double)p_149734_5_.nextFloat() - 0.5D) * 0.2D;
            float f = (float)l / 15.0F;
            float f1 = f * 0.6F + 0.4F;

            if (l == 0)
            {
                f1 = 0.0F;
            }

            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f3 < 0.0F)
            {
                f3 = 0.0F;
            }

            p_149734_1_.spawnParticle("reddust", d0, d1, d2, (double)f1, (double)f2, (double)f3);
        }
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        
    }
}
