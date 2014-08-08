package com.disney.customcraft.block;

import java.util.List;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.block.itemblock.ItemBlockMultiTable;
import com.disney.customcraft.block.tile.TileBindingTable;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMultiTable extends BlockContainer {
	
	public static final String[] NAMES = { "tableBinding" };
	
	@SideOnly(Side.CLIENT)
	protected IIcon[][] icons = new IIcon[NAMES.length][4];
	
	public BlockMultiTable() {
		super(Material.rock);
		
		setBlockName("customcraft");
		setCreativeTab(CreativeTabs.tabDecorations);
        
		register();
	}
	
	private void register() {
		GameRegistry.registerBlock(this, ItemBlockMultiTable.class, getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TileBindingTable.class, getUnlocalizedName() + "Tile");
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int i = 0; i < NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for(int i = 0; i < NAMES.length; i++) { 
			icons[i][0] = register.registerIcon("customcraft:" + NAMES[i] + "Bot");
			icons[i][1] = register.registerIcon("customcraft:" + NAMES[i] + "Top");
			icons[i][2] = register.registerIcon("customcraft:" + NAMES[i] + "Front");
			icons[i][3] = register.registerIcon("customcraft:" + NAMES[i] + "Side");
    	}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		switch(side) {
			case 0:
				return icons[meta][0];
			case 1:
				return icons[meta][1];
			case 2:
			case 4:	
				return icons[meta][2];
			default:
				return icons[meta][3];
		}
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float par7, float par8, float par9) {
		if(entityPlayer.isSneaking()) {
			return false;
		}
		
		entityPlayer.openGui(CustomCraft.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileBindingTable();
	}
	
}
