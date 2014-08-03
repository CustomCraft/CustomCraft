package com.disney.customcraft.modules.nomad.item;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemClub extends ItemTool {
	
	private static final Set toolClasses = Sets.newHashSet("pickaxe", "axe", "shovel");
	
	private String itemName;
	
	public ItemClub(ToolMaterial material, String itemName) {
		super(0.0F, material, Sets.newHashSet());
		
		this.itemName = itemName;
		
		setUnlocalizedName("nomad");
		setCreativeTab(CreativeTabs.tabTools);
		
		register();
	}
	
	public void register() {
		GameRegistry.registerItem(this, getUnlocalizedName() + ".clubStone");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + ".clubStone";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("customcraft:nomad/" + "clubStone");
	}
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", func_150913_i().getDamageVsEntity(), 0));
        return multimap;
    }
	
	/**
	 * Dig speed
	 */
	public float func_150893_a(ItemStack itemStack, Block block)
    {
        return 2.0F;
    }

    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
        	itemStack.damageItem(1, entity);
        }
        return true;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    /*===================================== FORGE START =================================*/
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        if (toolClass.equals("pickaxe") || toolClass.equals("axe") || toolClass.equals("shovel")) {
            return this.toolMaterial.getHarvestLevel();
        }
        else {
            return -1;
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
    	return toolClasses;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return efficiencyOnProperMaterial;
        }
        return super.getDigSpeed(stack, block, meta);
    }

}
