package com.disney.customcraft.item.part;

import java.util.List;

import com.disney.customcraft.api.IHeadPart;
import com.disney.customcraft.api.IShaftPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryParts;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class ItemCustomTool extends Item {
	
	public ItemCustomTool() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(null);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".tool");
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + RegistryParts.TOOLS.get(itemstack.getItemDamage()).getName();
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses () {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public int getRenderPasses (int metadata) {
        return 2;
    }
	
	@Override
	public IIcon getIconFromDamage(int metadata) {
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(IHeadPart headPart : RegistryParts.PARTS_HEAD) {
			headPart.registerIcons(register);
		}
		for(IShaftPart shaftPart : RegistryParts.PARTS_SHAFT) {
			shaftPart.registerIcons(register);
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass) {
		NBTTagCompound tags = stack.getTagCompound();
		if(tags != null) {
			if(renderPass == 0) {
				return RegistryParts.TOOLS.get(stack.getItemDamage()).getHeadPart().getIcon(tags, ItemHeadBlade.TYPE_BLADE);
			} else if(renderPass == 1) {
				return RegistryParts.TOOLS.get(stack.getItemDamage()).getShaftPart().getIcon(tags, ItemShaftStick.TYPE_HILT);
			}
		}
        return null;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
		int damage = stack.getItemDamage();
		
		RegistryParts.TOOLS.get(damage).getInformation(stack, player, list, advanced);
		
		super.addInformation(stack, player, list, advanced);
	}

}
