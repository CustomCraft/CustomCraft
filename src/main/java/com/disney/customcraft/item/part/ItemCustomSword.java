package com.disney.customcraft.item.part;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class ItemCustomSword extends Item {
	
	public static final String[] BLADE_NAMES = { "Copper" };
	public static final String[] HILT_NAMES = { "Tin" };
	
	protected IIcon[] iconsBlade = new IIcon[BLADE_NAMES.length];
	protected IIcon[] iconsHilt = new IIcon[HILT_NAMES.length];
	
	public ItemCustomSword() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(null);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".customSword");
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
	public IIcon getIconFromDamage(int p_77617_1_) {
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < BLADE_NAMES.length; i++) { 
			iconsBlade[i] = register.registerIcon("customcraft:blade" + BLADE_NAMES[i] + "Part");
		}
		for(int i = 0; i < HILT_NAMES.length; i++) { 
			iconsHilt[i] = register.registerIcon("customcraft:hilt" + HILT_NAMES[i] + "Part");
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass) {
		NBTTagCompound tags = stack.getTagCompound();
        if(tags != null) {
        	//if(tags.hasKey("customitem")) {
        		//tags = stack.getTagCompound().getCompoundTag("customitem");
        		if(renderPass == 0) {
        			if(tags.hasKey("blade")) {
        				return iconsBlade[tags.getInteger("blade")];
        			}
        		} else if(renderPass == 1) {
        			if(tags.hasKey("hilt")) {
        				return iconsHilt[tags.getInteger("hilt")];
        			}
        		}
        	//}
        }
        return null;
	}

}
