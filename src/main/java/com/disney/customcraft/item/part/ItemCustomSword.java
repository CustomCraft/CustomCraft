package com.disney.customcraft.item.part;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;
import com.disney.customcraft.api.RegistryTools.HeadMaterial;
import com.disney.customcraft.api.RegistryTools.ShaftMaterial;
import com.disney.customcraft.handlers.ToolHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemCustomSword extends ItemSword implements ITool {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] headIcons = new IIcon[RegistryTools.MATERIALS_HEAD.size()];
	@SideOnly(Side.CLIENT)
	protected IIcon[] shaftIcons = new IIcon[RegistryTools.MATERIALS_SHAFT.size()];

	public ItemCustomSword() {
		super(ToolMaterial.WOOD);
		
		setUnlocalizedName("customcraft.sword");
		setCreativeTab(null);
		
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
			headIcons[i] = register.registerIcon("customcraft:sword/head" + RegistryTools.MATERIALS_HEAD.get(i).name);
		}
		for(int i = 0; i < shaftIcons.length; i++) {
			shaftIcons[i] = register.registerIcon("customcraft:sword/shaft" + RegistryTools.MATERIALS_SHAFT.get(i).name);
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass) {
		NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
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
		for(HeadMaterial headMaterial : RegistryTools.MATERIALS_HEAD) {
			for(ShaftMaterial shaftMaterial : RegistryTools.MATERIALS_SHAFT) {
				GameRegistry.addRecipe(new ShapedOreRecipe(createOutput(headMaterial, shaftMaterial), new Object[] { " i ", " i ", " s ", 'i', headMaterial.ingot, 's', shaftMaterial.itemName }));
			}
		}
	}
	
	public ItemStack createOutput(HeadMaterial headMaterial, ShaftMaterial shaftMaterial) {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagCompound tags = new NBTTagCompound();
		
		nbt.setTag("cctool", tags);
		
		tags.setInteger("head", RegistryTools.MATERIALS_HEAD.indexOf(headMaterial));
		tags.setInteger("shaft", RegistryTools.MATERIALS_SHAFT.indexOf(shaftMaterial));
		
		tags.setInteger("dura", headMaterial.dura + shaftMaterial.dura);
		tags.setInteger("maxdura", headMaterial.dura + shaftMaterial.dura);
		
		int damage = headMaterial.damage + shaftMaterial.damage;
		if(damage < 2) damage = 2;
		tags.setInteger("damage", damage);
		
		tags.setInteger("enchant", headMaterial.enchant + shaftMaterial.enchant);
		
		tags.setInteger("harvest", 0);
		tags.setInteger("speed", 0);
		
		ItemStack output = new ItemStack(this);
		output.setTagCompound(nbt);
		
		return output;
	}
	
	public String getPartName() {
		return "sword";
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity) {
		return ToolHelper.hitEntity(stack, player, entity, 1);
    }

	@Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
        return ToolHelper.onBlockDestroyed(stack, world, block, x, y, z, player, 2);
    }
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return ToolHelper.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return ToolHelper.showDurabilityBar(stack);
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return ToolHelper.getDurabilityForDisplay(stack);
	}

	@Override
    public boolean getIsRepairable(ItemStack tool, ItemStack ingot) {
    	return ToolHelper.getIsRepairable(tool, ingot);
    }
	
	/**
     * Override this to remove the normal damage value provided by tools
     */
	@Override
    public Multimap getAttributeModifiers(ItemStack stack) {
		return HashMultimap.create();
    }
}
