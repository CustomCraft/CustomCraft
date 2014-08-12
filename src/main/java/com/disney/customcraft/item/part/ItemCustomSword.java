package com.disney.customcraft.item.part;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;
import com.disney.customcraft.api.RegistryTools.HeadMaterial;
import com.disney.customcraft.api.RegistryTools.ShaftMaterial;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemCustomSword extends ItemSword implements ITool {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] headIcons = new IIcon[RegistryTools.MATERIALS_HEAD.size()];
	@SideOnly(Side.CLIENT)
	protected IIcon[] shaftIcons = new IIcon[RegistryTools.MATERIALS_SHAFT.size()];

	public ItemCustomSword() {
		super(ToolMaterial.WOOD);
		
		setUnlocalizedName("customcraft.sword");
		//setCreativeTab(null);
		
		setMaxDamage(30);
		
		register();
	}
	
	private void register() {
		GameRegistry.registerItem(this, getUnlocalizedName());
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
		for(int i = 0; i < headIcons.length; i++) {
			headIcons[i] = register.registerIcon("customcraft:tool/bladePart" + RegistryTools.MATERIALS_HEAD.get(i).getName());
		}
		for(int i = 0; i < shaftIcons.length; i++) {
			shaftIcons[i] = register.registerIcon("customcraft:tool/hiltPart" + RegistryTools.MATERIALS_SHAFT.get(i).getName());
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass) {
		NBTTagCompound tags = stack.getTagCompound();
		if(tags != null) {
			if(renderPass == 0) {
				return headIcons[tags.getInteger("head")];
			} else if(renderPass == 1) {
				return shaftIcons[tags.getInteger("shaft")];
			}
		}
        return null;
	}
	
	public void createRecipes() {
		for(int i = 0; i < RegistryTools.MATERIALS_HEAD.size(); i++) {
			for(int j = 0; j < RegistryTools.MATERIALS_SHAFT.size(); j++) {
				HeadMaterial headMaterial = RegistryTools.MATERIALS_HEAD.get(i);
				ShaftMaterial shaftMaterial = RegistryTools.MATERIALS_SHAFT.get(j);
				
				GameRegistry.addRecipe(new ShapedOreRecipe(createOutput(i, j, headMaterial, shaftMaterial), new Object[] { " i ", " i ", " s ", 'i', "ingot" + headMaterial.getName(), 's', "stick" + shaftMaterial.getName() }));
			}
		}
	}
	
	public ItemStack createOutput(int headID, int shaftID) {
		HeadMaterial headMaterial = RegistryTools.MATERIALS_HEAD.get(headID);
		ShaftMaterial shaftMaterial = RegistryTools.MATERIALS_SHAFT.get(shaftID);
		
		return createOutput(headID, shaftID, headMaterial, shaftMaterial);
	}
	
	public ItemStack createOutput(int headID, int shaftID, HeadMaterial headMaterial, ShaftMaterial shaftMaterial) {
		NBTTagCompound tags = new NBTTagCompound();
		tags.setInteger("head", headID);
		tags.setInteger("shaft", shaftID);
		
		tags.setInteger("dura", 0);
		tags.setInteger("maxdura", headMaterial.getDura() + shaftMaterial.getDura());
		
		tags.setInteger("damage", 3 + headID);
		
		ItemStack output = new ItemStack(this);
		output.setTagCompound(tags);
		
		return output;
	}
	
	public String getPartName() {
		return "blade";
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			NBTTagCompound tags = itemStack.getTagCompound();
			if (tags != null) {
				String str;
				
				str = "Blade Material : " + RegistryTools.MATERIALS_HEAD.get(tags.getInteger("head")).getName();
				list.add(str);

				str = "Hilt Material : " + RegistryTools.MATERIALS_SHAFT.get(tags.getInteger("shaft")).getName();
				list.add(str);

				str = "Durability : " + (tags.getInteger("maxdura") - tags.getInteger("dura")) + "/" + tags.getInteger("maxdura");
				list.add(str);
				
				str = "Damage : " + tags.getInteger("damage");
				list.add(str);
			}
		} else { 
			list.add(StatCollector.translateToLocalFormatted("tooltip.info", EnumChatFormatting.GREEN.toString() + EnumChatFormatting.ITALIC + "Shift" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY));
		}
	}
	
	/**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
	@Override
    public Multimap getAttributeModifiers(ItemStack stack) {
		return HashMultimap.create();
    }

}
