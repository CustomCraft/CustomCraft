package com.disney.customcraft.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.disney.customcraft.api.RegistryTorch;
import com.disney.customcraft.block.BlockCampfire;
import com.disney.customcraft.block.BlockOilTrail;
import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;
import com.disney.customcraft.block.tile.TileTorch;

public class ItemBlockTorchOff extends ItemBlock {
		
	public ItemBlockTorchOff(Block block) {
		super(block);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		Block block = world.getBlock(x, y, z);
		
		if(block instanceof BlockCampfire) {
			if(((BlockCampfire)block).isBurning(world, x, y, z)) { 
				Block torchOn = RegistryTorch.getTorchOn((BlockTorchOff)field_150939_a);
				if(torchOn != null) {
		        	ItemStack itemStack = new ItemStack(torchOn);
					NBTTagCompound tags = new NBTTagCompound();
					tags.setInteger("dura", 0);
					itemStack.setTagCompound(tags);
		        	
					MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
					player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
					
					return true;
				}
			}
        }
		else if(block instanceof BlockFire) {
			Block torchOn = RegistryTorch.getTorchOn((BlockTorchOff)field_150939_a);
			if(torchOn != null) {
	        	ItemStack itemStack = new ItemStack(torchOn);
				NBTTagCompound tags = new NBTTagCompound();
				tags.setInteger("dura", 0);
				itemStack.setTagCompound(tags);
	        	
				MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
				player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
				
				return true;
			}
		}
		else if(block instanceof BlockTorchOn) {
			Block torchOn = RegistryTorch.getTorchOn((BlockTorchOff)field_150939_a);
			if(torchOn != null) {
				ItemStack itemStack = new ItemStack(torchOn);
				NBTTagCompound tags = new NBTTagCompound();
				
				TileEntity tile = world.getTileEntity(x, y, z);
				if(tile instanceof TileTorch) {
					int dura = ((TileTorch)tile).maxDura - ((BlockTorchOff)block).getTorchMaterial().getMaxDura() + ((TileTorch)tile).currentDura;
					if(dura < 0) dura = 0;
					tags.setInteger("dura", dura);
				} else {
					tags.setInteger("dura", 0);	
				}
				itemStack.setTagCompound(tags);
	        	
				MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
				player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
				
				return true;
			}
		}
		else if(block instanceof BlockOilTrail) {
			if(((BlockOilTrail)block).isBurning(world, x, y, z)) { 
				Block torchOn = RegistryTorch.getTorchOn((BlockTorchOff)field_150939_a);
				if(torchOn != null) {
		        	ItemStack itemStack = new ItemStack(torchOn);
					NBTTagCompound tags = new NBTTagCompound();
					tags.setInteger("dura", 0);
					itemStack.setTagCompound(tags);
		        	
					MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
					player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
					
					return true;
				}
			}
        }
		return super.onItemUse(item, player, world, x, y, z, par7, par8, par9, par10);
	}
	
}
