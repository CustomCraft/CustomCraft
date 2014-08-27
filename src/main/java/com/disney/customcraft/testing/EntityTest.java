package com.disney.customcraft.testing;

import java.util.Iterator;
import java.util.List;

import com.disney.customcraft.CustomCraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor;

public class EntityTest extends Entity {

	public boolean thrust = false;
	public double speed = 0.0;
	
	public EntityTest(World world) {
		super(world);
		
		preventEntitySpawning = true;
	}
	
	public EntityTest(World world, double x, double y, double z) {
		super(world);
		
		preventEntitySpawning = true;
		
		posX = MathHelper.floor_double(x) + 0.5;
		posZ = MathHelper.floor_double(z) + 0.5;
		setPosition(MathHelper.floor_double(x) + 0.5, MathHelper.floor_double(y), MathHelper.floor_double(z) + 0.5);
		setSize(0.8F, 1.8F);
		
		//yOffset = this.height / 2.0F;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox() {
		return null;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
		return p_70114_1_.boundingBox;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
        if(riddenByEntity != null && riddenByEntity instanceof EntityPlayer && riddenByEntity != player) {
            return true;
        } else if (riddenByEntity != null && riddenByEntity != player) {
            return false;
        } else {
            if (!worldObj.isRemote) {
                player.mountEntity(this);
            }

            return true;
        }
    }
	
	@Override
	public void updateRiderPosition() {
        if(riddenByEntity != null) {
        	this.riddenByEntity.setPosition(posX, posY + getMountedYOffset(), posZ);
        }
    }
	
	public double getMountedYOffset()
    {
        return (double)this.height * 0.0D + 1.6D;
    }
	
	@Override
	public boolean shouldRiderSit() {
        return false;
    }
	
	@Override
	public void onUpdate() {
		//super.onUpdate();
		
		boolean oldthrust;
		
		//check is on ground
		
		//moveEntity(0, -0.1, 0);
		if (!this.worldObj.isRemote)
		{
			if(thrust) {
				//moveEntity(0, 0.1, 0);
				//transferToDimension(this.riddenByEntity, worldObj.provider.dimensionId);
				transferToDimension(this.riddenByEntity, 4);
			} else {
				if(riddenByEntity == null) moveEntity(0, -0.8, 0);
				else moveEntity(0, -0.1, 0);
			}
			//CustomCraft.networkHandler.sendToAll(new PacketEntity(getEntityId(), thrust));
		} else {
			oldthrust = thrust;
			
			if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
	            EntityLivingBase entitylivingbase = (EntityLivingBase)this.riddenByEntity;
	            if(entitylivingbase.moveForward > 0) {
	            	thrust = true;
	            	moveEntity(0, 0.08, 0);
	            } else {
	            	thrust = false;
	            	if(!onGround) {
	            		moveEntity(0, -0.08, 0);
	            	}
	            }
	        } else if(!onGround) {
        		moveEntity(0, -0.78, 0);
        	}
			
			if(oldthrust != thrust) {
        		CustomCraft.networkHandler.sendToServer(new PacketEntity(getEntityId(), thrust));
        	}
		}
		
