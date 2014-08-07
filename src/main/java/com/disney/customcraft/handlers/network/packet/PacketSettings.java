package com.disney.customcraft.handlers.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.disney.customcraft.handlers.LogHandler;
import com.disney.customcraft.handlers.config.Config;
import com.disney.customcraft.handlers.network.AbstractPacket;

public class PacketSettings extends AbstractPacket {
	
	public boolean enabledDayLength;
	public int valueDayLength;
	
	public boolean enabledAdvDarkness;
	public float valueMoonlight;
	public float valueGamma;

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeBoolean(enabledDayLength);
		buffer.writeInt(valueDayLength);
		
		buffer.writeBoolean(enabledAdvDarkness);
		buffer.writeFloat(valueMoonlight);
		buffer.writeFloat(valueGamma);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		enabledDayLength = buffer.readBoolean();
		valueDayLength = buffer.readInt();
		
		enabledAdvDarkness = buffer.readBoolean();
		valueMoonlight = buffer.readFloat();
		valueGamma = buffer.readFloat();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		Config.nomadLongerDays = enabledDayLength;
		Config.nomadDayLength = valueDayLength;
		
		Config.nomadDarkerNights = enabledAdvDarkness;
		Config.nomadMoonlight = valueMoonlight;
		Config.nomadGamma = valueGamma;
		Config.nomadMoonPhases = new float[] { 1.0F * Config.nomadMoonlight + 0.01F, 0.75F * Config.nomadMoonlight + 0.01F, 0.5F * Config.nomadMoonlight + 0.01F, 0.25F * Config.nomadMoonlight + 0.01F, 0.0F * Config.nomadMoonlight + 0.01F, 0.25F * Config.nomadMoonlight + 0.01F, 0.5F * Config.nomadMoonlight + 0.01F,0.75F * Config.nomadMoonlight + 0.01F};
		
		LogHandler.log("HandleSettingsPacket: DayLength:" + enabledDayLength + ", value:" + valueDayLength);
		LogHandler.log("HandleSettingsPacket: Darkness:" + enabledAdvDarkness + ", value1:" + valueMoonlight + ", value2:" + valueGamma);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		//packet is only sent to client
	}

}
