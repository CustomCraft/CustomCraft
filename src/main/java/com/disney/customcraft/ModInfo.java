package com.disney.customcraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModInfo {

	public static final String ID = "CustomCraft";
	public static final String NAME = ID;
	public static final int VERSION_MAJOR = 0;
	public static final int VERSION_MINOR = 1;
	public static final int VERSION_REVISION = 0;
	public static final String VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_REVISION;
	public static final String CHANNEL = ID;
	
	public static final String COMMON_PROXY = "com.disney.customcraft.handlers.proxy.CommonProxy";
	public static final String CLIENT_PROXY = "com.disney.customcraft.handlers.proxy.ClientProxy";

	public static final String CONFIG_LOC = "/customcraft";
	public static final String MAINFILE_LOC = "/customcraft.cfg";
	
	public static final String MODULE_LOC = "/module";
	public static final String MODULE_CORE = "/core.cfg";
	public static final String MODULE_NOMAD = "/nomad.cfg";

	public static final String[] CORE_ORE_NAMES = { "oreCopper", "oreTin", "oreBronze", "oreSteel" };
	public static final String[] CORE_STORAGE_NAMES = { "blockCopper", "blockTin", "blockBronze", "blockSteel" };
	public static final String[] CORE_INGOT_NAMES = { "ingotCopper", "ingotTin", "ingotBronze", "ingotSteel" };
	public static final String[] CORE_DUST_NAMES = { "dustCopper", "dustTin", "dustBronze", "dustSteel", "dustIron", "dustGold", "dustDiamond" };
	public static final String[] CORE_SWORD_NAMES = { "swordCopper", "swordTin", "swordBronze", "swordSteel" };
	public static final String[] CORE_PICKAXE_NAMES = { "pickaxeCopper", "pickaxeTin", "pickaxeBronze", "pickaxeSteel" };
	public static final String[] CORE_AXE_NAMES = { "axeCopper", "axeTin", "axeBronze", "axeSteel" };
	public static final String[] CORE_SHOVEL_NAMES = { "shovelCopper", "shovelTin", "shovelBronze", "shovelSteel" };
	public static final String[] CORE_HOE_NAMES = { "hoeCopper", "hoeTin", "hoeBronze", "hoeSteel" };
	public static final String[] CORE_HELMET_NAMES = { "helmetCopper", "helmetTin", "helmetBronze", "helmetSteel" };
	public static final String[] CORE_CHEST_NAMES = { "chestCopper", "chestTin", "chestBronze", "chestSteel" };
	public static final String[] CORE_LEGS_NAMES = { "legsCopper", "legsTin", "legsBronze", "legsSteel" };
	public static final String[] CORE_BOOTS_NAMES = { "bootsCopper", "bootsTin", "bootsBronze", "bootsSteel" };
	
	public static final String[] NOMAD_CLUB_NAMES = { "clubStone" };

	public static final ToolMaterial TOOL_COPPER = EnumHelper.addToolMaterial("COPPER", 1, 190, 5.0F, 1.0F, 11);
	public static final ToolMaterial TOOL_TIN = EnumHelper.addToolMaterial("TIN", 1, 22, 9.0F, 0.0F, 18);
	public static final ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 600, 7.0F, 2.0F, 15);
	public static final ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial("STEEL", 3, 900, 8.0F, 3.0F, 8);
	public static final ToolMaterial[] TOOL_MATERIALS = { TOOL_COPPER, TOOL_TIN, TOOL_BRONZE, TOOL_STEEL };
	
	public static final ArmorMaterial ARMOR_COPPER = EnumHelper.addArmorMaterial("COPPER", 10, new int[]{2, 5, 4, 1}, 11);
	public static final ArmorMaterial ARMOR_TIN = EnumHelper.addArmorMaterial("TIN", 4, new int[]{1, 3, 2, 1}, 20);
	public static final ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("BRONZE", 22, new int[]{3, 7, 5, 3}, 15);
	public static final ArmorMaterial ARMOR_STEEL = EnumHelper.addArmorMaterial("STEEL", 28, new int[]{3, 8, 6, 3}, 7);
	public static final ArmorMaterial[] ARMOR_MATERIALS = { ARMOR_COPPER, ARMOR_TIN, ARMOR_BRONZE, ARMOR_STEEL };
	
}
