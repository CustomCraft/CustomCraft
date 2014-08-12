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
	
	public enum HeadMaterial {
		
	    COPPER("Copper", 100),
	    IRON("Iron", 200),
	    BRONZE("Bronze", 500),
	    STEEL("Steel", 500),
	    DIAMOND("Diamond", 750),
	    TIN("Tin", 50),
	    GOLD("Gold", 150);
	    
	    private final String name;
	    private final int dura;

	    private HeadMaterial(String name, int dura) {
	    	this.name = name;
	        this.dura = dura;
	    }
	    
	    public String getName() {
	    	return name;
	    }
	    
	    public int getDura() {
	        return dura;
	    }
	}
	
	public enum ShaftMaterial {
		
		WOOD("Wood", 0),
	    COPPER("Copper", 50),
	    IRON("Iron", 100),
	    BRONZE("Bronze", 250),
	    STEEL("Steel", 250),
	    DIAMOND("Diamond", 500),
	    TIN("Tin", 0),
	    GOLD("Gold", 0);
	    
	    private final String name;
	    private final int dura;

	    private ShaftMaterial(String name, int dura) {
	    	this.name = name;
	        this.dura = dura;
	    }
	    
	    public String getName() {
	    	return name;
	    }
	    
	    public int getDura() {
	        return dura;
	    }
	}

}
