package com.disney.customcraft.testing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRocket extends ModelBase
{
	ModelRenderer Top;
    ModelRenderer Floor;
    ModelRenderer Front;
    ModelRenderer Back;
    ModelRenderer Left;
    ModelRenderer Right;

	public ModelRocket()
	{
		textureWidth = 256;
	    textureHeight = 128;

	    Top = new ModelRenderer(this, 1, 1);
		Top.addBox(0F, 0F, 0F, 20, 1, 20);
		Top.setRotationPoint(-10F, -8F, -10F);
		Top.setTextureSize(256, 128);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Floor = new ModelRenderer(this, 83, 1);
		Floor.addBox(0F, 0F, 0F, 20, 1, 20);
		Floor.setRotationPoint(-10F, 23F, -10F);
		Floor.setTextureSize(256, 128);
		Floor.mirror = true;
		setRotation(Floor, 0.0174533F, 0F, 0F);
		Front = new ModelRenderer(this, 1, 24);
		Front.addBox(0F, 0F, 0F, 20, 30, 1);
		Front.setRotationPoint(-10F, -7F, -10F);
		Front.setTextureSize(256, 128);
		Front.mirror = true;
		setRotation(Front, 0F, 0F, 0F);
		Back = new ModelRenderer(this, 45, 24);
		Back.addBox(0F, 0F, 0F, 20, 30, 1);
		Back.setRotationPoint(-10F, -7F, 9F);
		Back.setTextureSize(256, 128);
		Back.mirror = true;
		setRotation(Back, 0F, 0F, 0F);
		Left = new ModelRenderer(this, 1, 57);
		Left.addBox(0F, 0F, 0F, 1, 30, 18);
		Left.setRotationPoint(-10F, -7F, -9F);
		Left.setTextureSize(256, 128);
		Left.mirror = true;
		setRotation(Left, 0F, 0F, 0F);
		Right = new ModelRenderer(this, 41, 57);
		Right.addBox(0F, 0F, 0F, 1, 30, 18);
		Right.setRotationPoint(9F, -7F, -9F);
		Right.setTextureSize(256, 128);
		Right.mirror = true;
		setRotation(Right, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Top.render(f5);
	    Floor.render(f5);
	    Front.render(f5);
	    Back.render(f5);
	    Left.render(f5);
	    Right.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
