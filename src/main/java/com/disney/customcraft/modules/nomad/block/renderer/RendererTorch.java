package com.disney.customcraft.modules.nomad.block.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererTorch implements ISimpleBlockRenderingHandler {

	final int renderID;

	public RendererTorch(int id) {
		renderID = id;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int l = renderer.blockAccess.getBlockMetadata(x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double d0 = 0.4000000059604645D;
        double d1 = 0.5D - d0;
        double d2 = 0.20000000298023224D;

        if (l == 1)
        {
            renderTorchAtAngle(renderer, block, (double)x - d1, (double)y + d2, (double)z, -d0, 0.0D, l);
        }
        else if (l == 2)
        {
            renderTorchAtAngle(renderer, block, (double)x + d1, (double)y + d2, (double)z, d0, 0.0D, l);
        }
        else if (l == 3)
        {
            renderTorchAtAngle(renderer, block, (double)x, (double)y + d2, (double)z - d1, 0.0D, -d0, l);
        }
        else if (l == 4)
        {
            renderTorchAtAngle(renderer, block, (double)x, (double)y + d2, (double)z + d1, 0.0D, d0, l);
        }
        else	
        {
            renderTorchAtAngle(renderer, block, (double)x, (double)y, (double)z, 0.0D, 0.0D, l);
        }
		
		return false;
	}
	
	public void renderTorchAtAngle(RenderBlocks renderer, Block block, double p_147747_2_, double p_147747_4_, double p_147747_6_, double p_147747_8_, double p_147747_10_, int p_147747_12_)
    {
        Tessellator tessellator = Tessellator.instance;
        
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 0, p_147747_12_);

        if (renderer.hasOverrideBlockTexture())
        {
            iicon = renderer.overrideBlockTexture;
        }

        double d5 = (double)iicon.getMinU();
        double d6 = (double)iicon.getMinV();
        double d7 = (double)iicon.getMaxU();
        double d8 = (double)iicon.getMaxV();
        double d9 = (double)iicon.getInterpolatedU(7.0D);
        double d10 = (double)iicon.getInterpolatedV(6.0D);
        double d11 = (double)iicon.getInterpolatedU(9.0D);
        double d12 = (double)iicon.getInterpolatedV(8.0D);
        double d13 = (double)iicon.getInterpolatedU(7.0D);
        double d14 = (double)iicon.getInterpolatedV(13.0D);
        double d15 = (double)iicon.getInterpolatedU(9.0D);
        double d16 = (double)iicon.getInterpolatedV(15.0D);
        p_147747_2_ += 0.5D;
        p_147747_6_ += 0.5D;
        double d17 = p_147747_2_ - 0.5D;
        double d18 = p_147747_2_ + 0.5D;
        double d19 = p_147747_6_ - 0.5D;
        double d20 = p_147747_6_ + 0.5D;
        double d21 = 0.0625D;
        double d22 = 0.625D;
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ * (1.0D - d22) - d21, p_147747_4_ + d22, p_147747_6_ + p_147747_10_ * (1.0D - d22) - d21, d9, d10);
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ * (1.0D - d22) - d21, p_147747_4_ + d22, p_147747_6_ + p_147747_10_ * (1.0D - d22) + d21, d9, d12);
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ * (1.0D - d22) + d21, p_147747_4_ + d22, p_147747_6_ + p_147747_10_ * (1.0D - d22) + d21, d11, d12);
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ * (1.0D - d22) + d21, p_147747_4_ + d22, p_147747_6_ + p_147747_10_ * (1.0D - d22) - d21, d11, d10);
        tessellator.addVertexWithUV(p_147747_2_ + d21 + p_147747_8_, p_147747_4_, p_147747_6_ - d21 + p_147747_10_, d15, d14);
        tessellator.addVertexWithUV(p_147747_2_ + d21 + p_147747_8_, p_147747_4_, p_147747_6_ + d21 + p_147747_10_, d15, d16);
        tessellator.addVertexWithUV(p_147747_2_ - d21 + p_147747_8_, p_147747_4_, p_147747_6_ + d21 + p_147747_10_, d13, d16);
        tessellator.addVertexWithUV(p_147747_2_ - d21 + p_147747_8_, p_147747_4_, p_147747_6_ - d21 + p_147747_10_, d13, d14);
        tessellator.addVertexWithUV(p_147747_2_ - d21, p_147747_4_ + 1.0D, d19, d5, d6);
        tessellator.addVertexWithUV(p_147747_2_ - d21 + p_147747_8_, p_147747_4_ + 0.0D, d19 + p_147747_10_, d5, d8);
        tessellator.addVertexWithUV(p_147747_2_ - d21 + p_147747_8_, p_147747_4_ + 0.0D, d20 + p_147747_10_, d7, d8);
        tessellator.addVertexWithUV(p_147747_2_ - d21, p_147747_4_ + 1.0D, d20, d7, d6);
        tessellator.addVertexWithUV(p_147747_2_ + d21, p_147747_4_ + 1.0D, d20, d5, d6);
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ + d21, p_147747_4_ + 0.0D, d20 + p_147747_10_, d5, d8);
        tessellator.addVertexWithUV(p_147747_2_ + p_147747_8_ + d21, p_147747_4_ + 0.0D, d19 + p_147747_10_, d7, d8);
        tessellator.addVertexWithUV(p_147747_2_ + d21, p_147747_4_ + 1.0D, d19, d7, d6);
        tessellator.addVertexWithUV(d17, p_147747_4_ + 1.0D, p_147747_6_ + d21, d5, d6);
        tessellator.addVertexWithUV(d17 + p_147747_8_, p_147747_4_ + 0.0D, p_147747_6_ + d21 + p_147747_10_, d5, d8);
        tessellator.addVertexWithUV(d18 + p_147747_8_, p_147747_4_ + 0.0D, p_147747_6_ + d21 + p_147747_10_, d7, d8);
        tessellator.addVertexWithUV(d18, p_147747_4_ + 1.0D, p_147747_6_ + d21, d7, d6);
        tessellator.addVertexWithUV(d18, p_147747_4_ + 1.0D, p_147747_6_ - d21, d5, d6);
        tessellator.addVertexWithUV(d18 + p_147747_8_, p_147747_4_ + 0.0D, p_147747_6_ - d21 + p_147747_10_, d5, d8);
        tessellator.addVertexWithUV(d17 + p_147747_8_, p_147747_4_ + 0.0D, p_147747_6_ - d21 + p_147747_10_, d7, d8);
        tessellator.addVertexWithUV(d17, p_147747_4_ + 1.0D, p_147747_6_ - d21, d7, d6);
    }

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}
	
	
	
}
