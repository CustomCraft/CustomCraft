package com.disney.customcraft.testcasting;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCastingTable extends ModelBase {
	
	ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Side4;
    ModelRenderer Top;
    ModelRenderer Bottom;
  
  public ModelCastingTable()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Side1 = new ModelRenderer(this, 1, 1);
      Side1.addBox(0F, 0F, 0F, 16, 16, 1);
      Side1.setRotationPoint(-8F, 8F, 7F);
      Side1.setTextureSize(128, 64);
      Side1.mirror = true;
      setRotation(Side1, 0F, 0F, 0F);
      Side2 = new ModelRenderer(this, 1, 1);
      Side2.addBox(0F, 0F, 0F, 16, 16, 1);
      Side2.setRotationPoint(-8F, 8F, -8F);
      Side2.setTextureSize(128, 64);
      Side2.mirror = true;
      setRotation(Side2, 0F, 0F, 0F);
      Side3 = new ModelRenderer(this, 1, 19);
      Side3.addBox(0F, 0F, 0F, 1, 16, 14);
      Side3.setRotationPoint(7F, 8F, -7F);
      Side3.setTextureSize(128, 64);
      Side3.mirror = true;
      setRotation(Side3, 0F, 0F, 0F);
      Side4 = new ModelRenderer(this, 1, 19);
      Side4.addBox(0F, 0F, 0F, 1, 16, 14);
      Side4.setRotationPoint(-8F, 8F, -7F);
      Side4.setTextureSize(128, 64);
      Side4.mirror = true;
      setRotation(Side4, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 36, 1);
      Top.addBox(0F, 0F, 0F, 14, 1, 14);
      Top.setRotationPoint(-7F, 9F, -7F);
      Top.setTextureSize(128, 64);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 36, 17);
      Bottom.addBox(0F, 0F, 0F, 14, 1, 14);
      Bottom.setRotationPoint(-7F, 23F, -7F);
      Bottom.setTextureSize(128, 64);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
  }
	
	public void render(float size) {
		Side1.render(size);
	    Side2.render(size);
	    Side3.render(size);
	    Side4.render(size);
	    Top.render(size);
	    Bottom.render(size);
    }
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
    }

}
