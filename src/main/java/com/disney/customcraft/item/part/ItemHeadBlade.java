package com.disney.customcraft.item.part;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.RegistryParts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHeadBlade implements IHeadPart {
	
	public static final int NUM_TYPE = 1;
	public static final int TYPE_BLADE = 0;

	@SideOnly(Side.CLIENT)
	protected IIcon[][] icons = new IIcon[RegistryParts.MATERIALS_HEAD.size()][NUM_TYPE];
	
	@Override
	public String getItemName() {
		return "blade";
	}

	@Override
	public String getPartName(int type) {
		return "bladePart";
	}

	@Override
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < RegistryParts.MATERIALS_HEAD.size(); i++) {
			for(int j = 0; j < NUM_TYPE; j++) {
				icons[i][j] = register.registerIcon("customcraft:tool/" + getPartName(j) + RegistryParts.MATERIALS_HEAD.get(i));
			}
		}
	}
	
	public IIcon getIcon(NBTTagCompound tags, int type) {
		return icons[tags.getInteger(getItemName())][type];
	}

}
