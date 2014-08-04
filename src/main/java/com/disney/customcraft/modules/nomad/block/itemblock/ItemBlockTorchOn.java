package com.disney.customcraft.modules.nomad.block.itemblock;

import com.disney.customcraft.ModInfo;
import com.disney.customcraft.modules.nomad.ModuleNomad;
import com.disney.customcraft.modules.nomad.block.BlockTorchOff;
import com.disney.customcraft.modules.nomad.block.BlockTorchOn;
import com.disney.customcraft.modules.nomad.block.BlockTorchOff.TorchMaterial;
import com.disney.customcraft.modules.nomad.block.tile.TileTorch;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ItemBlockTorchOn extends ItemBlock {

	TorchMaterial material;
	
	public ItemBlockTorchOn(Block block) {
		super(block);
		setMaxStackSize(1);
		
		material = ((BlockTorchOn)block).material;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + ModInfo.NOMAD_TORCH_NAMES[material.getMeta()];
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		if (block instanceof BlockTorchOff) {
        	world.setBlock(x, y, z, ModuleNomad.blockTorchOn[((BlockTorchOff)block).material.getMeta()]);
        	
        	TileTorch tile = (TileTorch)world.getTileEntity(x, y, z);
        	tile.currentDura = Math.max(0, ((BlockTorchOff)block).material.getMaxDura() - material.getMaxDura() + item.getTagCompound().getInteger("dura"));
        	
        	return true;
        }
		
		return super.onItemUse(item, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean place = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
    	
    	if(place) {
    		TileTorch tile = (TileTorch)world.getTileEntity(x, y, z);
    		
    		if(stack.getTagCompound() != null) {
    			tile.currentDura = stack.getTagCompound().getInteger("dura");
    		} else {
    			tile.currentDura = 0;
    		}
    		
    		/*if(!world.isRemote) {
    			PacketHandler.sendPacket(Transmission.ALL_CLIENTS, new PacketTileEntity().setParams(Object3D.get(tileEntity), tileEntity.getNetworkedData(new ArrayList())));
    		}*/
    	}
    	
    	return place;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int slot, boolean par5) {
		NBTTagCompound tags = item.getTagCompound();
        if (tags != null) {
    		if(tags.getInteger("dura") >= material.getMaxDura()) {
    			if(entity instanceof EntityPlayer) {
    				EntityPlayer entityPlayer = (EntityPlayer) entity;
    				
    				ItemStack itemstack = new ItemStack(ModuleNomad.blockTorchOff[material.getMeta()]);
    				
    				MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(entityPlayer, item));
    				entityPlayer.inventory.mainInventory[slot] = itemstack;
    			}
    		}
    		
    		tags.setInteger("dura", tags.getInteger("dura") + 1);
    	}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags != null) {
			if(tags.getInteger("dura") > 0) return (double)tags.getInteger("dura") / (double)material.getMaxDura();
		}
		return 0;
	} 
	
}
