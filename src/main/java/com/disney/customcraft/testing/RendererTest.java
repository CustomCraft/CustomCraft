package com.disney.customcraft.testing;

import org.lwjgl.opengl.GL11;

import com.disney.customcraft.block.model.ModelCampfire;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RendererTest extends Render {

	private ModelBase model = new ModelRocket();
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float par8, float par9) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x, (float) (y + 1.5F), (float) z);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		//final float var28 = entity.rollAmplitude - par9;
		//float var30 = entity.shipDamage - par9;

		/*if (var30 < 0.0F)
		{
			var30 = 0.0F;
		}*/

		/*if (var28 > 0.0F)
		{
			final float i = entity.getLaunched() ? (5 - MathHelper.floor_double(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
			GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 1.0F);
		}*/

		bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);

		model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation("customcraft", "render/rocket.png");
	}

}
