package com.disney.customcraft.testcasting;

import com.disney.customcraft.CustomItems;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCastingTable extends TileEntity implements IFluidHandler {

	private FluidTank fluidTank = new FluidTank(100);
	
	private int tick = 0;
	
	private Item pattern = null;
	private Item output = null;
	private Fluid fluid = null;
	
	private int progress = 0;
	
	public TileCastingTable() {
		pattern = CustomItems.pattern;
	}
	
	@Override
	public void updateEntity() {
		tick++;
		
		if(tick >= 20) {
			tick = 0;
			
			if(output == null) {
				if(fluid != null) {
					if(progress > 4) {
						progress = 0;
						
						fluid = null;
						output = CustomItems.ingotMulti;
					}
					progress++;
				}
			}
		}
		
		//worldObj.func_147479_m(xCoord, yCoord, zCoord);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        //markDirty();
	}
	
	public float getFluidRender() {
		return (float) (progress * 0.01);
	}
	
	public boolean addFluid(Fluid fluid) {
		if(this.fluid == null) {
			this.fluid = fluid;
			return true;
		}
		return false;
	}
	
	public void removeOutput() {
		output = null;
	}
	
	public boolean isOutput() {
		return output != null;
	}
	
	public Item getOutput() {
		return output;
	}
	
	public Item getPattern() {
		return pattern;
	}
	
	public Fluid getFluid() {
		return fluid;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if(fluid != null) {
			nbt.setString("FluidName", fluid.getName());
		} else {
			nbt.setString("FluidName", "empty");
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		String fluidName = nbt.getString("FluidName");
		if(fluidName.isEmpty() || fluidName.equals("empty")) {
			fluid = null;
		} else {
			fluid = FluidRegistry.getFluid(fluidName);
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null) return 0;
		if(resource.amount < 100) return 0;
		if(doFill && !canFill(from, resource.getFluid())) return 0;
		if(output != null || fluid != null) return 0;
		
		fluid = resource.getFluid();
		
		return 100;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(fluid == FluidRegistry.LAVA) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { fluidTank.getInfo() };
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

}