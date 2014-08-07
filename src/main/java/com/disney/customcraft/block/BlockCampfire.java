package com.disney.customcraft.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.disney.customcraft.block.itemblock.ItemBlockCampfire;
import com.disney.customcraft.block.renderer.RendererCampfire;
import com.disney.customcraft.block.tile.TileCampfire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCampfire extends BlockContainer {
		
	public BlockCampfire(String itemName) {
		super(Material.fire);
		
		setBlockName("customcraft." + itemName);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockCampfire.class, getUnlocalizedName());
		
		ClientRegistry.registerTileEntity(TileCampfire.class, getUnlocalizedName() + "Tile", new RendererCampfire());
	}
	
	public boolean isBurning(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileCampfire) {
			return((TileCampfire)tile).isBurning();
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCampfire();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public IIcon getIcon(int side, int meta) {
        return null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(tile instanceof TileCampfire) {
			ItemStack item = entityPlayer.getCurrentEquippedItem();
			if(item != null) {
				if(item.getItem() == Items.stick) {
					boolean useStick = ((TileCampfire) tile).addStick();
					
					if(useStick) {
						item.stackSize--;
						
						if(item.stackSize == 0) {
							entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = null;
						}
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		if(isBurning(world, x, y, z)) {
			return 15;
		}
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(tile instanceof TileCampfire) {
			if(((TileCampfire)tile).isBurning()) {
		        if (rand.nextInt(24) == 0) {
		            world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "fire.fire", 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
		        }
		
		        float xLoc;
		        float yLoc;
		        float zLoc;
		
				for (int i = 0; i < 2; ++i) {
					xLoc = (float) x + rand.nextFloat() * 0.6F;
					yLoc = (float) y + rand.nextFloat() * 0.5F + 0.3F;
					zLoc = (float) z + rand.nextFloat() * 0.6F;
					world.spawnParticle("largesmoke", (double) xLoc, (double) yLoc, (double) zLoc, 0.0D, 0.0D, 0.0D);
				}
			} else if(((TileCampfire)tile).isSmoking()) {
		        if (rand.nextInt(24) == 0) {
		            world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "fire.fire", 0.2F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
		        }
		
		        float xLoc;
		        float yLoc;
		        float zLoc;
		
				for (int i = 0; i < 2; ++i) {
					xLoc = (float) x + rand.nextFloat() * 0.6F;
					yLoc = (float) y + rand.nextFloat() * 0.5F + 0.3F;
					zLoc = (float) z + rand.nextFloat() * 0.6F;
					world.spawnParticle("smoke", (double) xLoc, (double) yLoc, (double) zLoc, 0.0D, 0.0D, 0.0D);
				}
			}
		}
    }

}
