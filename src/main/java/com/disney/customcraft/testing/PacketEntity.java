package com.disney.customcraft.testing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import com.disney.customcraft.handlers.network.AbstractPacket;

public class PacketEntity extends AbstractPacket {
	
	private int entityID;
	private boolean thrust;
	//private double posX;
	//private double posY;
	//private double posZ;
	
	public PacketEntity() {

	}
	
	public PacketEntity(int entityID, boolean thrust) {
		this.entityID = entityID;
		this.thrust = thrust;
	}
	
	public PacketEntity(int entityID, double posX, double posY, double posZ) {
		this.entityID = entityID;
		//this.posX = posX;
		//this.posY = posY;
		//this.posZ = posZ;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(entityID);
		buffer.writeBoolean(thrust);
		//buffer.writeDouble(posX);
		//buffer.writeDouble(posY);
		//buffer.writeDouble(posZ);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		entityID = buffer.readInt();
		thrust = buffer.readBoolean();
		//posX = buffer.readDouble();
		//posY = buffer.readDouble();
		//posZ = buffer.readDouble();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		moveEntity(player);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		moveEntity(player);		
	}
	
	private void moveEntity(EntityPlayer player)
	{
		Entity entity = player.worldObj.getEntityByID(entityID);
		if(entity instanceof EntityTest) {
			EntityTest entityTest = (EntityTest) entity;
			//entity.setPosition(posX, posY, posZ);
			//entity.moveEntity(posX, posY, posZ);
			
			if(thrust) {
				entityTest.thrust = true;
			} else {
				entityTest.thrust = false;
			}
			
			//if(player.worldObj.isRemote || player.getUniqueID().equals(((IEntityFullSync) entity).getOwnerUUID()) || ((IEntityFullSync) entity).getOwnerUUID() == null) {	
		}
	}

}
