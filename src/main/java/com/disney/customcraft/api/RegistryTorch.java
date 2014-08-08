package com.disney.customcraft.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;

public class RegistryTorch {
	
	private static List<TorchMaterial> torchList = new ArrayList<TorchMaterial>();
	private static List<Block> blockTorchOn = new ArrayList<Block>();
	private static List<Block> blockTorchOff = new ArrayList<Block>();
	
	public enum TorchMaterial {
		
	    WOOD("torchWood", 180),
	    COAL("torchCoal", 360),
	    WAX("torchWax", 720),
	    OIL("torchOil", 2160);

	    private final String name;
	    private final int maxDura;

	    private TorchMaterial(String name, int maxDura) {
	    	this.name = name;
	        this.maxDura = maxDura;
	    }
	    
	    public String getItemName() {
	    	return name;
	    }
	    
	    public int getMaxDura() {
	        return maxDura;
	    }
	}
	
	public static void addTorch(TorchMaterial torch) {
		if(!torchList.contains(torch)) {
			torchList.add(torch);
		}
	}
	
	public static void initTorches() {
		for(TorchMaterial torch : torchList) {
			blockTorchOn.add(new BlockTorchOn(torch));
			blockTorchOff.add(new BlockTorchOff(torch));
		}
	}
	
	public static Block getTorchOn(BlockTorchOff block) {
		int index = blockTorchOff.indexOf(block);
		if(index > -1) {
			return blockTorchOn.get(index);
		}
		return null;
	}

	public static Block getTorchOn(TorchMaterial material) {
		int index = torchList.indexOf(material);
		if(index > -1) {
			return blockTorchOn.get(index);
		}
		return null;
	}
	
	public static Block getTorchOff(BlockTorchOn block) {
		int index = blockTorchOn.indexOf(block);
		if(index > -1) {
			return blockTorchOff.get(index);
		}
		return null;
	}
	
	public static Block getTorchOff(TorchMaterial material) {
		int index = torchList.indexOf(material);
		if(index > -1) {
			return blockTorchOff.get(index);
		}
		return null;
	}

}
