package com.disney.customcraft.testcasting;

import com.disney.customcraft.CustomItems;
import com.disney.customcraft.block.itemblock.ItemBlockCampfire;
import com.disney.customcraft.block.renderer.RendererCampfire;
import com.disney.customcraft.block.tile.TileCampfire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockCastingTable extends BlockContainer {
	
	public BlockCastingTable(String itemName) {
		super(Material.iron);
		
		setBlockName("customcraft." + itemName);
		//setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockCastingTable.class, getUnlocalizedName());
		
		ClientRegistry.registerTileEntity(TileCastingTable.class, getUnlocalizedName() + "Tile", new RendererCastingTable());
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCastingTable();
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
		
		if(tile instanceof TileCastingTable) {
			TileCastingTable castingTable = (TileCastingTable) tile;
			
			if(castingTable.isOutput()) {
				spawnItemAtEntity(entityPlayer, new ItemStack(castingTable.getOutput()), 10);
				castingTable.removeOutput();
			} else {
				ItemStack item = entityPlayer.getCurrentEquippedItem();
				if(item != null) {
					if(item.getItem() == Items.water_bucket) {
						boolean useBucket = castingTable.addFluid(FluidRegistry.WATER);
						
						if(useBucket) {
							entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = new ItemStack(Items.bucket);
						}
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static void spawnItemAtEntity (Entity entity, ItemStack stack, int delay) {
        if (!entity.worldObj.isRemote) {
            EntityItem entityitem = new EntityItem(entity.worldObj, entity.posX + 0.5D, entity.posY + 0.5D, entity.posZ + 0.5D, stack);
            entityitem.delayBeforeCanPickup = delay;
            entity.worldObj.spawnEntityInWorld(entityitem);
        }
    }
	
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 15;
	}
	
}
