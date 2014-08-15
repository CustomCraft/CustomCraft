package com.disney.customcraft.handlers.proxy;

import net.minecraft.block.Block;

import com.disney.customcraft.block.BlockOilTrail;
import com.disney.customcraft.block.BlockTorchOff;
import com.disney.customcraft.block.BlockTorchOn;
import com.disney.customcraft.block.renderer.RendererOilTrail;
import com.disney.customcraft.block.renderer.RendererTorch;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	private int renderIDTorch = -1;
	private int renderIDOil = -1;
	
	@Override
	public void registerSimpleRenderer(Block block) {
		if(block instanceof BlockTorchOn || block instanceof BlockTorchOff) {
			if(renderIDTorch == -1) {
				renderIDTorch = RenderingRegistry.getNextAvailableRenderId();
				RenderingRegistry.registerBlockHandler(new RendererTorch(renderIDTorch));
			}
		} else if(block instanceof BlockOilTrail) {
			if(renderIDOil == -1) {
				renderIDOil = RenderingRegistry.getNextAvailableRenderId();
				RenderingRegistry.registerBlockHandler(new RendererOilTrail(renderIDOil));
			}
		}
	}
	
	@Override
	public int getRendererID(Block block) {
		if(block instanceof BlockTorchOn || block instanceof BlockTorchOff) {
			return renderIDTorch;
		} else if(block instanceof BlockOilTrail) {
			return renderIDOil;
		}
		return -1;
	}

}
