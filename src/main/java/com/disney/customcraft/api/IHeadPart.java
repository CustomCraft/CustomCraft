package com.disney.customcraft.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IHeadPart {
	
	public String getItemName();
	public String getPartName(int type);
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register);
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(NBTTagCompound tags, int type);

}
