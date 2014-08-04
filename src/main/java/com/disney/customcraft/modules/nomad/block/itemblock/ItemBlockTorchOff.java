package com.disney.customcraft.modules.nomad.block.itemblock;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.modules.nomad.ModuleNomad;
import com.disney.customcraft.modules.nomad.block.BlockTorchOff;
import com.disney.customcraft.modules.nomad.block.BlockTorchOff.TorchMaterial;
import com.disney.customcraft.modules.nomad.block.tile.TileTorch;
import com.disney.customcraft.modules.nomad.block.BlockTorchOn;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ItemBlockTorchOff extends ItemBlock {
	
	TorchMaterial material;
	
	public ItemBlockTorchOff(Block block) {
		super(block);
		setMaxStackSize(1);
		
		material = ((BlockTorchOff)block).material;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + ModInfo.NOMAD_TORCH_NAMES[material.getMeta()];
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		Block block = world.getBlock(x, y, z);
		
		if(block instanceof BlockGrass) {
        	ItemStack itemStack = new ItemStack(ModuleNomad.blockTorchOn[material.getMeta()]);
			NBTTagCompound tags = new NBTTagCompound();
			tags.setInteger("dura", 0);
			itemStack.setTagCompound(tags);
        	
			MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
			player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
			
			return true;
        }
		else if(block instanceof BlockTorchOn) {
			ItemStack itemStack = new ItemStack(ModuleNomad.blockTorchOn[material.getMeta()]);
			NBTTagCompound tags = new NBTTagCompound();
			
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileTorch) {
				tags.setInteger("dura", Math.max(0, ((TileTorch)tile).maxDura - material.getMaxDura() + ((TileTorch)tile).currentDura));
			} else {
				tags.setInteger("dura", 0);	
			}
			itemStack.setTagCompound(tags);
        	
			MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
			player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
			
			return true;
		}
		
		return super.onItemUse(item, player, world, x, y, z, par7, par8, par9, par10);
	}
	
}