		prevPosX = this.posX;
        prevPosY = this.posY;
        prevPosZ = this.posZ;
	}
	
	@Override
	public void updateRidden() {
		super.updateRidden();
	}
	
	public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_)
    {
		System.out.print(p_70107_1_ + "\n");
		if(p_70107_1_ != -814) {
			System.out.print(p_70107_1_ + "\n");
		}
		
		//posX = p_70107_1_;
        posY = p_70107_3_;
        //posZ = p_70107_5_;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.boundingBox.setBounds(posX - (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize + (double)f1, p_70107_5_ + (double)f);
        
        updateRiderPosition();
    }
	
	@SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
		//if(riddenByEntity != null || !onGround) {
			this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
		//}
    }
	
	public void transferToDimension(Entity entity, int dimensionID) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server != null) {
			WorldServer worldServer = server.worldServerForDimension(dimensionID);
			if (worldServer != null && !worldServer.isRemote) {
				teleportPlayer(worldObj, worldServer, entity, this, dimensionID);
				thrust = false;
				onGround = false;
			}
		}
	}
	
	@Override
	public void mountEntity(Entity p_70078_1_) {
		super.mountEntity(p_70078_1_);
	}
	
	public void teleportPlayer(World oldWorld, World newWorld, Entity entity, Entity rocket, int dimensionID) {
		if (entity instanceof EntityPlayerMP && rocket instanceof EntityTest) {
			//EntityPlayerMP player = (EntityPlayerMP) entity;
			
			EntityTest newRocket = transferRocket((EntityTest)rocket, dimensionID);
			
			transferPlayer((EntityPlayerMP)entity, dimensionID);
			
			entity.setPositionAndRotation(0.0, 55.0, 0.0, 0, 0);
			newWorld.updateEntityWithOptionalForce(entity, true);
			
			newWorld.spawnEntityInWorld(newRocket);
			newRocket.setWorld(newWorld);
			newWorld.updateEntityWithOptionalForce(newRocket, true);
			
			entity.ridingEntity = newRocket;
			newRocket.riddenByEntity = entity;
		}
	}
	
	public EntityTest transferRocket(EntityTest rocket, int dimension) {
		EntityTest newRocket = null;
		if (rocket != null) {
			//NBTTagCompound nbt = new NBTTagCompound();
			rocket.isDead = false;
			rocket.riddenByEntity = null;
			//rocket.writeToNBTOptional(nbt);

			((WorldServer) rocket.worldObj).getEntityTracker().removeEntityFromAllTrackingPlayers(rocket);
			rocket.worldObj.loadedEntityList.remove(rocket);
			rocket.worldObj.onEntityRemoved(rocket);
			//rocket.worldObj.removeEntity(rocket);

			WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(dimension);
			newRocket = new EntityTest(world, 0.0, 55.0, 0.0);

			if(newRocket != null) {
				//rocket.setWaitForPlayer(true);

				//if(ridingRocket instanceof IWorldTransferCallback) {
				//	((IWorldTransferCallback) ridingRocket).onWorldTransferred(worldNew);
				//}
			}
		}
		return newRocket;
	}
	
	public void transferPlayer(EntityPlayerMP player, int dimensionNew) {
		MinecraftServer mcServer = MinecraftServer.getServer();
		
		int dimensionOld = player.dimension;
		WorldServer worldOld = mcServer.worldServerForDimension(dimensionOld);
		
		player.dimension = dimensionNew;
		WorldServer worldNew = mcServer.worldServerForDimension(dimensionNew);
		
	    player.playerNetServerHandler.sendPacket(new S07PacketRespawn(dimensionNew, player.worldObj.difficultySetting, player.worldObj.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));
	    
	    worldOld.removePlayerEntityDangerously(player);
	    player.isDead = false;
	    
	    player.setLocationAndAngles(0.0, 50.0, 0.0, player.rotationYaw, player.rotationPitch);
        //teleporter.placeInPortal(p_82448_1_, d3, d4, d5, f);
	    worldNew.spawnEntityInWorld(player);
	    worldNew.updateEntityWithOptionalForce(player, false);
	    
	    mcServer.getConfigurationManager().func_72375_a(player, worldOld);
	    
	    player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
	    
	    player.theItemInWorldManager.setWorld(worldNew);
	    
	    mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, worldNew);
	    mcServer.getConfigurationManager().syncPlayerInventory(player);
	    
	    Iterator iterator = player.getActivePotionEffects().iterator();
	    while (iterator.hasNext()) {
	        PotionEffect potioneffect = (PotionEffect)iterator.next();
	        player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), potioneffect));
	    }
	    FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, dimensionOld, dimensionNew);		
	}

}
