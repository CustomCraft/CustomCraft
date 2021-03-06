package com.disney.customcraft.handlers;

import com.disney.customcraft.testingMachine.te.ContainerElectricTE;
import com.disney.customcraft.testingMachine.te.GuiElectricTE;
import com.disney.customcraft.testingMachine.te.TileElectricTE;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileElectricTE) {
	    	return new ContainerElectricTE(player.inventory, (TileElectricTE) tileEntity);
	    }
		
		/*TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if(tileEntity instanceof TileBindingTable) {
			return new ContainerBindingTable(player.inventory, (TileBindingTable) tileEntity);
		}*/
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileElectricTE) {
	    	return new GuiElectricTE(player.inventory, (TileElectricTE) tileEntity);
	    }
		
		/*TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity instanceof TileBindingTable) {
			return new GuiBindingTable(player.inventory, (TileBindingTable) tileEntity);
		}*/
		return null;
	}

}
