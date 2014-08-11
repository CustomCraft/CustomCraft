package com.disney.customcraft.item.part;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.IShaftPart;
import com.disney.customcraft.api.RegistryParts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShaftStick implements IShaftPart {
	
	public static final int NUM_TYPE = 2;
	public static final int TYPE_HILT = 0;
	public static final int TYPE_ROD = 1;
	
	@SideOnly(Side.CLIENT)
	protected IIcon[][] icons = new IIcon[RegistryParts.MATERIALS_SHAFT.size()][2];

	@Override
	public String getItemName() {
		return "stick";
	}

	@Override
	public String getPartName(int type) {
		if(type == TYPE_HILT) {
			return "hiltPart";
		} else if(type == TYPE_ROD) {
			return "rodPart";
		}
		return null;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < RegistryParts.MATERIALS_SHAFT.size(); i++) { 
			for(int j = 0; j < NUM_TYPE; j++) {
				icons[i][j] = register.registerIcon("customcraft:tool/" + getPartName(j) + RegistryParts.MATERIALS_SHAFT.get(i));
			}
		}
	}
	
	public IIcon getIcon(NBTTagCompound tags, int type) {
		return icons[tags.getInteger(getItemName())][type];
	}

}
