package com.disney.customcraft.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCampfire extends ModelBase {

	ModelRenderer Stick1;
    ModelRenderer Stick2;
    ModelRenderer Stick3;
    ModelRenderer Stick4;
    ModelRenderer Stick5;
    ModelRenderer Stick6;
    ModelRenderer Stick7;
    ModelRenderer Stick8;
    ModelRenderer Floor;
    ModelRenderer Top;
    ModelRenderer Right;
    ModelRenderer Bot;
    ModelRenderer Left;
  
    public ModelCampfire() {
    	textureWidth = 128;
    	textureHeight = 32;
    
    	Stick1 = new ModelRenderer(this, 27, 19);
        Stick1.addBox(0F, 0F, 0F, 1, 1, 5);
        Stick1.setRotationPoint(-0.5F, 22F, 0.5F);
        Stick1.setTextureSize(128, 32);
        Stick1.mirror = true;
        setRotation(Stick1, 0F, 0F, 0F);
        Stick2 = new ModelRenderer(this, 41, 19);
        Stick2.addBox(0F, 0F, 0F, 1, 1, 6);
        Stick2.setRotationPoint(0F, 20.5F, 0F);
        Stick2.setTextureSize(128, 32);
        Stick2.mirror = true;
        setRotation(Stick2, -0.2617994F, 0.715585F, 0F);
        Stick3 = new ModelRenderer(this, 27, 19);
        Stick3.addBox(0F, 0F, 0F, 1, 1, 5);
        Stick3.setRotationPoint(0.5F, 22F, 0.5F);
        Stick3.setTextureSize(128, 32);
        Stick3.mirror = true;
        setRotation(Stick3, 0F, 1.570796F, 0F);
        Stick4 = new ModelRenderer(this, 41, 19);
        Stick4.addBox(0F, 0F, 0F, 1, 1, 6);
        Stick4.setRotationPoint(0.2F, 20.6F, 0.5F);
        Stick4.setTextureSize(128, 32);
        Stick4.mirror = true;
        setRotation(Stick4, -0.2268928F, -0.8552113F, 0F);
        Stick5 = new ModelRenderer(this, 27, 19);
        Stick5.addBox(0F, 0F, 0F, 1, 1, 5);
        Stick5.setRotationPoint(0.5F, 22F, -0.5F);
        Stick5.setTextureSize(128, 32);
        Stick5.mirror = true;
        setRotation(Stick5, 0F, 3.141593F, 0F);
        Stick6 = new ModelRenderer(this, 41, 19);
        Stick6.addBox(0F, 0F, 0F, 1, 1, 6);
        Stick6.setRotationPoint(-5F, 22F, -3F);
        Stick6.setTextureSize(128, 32);
        Stick6.mirror = true;
        setRotation(Stick6, 0.2792527F, 0.837758F, 0F);
        Stick7 = new ModelRenderer(this, 27, 19);
        Stick7.addBox(0F, 0F, 0F, 1, 1, 5);
        Stick7.setRotationPoint(-0.5F, 22F, -0.5F);
        Stick7.setTextureSize(128, 32);
        Stick7.mirror = true;
        setRotation(Stick7, 0F, -1.570796F, 0F);
        Stick8 = new ModelRenderer(this, 41, 19);
        Stick8.addBox(0F, 0F, -1F, 1, 1, 6);
        Stick8.setRotationPoint(3F, 21.7F, -4F);
        Stick8.setTextureSize(128, 32);
        Stick8.mirror = true;
        setRotation(Stick8, 0.2617994F, -0.9424778F, 0F);
        Floor = new ModelRenderer(this, 1, 1);
        Floor.addBox(0F, 0F, 0F, 16, 1, 16);
        Floor.setRotationPoint(-8F, 23F, -8F);
        Floor.setTextureSize(128, 32);
        Floor.mirror = true;
        setRotation(Floor, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 66, 1);
        Top.addBox(0F, 0F, 0F, 14, 2, 1);
        Top.setRotationPoint(-7F, 22F, 6F);
        Top.setTextureSize(128, 32);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 97, 1);
        Right.addBox(0F, 0F, 0F, 1, 2, 12);
        Right.setRotationPoint(6F, 22F, -6F);
        Right.setTextureSize(128, 32);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Bot = new ModelRenderer(this, 66, 4);
        Bot.addBox(0F, 0F, 0F, 14, 2, 1);
        Bot.setRotationPoint(-7F, 22F, -7F);
        Bot.setTextureSize(128, 32);
        Bot.mirror = true;
        setRotation(Bot, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 97, 15);
        Left.addBox(0F, 0F, 0F, 1, 2, 12);
        Left.setRotationPoint(-7F, 22F, -6F);
        Left.setTextureSize(128, 32);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
    }
  
    public void render(float size, int sticks) {
    	Floor.render(size);
    	Top.render(size);
    	Right.render(size);
    	Bot.render(size);
    	Left.render(size);
    
	    switch(sticks) {
	    	case 8 :
	    		Stick4.render(size);
	    	case 7 :
	    		Stick6.render(size);
	    	case 6 :
	    		Stick8.render(size);
	    	case 5 :
	    		Stick2.render(size);
	    	case 4 :
	    		Stick7.render(size);
	    	case 3 :
	    		Stick5.render(size);
	    	case 2 :
	    		Stick3.render(size);
	    	case 1 :
	    		Stick1.render(size);
	    }
    }
  
    private void setRotation(ModelRenderer model, float x, float y, float z) {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
    }
    
}
