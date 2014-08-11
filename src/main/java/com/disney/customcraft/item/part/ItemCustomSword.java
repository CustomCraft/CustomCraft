package com.disney.customcraft.item.part;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.IShaftPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryParts;

public class ItemCustomSword implements ITool {

	private IHeadPart headPart;
	private IShaftPart shaftPart;
	
	public ItemCustomSword(IHeadPart headPart, IShaftPart shaftPart) {
		this.headPart = headPart;
		this.shaftPart = shaftPart;
	}
	
	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public IHeadPart getHeadPart() {
		return headPart;
	}

	@Override
	public IShaftPart getShaftPart() {
		return shaftPart;
	}

	@Override
	public boolean isValidInputs(IHeadPart headPart, IShaftPart shaftPart) {
		return headPart instanceof ItemHeadBlade && shaftPart instanceof ItemShaftStick;
	}
	
	public void getInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced) {
		//if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			NBTTagCompound tags = itemStack.getTagCompound();
			if (tags != null) {
				String str;
				
				str = "Blade Material : " + RegistryParts.MATERIALS_HEAD.get(tags.getInteger(getHeadPart().getItemName())).getName();
				list.add(str);

				str = "Hilt Material : " + RegistryParts.MATERIALS_SHAFT.get(tags.getInteger(shaftPart.getItemName())).getName();
				list.add(str);

				int max = tags.getInteger("maxdura"); 
				str = "Durability : " + (max - tags.getInteger("dura")) + "/" + max;
				list.add(str);
			}
		//}
	}

}
