package com.disney.customcraft.testingMachine;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.ModInfo;
import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.testingMachine.te.TileElectricTE;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElectricAbstract extends BlockContainer {
	
	public static final String[] NAMES = { "furnace" };
	
	protected IIcon[][] icons = new IIcon[1][2];
	protected IIcon[][] blockIcons = new IIcon[NAMES.length][2];

	protected int facing = 3;
	
	public BlockElectricAbstract() {
		super(Material.iron);
		
		setBlockName("machine.electric");
		setCreativeTab(CreativeTabs.tabDecorations);
		
		setHardness(5.0F);
        
		register();
	}
	
	protected void register() {
		GameRegistry.registerBlock(this, ItemBlockElectricAbstract.class, getUnlocalizedName());
		
		GameRegistry.registerTileEntity(TileElectricTE.class, "ElectricFurnaceTileEntity");
		//GameRegistry.registerTileEntity(TileElectricFurnace.class, "ElectricFurnaceTileEntity");
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
		if(entityPlayer.isSneaking()) {
			return false;
		}
		
		entityPlayer.openGui(CustomCraft.instance, 0, world, x, y, z);
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		switch(side) {
			case 1: case 2: 					//bot or top
				return icons[0][0];
			case 3:								//front
				return blockIcons[meta][1];
			default:							//sides
				return icons[0][1];
		}
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
    	TileEntity tileEntity = world.getTileEntity(x, y, z);
    	
		if(side < 2) {
			return icons[0][0];
		}
		else if(side == facing) {
			return blockIcons[meta][((TileElectricTE)tileEntity).isPowered() ? 0 : 1];
		}
		else {
			return icons[0][1];
		}
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
    	icons[0][0] = register.registerIcon("customcraft:machineElectricTop");
		icons[0][1] = register.registerIcon("customcraft:machineElectricSide");
    	
    	for(int i = 0; i < NAMES.length; i++) {
    		blockIcons[i][0] = register.registerIcon("customcraft:" + NAMES[i] + "Electric_on");
    		blockIcons[i][1] = register.registerIcon("customcraft:" + NAMES[i] + "Electric_off");
    	}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		try {
			switch(meta) {
				case 0:
					return TileElectricTE.class.newInstance();
				default:
					return null;
			}
	    } catch(Exception e) {
	    	LogHandler.log(Level.ERROR, "ElectricFactory : Unable to create tile entity");
	    	return null;
	    }
	}

}
