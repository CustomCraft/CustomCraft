package com.disney.customcraft.item.part;

import com.disney.customcraft.api.IPart;
import com.disney.customcraft.api.ITool;
import com.disney.customcraft.api.RegistryTools;

public class ToolMulti {
	
	ITool customTool;
	IPart customPart;
	
	public ToolMulti(ITool customTool) {
		this.customTool = customTool;
		this.customPart = new ItemMultiPart(customTool);
		
		RegistryTools.addToolPart(customPart);
	}
	
	public void createRecipes() {
		customTool.createRecipes();
	}

}
