package com.disney.customcraft.handlers;

import java.util.Random;

import com.disney.customcraft.api.RegistryTools;
import com.disney.customcraft.api.RegistryTools.HeadMaterial;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ToolHelper {
	
	public static boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		NBTTagCompound nbt = stack.getTagCompound();
		
		if(nbt.hasKey("cctool")) {
			if(entity.canAttackWithItem()) {
	            if(!entity.hitByEntity(player)) {
	                float playerDamage = (float)player.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
	                float enchantDamage = 0.0F;
	                int knockback = 0;
	                
	                if(entity instanceof EntityLivingBase) {
	                	enchantDamage = EnchantmentHelper.getEnchantmentModifierLiving(player, (EntityLivingBase)entity);
	                	knockback += EnchantmentHelper.getKnockbackModifier(player, (EntityLivingBase)entity);
	                }
	
	                if(player.isSprinting()) {
	                    ++knockback;
	                }
	                
	                //calculate custom damage
	                NBTTagCompound tags = nbt.getCompoundTag("cctool");
	    			if(tags != null) {
	    				playerDamage += tags.getInteger("damage");
	    			}
	
	                if(playerDamage > 0.0F || enchantDamage > 0.0F) {
	                    //Increase damage if the player is falling  
	                	boolean fallingBonus = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null && entity instanceof EntityLivingBase;
	
	                    if(fallingBonus && playerDamage > 0.0F) {
	                        playerDamage *= 1.5F;
	                    }
	                    
	                    //Combine damage types after falling increase
	                    playerDamage += enchantDamage;
	                    
	                    //Set enemy on fire
	                    boolean setOnFire = false;
	                    int fireMod = EnchantmentHelper.getFireAspectModifier(player);
	
	                    if(entity instanceof EntityLivingBase && fireMod > 0 && !entity.isBurning()) {
	                    	setOnFire = true;
	                        entity.setFire(1);
	                    }
	
	                    //Do the attack
	                    boolean wasAttacked = entity.attackEntityFrom(DamageSource.causePlayerDamage(player), playerDamage);
	                    if(wasAttacked) {
	                        //Apply knockback
	                    	if(knockback > 0) {
	                            entity.addVelocity((double)(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F));
	                            player.motionX *= 0.6D;
	                            player.motionZ *= 0.6D;
	                            player.setSprinting(false);
	                        }
	
	                        if(fallingBonus) {
	                            player.onCriticalHit(entity);
	                        }
	
	                        if(enchantDamage > 0.0F) {
	                            player.onEnchantmentCritical(entity);
	                        }
	
	                        if(playerDamage >= 18.0F) {
	                            player.triggerAchievement(AchievementList.overkill);
	                        }
	
	                        player.setLastAttacker(entity);
	
	                        //Apply enchantment special modifiers
	                        if(entity instanceof EntityLivingBase) {
	                            EnchantmentHelper.func_151384_a((EntityLivingBase)entity, player);
	                        }
	                        EnchantmentHelper.func_151385_b(player, entity);
	                        
	                        //Do dragon part checks here
	                        ItemStack itemstack = player.getCurrentEquippedItem();
	                        Object object = entity;
	                        if(entity instanceof EntityDragonPart) {
	                            IEntityMultiPart ientitymultipart = ((EntityDragonPart)entity).entityDragonObj;
	
	                            if(ientitymultipart != null && ientitymultipart instanceof EntityLivingBase) {
	                                object = (EntityLivingBase)ientitymultipart;
	                            }
	                        }
	
	                        if(itemstack != null && object instanceof EntityLivingBase) {
	                            itemstack.hitEntity((EntityLivingBase)object, player);
	
	                            if(itemstack.stackSize <= 0) {
	                                player.destroyCurrentEquippedItem();
	                            }
	                        }
	
	                        if(entity instanceof EntityLivingBase) {
	                            player.addStat(StatList.damageDealtStat, Math.round(playerDamage * 10.0F));
	
	                            if(fireMod > 0) {
	                                entity.setFire(fireMod * 4);
	                            }
	                        }
	
	                        player.addExhaustion(0.3F);
	                    }
	                    else if(setOnFire) {
	                        entity.extinguish();
	                    }
	                }
	            }
			}
			return true;
		}
		return false;
	}
	
	public static boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity, int damage) {
		if(!(player instanceof EntityPlayer) || !((EntityPlayer)player).capabilities.isCreativeMode) {
			NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
			int dura = tags.getInteger("dura");
			
			if(dura > 0) {
				dura -= damageItem(stack, damage, player);
				tags.setInteger("dura", dura);
			}
			
			if(dura == 0) {
				player.renderBrokenItemStack(stack);
			}
		}
		
        return true;
    }

    public static boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player, int damage) {
        if((double)block.getBlockHardness(world, x, y, z) != 0.0D) {
        	NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
			int dura = tags.getInteger("dura");
			
			if(dura > 0) {
				dura -= damageItem(stack, damage, player);
				tags.setInteger("dura", dura);
			}
			
			if(dura == 0) {
				player.renderBrokenItemStack(stack);
			}
        }

        return true;
    }
	
	public static int damageItem(ItemStack stack, int damage, EntityLivingBase player) {
		Random random = player.getRNG();
		int enchant = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
		int negated = 0;
		
		for(int i = 0; enchant > 0 && i < damage; ++i) {
            if(EnchantmentDurability.negateDamage(stack, enchant, random)) {
                ++negated;
            }
        }
		damage -= negated;
		
		return damage;
	}
	
	public static boolean showDurabilityBar(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
		return tags.getInteger("dura") != tags.getInteger("maxdura");
	}
	
	public static double getDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound().getCompoundTag("cctool");
		if(tags.getInteger("dura") > 0) return (1.0D - (double)tags.getInteger("dura") / (double)tags.getInteger("maxdura"));
		else return 1.0D;
	}
	
    public static boolean getIsRepairable(ItemStack tool, ItemStack ingot) {
    	NBTTagCompound tags = tool.getTagCompound().getCompoundTag("cctool");
    	
    	HeadMaterial headMaterial = RegistryTools.MATERIALS_HEAD.get(tags.getInteger("head"));
    	if(headMaterial != null) {
    		int[] ingotIDs = OreDictionary.getOreIDs(ingot);
    		for(int i : ingotIDs) {
    			if(OreDictionary.getOreName(i).equals(headMaterial.ingot)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

}
