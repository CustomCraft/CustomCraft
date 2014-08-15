package com.disney.customcraft.event;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.disney.customcraft.api.IPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;
import com.ibm.icu.text.MessagePattern.Part;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventAnvil {

	@SubscribeEvent
	public void onBucketFill(AnvilUpdateEvent event) {
		ItemStack output = event.left.copy();
		
		for(IPart part : RegistryTools.PARTS) {
			if(output.getItem() == part.getTool()) {
				if(output.getItem().getIsRepairable(event.left, event.right)) {
					Map map = EnchantmentHelper.getEnchantments(output);
					int enchantCount = 0;
					
					NBTTagCompound tags = output.getTagCompound().getCompoundTag("cctool");
					int dura = tags.getInteger("dura");
					int maxDura = tags.getInteger("maxdura");
					
					int repairAmount = Math.min(maxDura - dura, maxDura / 4);
					
					for(int i = 0; repairAmount > 0 && i < event.right.stackSize; ++i) {
		                int newValue = dura + repairAmount;
		                tags.setInteger("dura", newValue);
		                
		                enchantCount += Math.max(1, repairAmount / 100) + map.size();
		                
		                dura = tags.getInteger("dura");
						maxDura = tags.getInteger("maxdura");
		                repairAmount = Math.min(maxDura - dura, maxDura / 4);
		            }
					
					event.output = output;
					event.cost = 1;
					
					

		            //this.stackSizeToBeUsedInRepair = l;
				}
			}
		}

        /*if (itemstack1.isItemStackDamageable() && itemstack1.getItem().getIsRepairable(itemstack, itemstack2))
        {
            k = Math.min(itemstack1.getItemDamageForDisplay(), itemstack1.getMaxDamage() / 4);

            if (k <= 0)
            {
                this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                this.maximumCost = 0;
                return;
            }

            for (l = 0; k > 0 && l < itemstack2.stackSize; ++l)
            {
                i1 = itemstack1.getItemDamageForDisplay() - k;
                itemstack1.setItemDamage(i1);
                i += Math.max(1, k / 100) + map.size();
                k = Math.min(itemstack1.getItemDamageForDisplay(), itemstack1.getMaxDamage() / 4);
            }

            this.stackSizeToBeUsedInRepair = l;
        }
        else
        {
            if (!flag && (itemstack1.getItem() != itemstack2.getItem() || !itemstack1.isItemStackDamageable()))
            {
                this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                this.maximumCost = 0;
                return;
            }

            if (itemstack1.isItemStackDamageable() && !flag)
            {
                k = itemstack.getMaxDamage() - itemstack.getItemDamageForDisplay();
                l = itemstack2.getMaxDamage() - itemstack2.getItemDamageForDisplay();
                i1 = l + itemstack1.getMaxDamage() * 12 / 100;
                int j1 = k + i1;
                k1 = itemstack1.getMaxDamage() - j1;

                if (k1 < 0)
                {
                    k1 = 0;
                }

                if (k1 < itemstack1.getItemDamage())
                {
                    itemstack1.setItemDamage(k1);
                    i += Math.max(1, i1 / 100);
                }
            }

            Map map1 = EnchantmentHelper.getEnchantments(itemstack2);
            iterator1 = map1.keySet().iterator();

            while (iterator1.hasNext())
            {
                i1 = ((Integer)iterator1.next()).intValue();
                enchantment = Enchantment.enchantmentsList[i1];
                k1 = map.containsKey(Integer.valueOf(i1)) ? ((Integer)map.get(Integer.valueOf(i1))).intValue() : 0;
                l1 = ((Integer)map1.get(Integer.valueOf(i1))).intValue();
                int i3;

                if (k1 == l1)
                {
                    ++l1;
                    i3 = l1;
                }
                else
                {
                    i3 = Math.max(l1, k1);
                }

                l1 = i3;
                int i2 = l1 - k1;
                boolean flag1 = enchantment.canApply(itemstack);

                if (this.thePlayer.capabilities.isCreativeMode || itemstack.getItem() == Items.enchanted_book)
                {
                    flag1 = true;
                }

                Iterator iterator = map.keySet().iterator();

                while (iterator.hasNext())
                {
                    int j2 = ((Integer)iterator.next()).intValue();

                    if (j2 != i1 && !enchantment.canApplyTogether(Enchantment.enchantmentsList[j2]))
                    {
                        flag1 = false;
                        i += i2;
                    }
                }

                if (flag1)
                {
                    if (l1 > enchantment.getMaxLevel())
                    {
                        l1 = enchantment.getMaxLevel();
                    }

                    map.put(Integer.valueOf(i1), Integer.valueOf(l1));
                    int l2 = 0;

                    switch (enchantment.getWeight())
                    {
                        case 1:
                            l2 = 8;
                            break;
                        case 2:
                            l2 = 4;
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        default:
                            break;
                        case 5:
                            l2 = 2;
                            break;
                        case 10:
                            l2 = 1;
                    }

                    if (flag)
                    {
                        l2 = Math.max(1, l2 / 2);
                    }

                    i += l2 * i2;
                }
            }
        }
    }

    if (StringUtils.isBlank(this.repairedItemName))
    {
        if (itemstack.hasDisplayName())
        {
            j = itemstack.isItemStackDamageable() ? 7 : itemstack.stackSize * 5;
            i += j;
            itemstack1.func_135074_t();
        }
    }
    else if (!this.repairedItemName.equals(itemstack.getDisplayName()))
    {
        j = itemstack.isItemStackDamageable() ? 7 : itemstack.stackSize * 5;
        i += j;

        if (itemstack.hasDisplayName())
        {
            k2 += j / 2;
        }

        itemstack1.setStackDisplayName(this.repairedItemName);
    }

    k = 0;

    for (iterator1 = map.keySet().iterator(); iterator1.hasNext(); k2 += k + k1 * l1)
    {
        i1 = ((Integer)iterator1.next()).intValue();
        enchantment = Enchantment.enchantmentsList[i1];
        k1 = ((Integer)map.get(Integer.valueOf(i1))).intValue();
        l1 = 0;
        ++k;

        switch (enchantment.getWeight())
        {
            case 1:
                l1 = 8;
                break;
            case 2:
                l1 = 4;
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                break;
            case 5:
                l1 = 2;
                break;
            case 10:
                l1 = 1;
        }

        if (flag)
        {
            l1 = Math.max(1, l1 / 2);
        }
    }

    if (flag)
    {
        k2 = Math.max(1, k2 / 2);
    }

    if (flag && !itemstack1.getItem().isBookEnchantable(itemstack1, itemstack2)) itemstack1 = null;

    this.maximumCost = k2 + i;

    if (i <= 0)
    {
        itemstack1 = null;
    }

    if (j == i && j > 0 && this.maximumCost >= 40)
    {
        this.maximumCost = 39;
    }

    if (this.maximumCost >= 40 && !this.thePlayer.capabilities.isCreativeMode)
    {
        itemstack1 = null;
    }

    if (itemstack1 != null)
    {
        l = itemstack1.getRepairCost();

        if (itemstack2 != null && l < itemstack2.getRepairCost())
        {
            l = itemstack2.getRepairCost();
        }

        if (itemstack1.hasDisplayName())
        {
            l -= 9;
        }

        if (l < 0)
        {
            l = 0;
        }

        l += 2;
        itemstack1.setRepairCost(l);
        EnchantmentHelper.setEnchantments(map, itemstack1);
    }

    this.outputSlot.setInventorySlotContents(0, itemstack1);
    this.detectAndSendChanges();*/
	}
	
}
