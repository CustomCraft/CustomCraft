package com.disney.customcraft.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.disney.customcraft.api.RegistryNEI.NEISet;

public class RegistryParts {
	
	public static final List<HeadMaterial> MATERIALS_HEAD = new ArrayList<HeadMaterial>();
	public static final List<ShaftMaterial> MATERIALS_SHAFT = new ArrayList<ShaftMaterial>();
	
	public static final List<IHeadPart> PARTS_HEAD = new ArrayList<IHeadPart>();
	public static final List<IShaftPart> PARTS_SHAFT = new ArrayList<IShaftPart>();
	
	public static final List<ITool> TOOLS = new ArrayList<ITool>();
	
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
	
	public static void addPartHead(IHeadPart headPart) {
		if(!PARTS_HEAD.contains(headPart)) {
			PARTS_HEAD.add(headPart);
		}
	}
	
	public static void addPartShaft(IShaftPart shaftPart) {
		if(!PARTS_SHAFT.contains(shaftPart)) {
			PARTS_SHAFT.add(shaftPart);
		}
	}
	
	public static void addTool(ITool tool) {
		if(!TOOLS.contains(tool)) {
			TOOLS.add(tool);
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
