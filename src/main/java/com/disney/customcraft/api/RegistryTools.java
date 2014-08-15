package com.disney.customcraft.api;

import java.util.ArrayList;
import java.util.List;

public class RegistryTools {
	
	public static final List<HeadMaterial> MATERIALS_HEAD = new ArrayList<HeadMaterial>();
	public static final List<ShaftMaterial> MATERIALS_SHAFT = new ArrayList<ShaftMaterial>();
	
	public static final List<IPart> PARTS = new ArrayList<IPart>();
	
	public static void addMaterialHead(HeadMaterial material) {
		if(!MATERIALS_HEAD.contains(material)) {
			MATERIALS_HEAD.add(material);
		}
	}
	
	public static void addMaterialShaft(ShaftMaterial material) {
		if(!MATERIALS_SHAFT.contains(material)) {
			MATERIALS_SHAFT.add(material);
		}
	}
	
	public static void addToolPart(IPart part) {
		if(!PARTS.contains(part)) {
			PARTS.add(part);
		}
	}
	
	public static int getHeadID(HeadMaterial material) {
		for(int i = 0; i < MATERIALS_HEAD.size(); i++) {
			if(material == MATERIALS_HEAD.get(i)) {
				return i;
			}
		}
		return -1;
	}
	
	public enum HeadMaterial {	
	    COPPER("Copper", "ingotCopper", 2, 2, 1, 8, 100),
	    IRON("Iron", "ingotIron", 3, 3, 1, 8, 200),
	    BRONZE("Bronze", "ingotBronze", 3, 3, 1, 8, 500),
	    STEEL("Steel", "ingotSteel", 4, 4, 2, 8, 500),
	    DIAMOND("Diamond", "gemDiamond", 4, 4, 3, 8, 750),
	    COBALT("Cobalt", "ingotCobalt", 5, 5, 4, 8, 750),
	    TITANIUM("Titanium", "ingotTitanium", 5, 5, 5, 10, 1000), 
	    ADAMANTITE("Adamantite", "adamantite", 5, 5, 5, 12, 1250),
	    TIN("Tin", "ingotTin", 0, 1, 0, 12, 50),
	    GOLD("Gold", "ingotGold", 0, 2, 1, 14, 100),
	    ALUMINIUM("Aluminium", "ingotAluminium", 1, 3, 2, 16, 200);
	    
	    public final String name;
	    public final String ingot;
	    public final int damage;
	    public final int harvestLvl;
	    public final int speed;
	    public final int enchant;
	    public final int dura;

	    private HeadMaterial(String name, String ingot, int damage, int speed, int harvestLvl, int enchant, int dura) {
	    	this.name = name;
	    	this.ingot = ingot;
	    	this.damage = damage;
	    	this.speed = speed;
	    	this.harvestLvl = harvestLvl;
	    	this.enchant = enchant;
	        this.dura = dura;
	    }
	}
	
	public enum ShaftMaterial {
		WOOD("Wood", "stickWood", false, 0, 0, 0, 0, 0),
	    COPPER("Copper", "stickCopper", true, 1, 1, 0, 2, 50),
	    IRON("Iron", "stickIron", true, 1, 1, 0, 2, 100),
	    BRONZE("Bronze", "stickBronze", true, 1, 1, 0, 2, 250),
	    STEEL("Steel", "stickSteel", true, 2, 2, 0, 2, 250),
	    DIAMOND("Diamond", "stickDiamond", true, 2, 2, 0, 2, 500),
	    COBALT("Cobalt", "stickCobalt", true, 3, 3, 0, 2, 500),
	    TITANIUM("Titanium", "stickTitanium", true, 3, 3, 0, 4, 750),
	    ADAMANTITE("Adamantite", "stickAdamantite", true, 3, 3, 0, 6, 1000),
	    TIN("Tin", "stickTin", true, 0, 0, 0, 6, 0),
	    GOLD("Gold", "stickGold", true, 0, 0, 0, 7, 100),
	    ALUMINIUM("Aluminium", "stickAluminium", true, 0, 0, 0, 8, 250),
		BONE("Bone", "bone", false, 0, 0, 0, 4, 100);
	    
		public final String name;
		public final String itemName;
		public final boolean create;
	    public final int damage;
	    public final int harvestLvl;
	    public final int speed;
	    public final int enchant;
	    public final int dura;

	    private ShaftMaterial(String name, String itemName, boolean create, int damage, int speed, int harvestLvl, int enchant, int dura) {
	    	this.name = name;
	    	this.itemName = itemName;
	    	this.create = create;
	    	this.damage = damage;
	    	this.speed = speed;
	    	this.harvestLvl = harvestLvl;
	    	this.enchant = enchant;
	        this.dura = dura;
	    }
	}

}
