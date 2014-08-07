package com.disney.customcraft.materials;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;

public enum FluidMaterial {
	OIL(MapColor.blackColor, 3000, 6000, 0, 2),
	FUEL(MapColor.sandColor, 3000, 6000, 0, 4),
	COOLANT(MapColor.iceColor, 3000, 6000, 0, 4);

    private final MaterialFluid material;
    private final int viscosity;
    private final int density;
    private final int luminosity;
    private final int quanta;

    private FluidMaterial(MapColor color, int viscosity, int density, int luminosity, int quanta) {
        this.material = new MaterialFluid(color);
        this.viscosity = viscosity;
        this.density = density;
        this.luminosity = luminosity;
        this.quanta = quanta;
    }

    public MaterialFluid getMaterial() {
    	return material;
    }
    
    public int getViscosity() {
    	return viscosity;
    }
    
    public int getDensity() {
        return density;
    }
    
    public int getLuminosity() {
        return luminosity;
    }
    
    public int getQuanta() {
        return quanta;
    }
	
	
	public class MaterialFluid extends MaterialLiquid {
		
		public MaterialFluid(MapColor p_i2114_1_) {
			super(p_i2114_1_);
		}
		
	}

}
