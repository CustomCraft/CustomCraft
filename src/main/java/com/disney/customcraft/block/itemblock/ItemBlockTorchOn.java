package com.disney.customcraft.block.itemblock;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.disney.customcraft.api.RegistryTorch;
import com.disney.customcraft.api.RegistryTorch.TorchMaterial;
import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;
import com.disney.customcraft.block.tile.TileTorch;

public class ItemBlockTorchOn extends ItemBlock {
	
	private TorchMaterial material;
	
	public ItemBlockTorchOn(Block block) {
		super(block);
		setMaxStackSize(1);
		
		material = ((BlockTorchOn)block).getTorchMaterial();
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		if(block instanceof BlockTorchOff) {
			world.setBlock(x, y, z, RegistryTorch.getTorchOn((BlockTorchOff) block));
        	
        	TileTorch tile = (TileTorch)world.getTileEntity(x, y, z);
        	int dura = ((BlockTorchOff)block).getTorchMaterial().getMaxDura() - material.getMaxDura() + item.getTagCompound().getInteger("dura");
			if(dura < 0) dura = 0;
        	tile.currentDura = dura;
        	
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
    				
    				Block torchOff = RegistryTorch.getTorchOff((BlockTorchOn) field_150939_a);
    				if(torchOff != null) {
	    				ItemStack itemstack = new ItemStack(torchOff);
	    				
	    				MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(entityPlayer, item));
	    				entityPlayer.inventory.mainInventory[slot] = itemstack;
    				}
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
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced) {
		String testString = "TestString\n";
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			NBTTagCompound tags = itemStack.getTagCompound();
			if (tags != null) {
				int dura = tags.getInteger("dura"); 
				int max = material.getMaxDura();
				String d = "Durability : " + (max - dura) + "/" + max;
				list.add(d);
			}
		}
		
		super.addInformation(itemStack, player, list, advanced);
	}
	
}
