package com.disney.customcraft.item.part;

import java.util.List;

import com.disney.customcraft.CustomItems;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PartBlade extends Item {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[ItemCustomSword.BLADE_NAMES.length];
	
	public PartBlade() {
		super();
				
		setUnlocalizedName("customcraft");
		setCreativeTab(CreativeTabs.tabMaterials);
		
		setHasSubtypes(true);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".blade");
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "blade" + ItemCustomSword.BLADE_NAMES[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		for(int i = 0; i < ItemCustomSword.BLADE_NAMES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for(int i = 0; i < ItemCustomSword.BLADE_NAMES.length; i++) { 
			icons[i] = register.registerIcon("customcraft:" + "blade" + ItemCustomSword.BLADE_NAMES[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float vecX, float vecY, float vecZ) {
		if(player.isSneaking()) {
			ItemStack itemStack = new ItemStack(CustomItems.customSword);
			
			NBTTagCompound tags = new NBTTagCompound();
			
			tags.setInteger("blade", 0);
			tags.setInteger("hilt", 0);
			itemStack.setTagCompound(tags);
        	
			MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, item));
			player.inventory.mainInventory[player.inventory.currentItem] = itemStack;
			
			return true;
		}
		return false;
    }

}
