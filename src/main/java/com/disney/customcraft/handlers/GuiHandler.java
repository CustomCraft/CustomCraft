package com.disney.customcraft.handlers;

import com.disney.customcraft.block.container.ContainerBindingTable;
import com.disney.customcraft.block.gui.GuiBindingTable;
import com.disney.customcraft.block.tile.TileBindingTable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if(tileEntity instanceof TileBindingTable) {
			return new ContainerBindingTable(player.inventory, (TileBindingTable) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity instanceof TileBindingTable) {
			return new GuiBindingTable(player.inventory, (TileBindingTable) tileEntity);
		}
		return null;
	}

}
