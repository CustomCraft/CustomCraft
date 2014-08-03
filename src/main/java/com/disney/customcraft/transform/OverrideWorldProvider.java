package com.disney.customcraft.transform;

import net.minecraft.world.WorldProviderSurface;

import com.disney.customcraft.handlers.config.Config;

public class OverrideWorldProvider extends WorldProviderSurface {
	
	public float calculateCelestialAngle(long par1, float par3)
    {
		if (Config.nomadLongerDays) {
			int j = (int)(par1 % Config.nomadDayLength);
	        float f1 = ((float)j + par3) / Config.nomadDayLength - 0.25F;
	
	        if (f1 < 0.0F)
	        {
	            ++f1;
	        }
	
	        if (f1 > 1.0F)
	        {
	            --f1;
	        }
	
	        float f2 = f1;
	        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
	        f1 = f2 + (f1 - f2) / 3.0F;
	        return f1;
		} else {
	        int j = (int)(par1 % 24000L);
	        float f1 = ((float)j + par3) / 24000.0F - 0.25F;
	
	        if (f1 < 0.0F)
	        {
	            ++f1;
	        }
	
	        if (f1 > 1.0F)
	        {
	            --f1;
	        }
	
	        float f2 = f1;
	        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
	        f1 = f2 + (f1 - f2) / 3.0F;
	        return f1;
		}
    }

    public int getMoonPhase(long par1)
    {
    	if (Config.nomadLongerDays) {
    		return (int)(par1 / Config.nomadDayLength % 8L + 8L) % 8;
		} else {
			return (int)(par1 / 24000L % 8L + 8L) % 8;
		}
    }

}
