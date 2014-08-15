package com.disney.customcraft.item.part;

import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;
import com.disney.customcraft.api.RegistryTools.HeadMaterial;
import com.disney.customcraft.api.RegistryTools.ShaftMaterial;
import com.disney.customcraft.handlers.ToolHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemCustomPickaxe extends ItemPickaxe implements ITool {
	
	private static final Set toolClasses = Sets.newHashSet("pickaxe");
	private static final Set breakSet = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] headIcons = new IIcon[RegistryTools.MATERIALS_HEAD.size()];
	@SideOnly(Side.CLIENT)
	protected IIcon[] shaftIcons = new IIcon[RegistryTools.MATERIALS_SHAFT.size()];

	public ItemCustomPickaxe() {
		super(ToolMaterial.WOOD);
		
		setUnlocalizedName("customcraft.pickaxe");
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
			headIcons[i] = register.registerIcon("customcraft:pickaxe/head" + RegistryTools.MATERIALS_HEAD.get(i).name);
		}
		for(int i = 0; i < shaftIcons.length; i++) {
			shaftIcons[i] = register.registerIcon("customcraft:pickaxe/shaft" + RegistryTools.MATERIALS_SHAFT.get(i).name);
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
				GameRegistry.addRecipe(new ShapedOreRecipe(createOutput(headMaterial, shaftMaterial), new Object[] { "iii", " s ", " s ", 'i', headMaterial.ingot, 's', shaftMaterial.itemName }));
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
		
		int damage = headMaterial.damage + shaftMaterial.damage - 4;
		if(damage < 1) damage = 1;
		tags.setInteger("damage", damage);
		
		tags.setInteger("harvest", headMaterial.harvestLvl + shaftMaterial.harvestLvl);
		tags.setInteger("speed", headMaterial.speed + shaftMaterial.speed);
		tags.setInteger("enchant", headMaterial.enchant + shaftMaterial.enchant);
		
		ItemStack output = new ItemStack(this);
		output.setTagCompound(nbt);
		
		return output;
	}
	
	public String getPartName() {
		return "pickaxe";
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
	
	/*===================================== FORGE START =================================*/
    private String toolClass;
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
    	if(toolClass.equals("pickaxe")) {
    		NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
        	return tags.getInteger("harvest");
        } else {
            return -1;
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe");
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
        	NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
        	return tags.getInteger("speed");
        }
        return 1.0F;
    }
    /*===================================== FORGE END =================================*/
    
}
