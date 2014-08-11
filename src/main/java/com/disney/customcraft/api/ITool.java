package com.disney.customcraft.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ITool {
	
	public String getName();
	
	public IHeadPart getHeadPart();
	public IShaftPart getShaftPart();
	
	public boolean isValidInputs(IHeadPart headPart, IShaftPart shaftPart);
	
	public void getInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced);

}
