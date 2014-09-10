package com.disney.customcraft.testing;

import java.util.Iterator;
import java.util.List;

import com.disney.customcraft.CustomCraft;
import com.disney.customcraft.handlers.LogHandler;

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
import net.minecraft.world.Teleporter;
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
		
		onGround = false;
		thrust = false;
		
		//yOffset = this.height / 2.0F;
	}
	
	public EntityTest(EntityPlayerMP player)
    {
        this(player.worldObj, player.posX, player.posY, player.posZ);

        //this.containedItems = new ItemStack[player.getPlayerStats().rocketStacks.length + 1];
        //this.fuelTank.setFluid(new FluidStack(GalacticraftCore.fluidFuel, player.getPlayerStats().fuelLevel));

        this.setPositionAndRotation(player.posX, player.posY, player.posZ, 0, 0);

        this.riddenByEntity = player;
        player.ridingEntity = this;
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
				if(posY > 10.0) {
					if(this.dimension == 4) {
						//transferEntityToDimension(this.riddenByEntity, 0, (WorldServer) this.worldObj, true, this);
						transferToDimension(this.riddenByEntity, 0);
					} else {
						transferToDimension(this.riddenByEntity, 4);
						//transferEntityToDimension(this.riddenByEntity, 4, (WorldServer) this.worldObj, true, this);
					}
				}
				else {
					moveEntity(0, 0.1, 0);
				}
				//transferToDimension(this.riddenByEntity, worldObj.provider.dimensionId);
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
		System.out.print(p_70107_3_ + "\n");
		
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
				
			}
		}
	}
	
	public void teleportPlayer(World oldWorld, World newWorld, Entity entity, Entity rocket, int dimensionID) {
		if(entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = ((EntityPlayerMP) entity);
			
			oldWorld.removeEntity(rocket);
			
			teleportPlayer(((EntityPlayerMP) entity), (WorldServer) newWorld, dimensionID);
			
			
			
			//((EntityPlayerMP) entity).mountEntity(null);
			
			//player.mcServer.getConfigurationManager().transferEntityToWorld(rocket, dimensionID, (WorldServer) oldWorld, (WorldServer) newWorld);
			
			//rocket.isDead = false;
			//rocket.riddenByEntity.ridingEntity = null;
			//rocket.riddenByEntity = null;
			
			//((WorldServer) rocket.worldObj).getEntityTracker().removeEntityFromAllTrackingPlayers(rocket);
			//rocket.worldObj.loadedEntityList.remove(rocket);
			//rocket.worldObj.onEntityRemoved(rocket);
			//rocket.isDead = true;
			
			
						
			//move the player
			//((EntityPlayerMP) entity).travelToDimension(dimensionID);
			
			
			//unmount the rocket
			//((EntityPlayerMP) entity).mountEntity(null);
			
			//delete the rocket
			//rocket.worldObj.removeEntity(rocket);
			
			player.capabilities.isFlying = false;
			
			/*EntityTest newRocket = new EntityTest(newWorld, player.posX, player.posY, player.posZ);
			newRocket.setPosition(player.posX, player.posY, player.posZ);
			player.ridingEntity = newRocket;
			newRocket.riddenByEntity = player;

			newWorld.spawnEntityInWorld(newRocket);
			newRocket.setWorld(newWorld);
			newRocket.onGround = false;*/
			
			//newWorld.updateEntityWithOptionalForce(player, true);
			//newWorld.updateEntityWithOptionalForce(newRocket, true);
			
			
			
			//player.mountEntity(newRocket);
		}
	}
	
	public void teleportPlayer(EntityPlayerMP player, WorldServer newWorld, int dimension) {
		TestTeleporter teleporter = new TestTeleporter(newWorld);
		transferPlayerToDimension(player.mcServer, player, dimension, teleporter);
		//player.lastExperience = -1;
		//player.lastHealth = -1.0F;
		//player.lastFoodLevel = -1;
	}
	
	public static Entity transferEntityToDimension(Entity entity, int dimensionID, WorldServer world, boolean transferInv, EntityTest ridingRocket) {
		if (!world.isRemote) {
			MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();

			if (mcServer != null) {
				final WorldServer var6 = mcServer.worldServerForDimension(dimensionID);

				if (var6 == null) {
					System.err.println("Cannot Transfer Entity to Dimension: Could not get World for Dimension " + dimensionID);
					return null;
				}
				
				return teleportEntity(var6, entity, dimensionID, transferInv, ridingRocket);
			}
		}

		return null;
	}
	
	private static Entity teleportEntity(World worldNew, Entity entity, int dimID, boolean transferInv, EntityTest ridingRocket) {
		
		if (entity.ridingEntity != null && entity.ridingEntity instanceof EntityTest) {
			entity.mountEntity(entity.ridingEntity);
		}

		boolean dimChange = entity.worldObj != worldNew;
		entity.worldObj.updateEntityWithOptionalForce(entity, false);
		EntityPlayerMP player = null;
		Vec3 spawnPos = null; 
		int oldDimID = entity.worldObj.provider.dimensionId;

		if (ridingRocket != null) {
			NBTTagCompound nbt = new NBTTagCompound();
			ridingRocket.isDead = false;
			ridingRocket.riddenByEntity.ridingEntity = null;
			ridingRocket.riddenByEntity = null;
			ridingRocket.writeToNBTOptional(nbt);
			ridingRocket.isDead = false;

			((WorldServer) ridingRocket.worldObj).getEntityTracker().removeEntityFromAllTrackingPlayers(ridingRocket);
			ridingRocket.worldObj.loadedEntityList.remove(ridingRocket);
			ridingRocket.worldObj.onEntityRemoved(ridingRocket);

			ridingRocket = (EntityTest) EntityList.createEntityFromNBT(nbt, worldNew);

			if (ridingRocket != null) {
//??			ridingRocket.setWaitForPlayer(true);

//??				if (ridingRocket instanceof IWorldTransferCallback)
//??				{
//??					((IWorldTransferCallback) ridingRocket).onWorldTransferred(worldNew);
//??				}
			}
		}
		
		if (dimChange) {
			if (entity instanceof EntityPlayerMP) {
				player = (EntityPlayerMP) entity;
				World worldOld = player.worldObj;

				LogHandler.log("DEBUG: Attempting to remove player from old dimension "+oldDimID);
				((WorldServer) worldOld).getPlayerManager().removePlayer(player);
				LogHandler.log("DEBUG: Successfully removed player from old dimension "+oldDimID);

				player.closeScreen();
//??				player.getPlayerStats().usingPlanetSelectionGui = false;
				
				player.dimension = dimID;

				LogHandler.log("DEBUG: Sending respawn packet to player for dim " + dimID);

				player.playerNetServerHandler.sendPacket(new S07PacketRespawn(dimID, player.worldObj.difficultySetting, player.worldObj.getWorldInfo().getTerrainType(), player.theItemInWorldManager.getGameType()));

//??				if (worldNew.provider instanceof WorldProviderOrbit)
//??				{
//??					((WorldProviderOrbit) worldNew.provider).sendPacketsToClient(player);
//??					if (WorldUtil.registeredSpaceStations.contains(dimID))
						//TODO This has never been effective before due to the earlier bug - what does it actually do?
//??					{
//??						NBTTagCompound var2 = new NBTTagCompound();
//??						SpaceStationWorldData.getStationData(worldNew, dimID, player).writeToNBT(var2);
//??						GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_UPDATE_SPACESTATION_DATA, new Object[] { dimID, var2 }), player);
//??					}
//??				}

				worldOld.playerEntities.remove(player);
				worldOld.updateAllPlayersSleepingFlag();
				
				if (player.addedToChunk && worldOld.getChunkProvider().chunkExists(player.chunkCoordX, player.chunkCoordZ)) {
					Chunk chunkOld = worldOld.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ); 
					chunkOld.removeEntity(player);
					chunkOld.isModified = true;
				}
				worldOld.loadedEntityList.remove(player);
				worldOld.onEntityRemoved(player);

				worldNew.spawnEntityInWorld(entity);
				entity.setWorld(worldNew);

				spawnPos = Vec3.createVectorHelper(0.0, 900.0, 0.0);
//??				spawnPos = type.getPlayerSpawnLocation((WorldServer) entity.worldObj, player);
                ChunkCoordIntPair pair = worldNew.getChunkFromChunkCoords((int) Math.floor(spawnPos.xCoord), (int) Math.floor(spawnPos.zCoord)).getChunkCoordIntPair();

                LogHandler.log("DEBUG: Loading first chunk in new dimension.");

                ((WorldServer) worldNew).theChunkProviderServer.loadChunk(pair.chunkXPos, pair.chunkZPos);
				//entity.setLocationAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, entity.rotationYaw, entity.rotationPitch);
				worldNew.updateEntityWithOptionalForce(entity, false);
				entity.setLocationAndAngles(spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, entity.rotationYaw, entity.rotationPitch);

				player.mcServer.getConfigurationManager().func_72375_a(player, (WorldServer) worldNew);
				player.playerNetServerHandler.setPlayerLocation(spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, entity.rotationYaw, entity.rotationPitch);
				//worldNew.updateEntityWithOptionalForce(entity, false);

				LogHandler.log("Server attempting to transfer player " + player.getGameProfile().getName() + " to dimension " + worldNew.provider.dimensionId);

				player.theItemInWorldManager.setWorld((WorldServer) worldNew);
				player.mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(player, (WorldServer) worldNew);
				player.mcServer.getConfigurationManager().syncPlayerInventory(player);

				for (Object o : player.getActivePotionEffects())
				{
					PotionEffect var10 = (PotionEffect) o;
					player.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(player.getEntityId(), var10));
				}

				//	player.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(player.experience, player.experienceTotal, player.experienceLevel));
			} else { //Non-player entity transfer i.e. it's an EntityCargoRocket
				removeEntityFromWorld(entity.worldObj, entity, true);

				NBTTagCompound nbt = new NBTTagCompound();
				entity.isDead = false;
				entity.writeToNBTOptional(nbt);
				entity.isDead = true;
				entity = EntityList.createEntityFromNBT(nbt, worldNew);

				if (entity == null) {
					return null;
				}

//??				if (entity instanceof IWorldTransferCallback)
//??				{
//??					((IWorldTransferCallback) entity).onWorldTransferred(worldNew);
//??				}

				worldNew.spawnEntityInWorld(entity);
				entity.setWorld(worldNew);

				worldNew.updateEntityWithOptionalForce(entity, false);
			}			
		} else {
			//Same dimension player transfer
			if (entity instanceof EntityPlayerMP) {
				player = (EntityPlayerMP) entity;
				
				player.closeScreen();
//??				player.getPlayerStats().usingPlanetSelectionGui = false;
	
				worldNew.updateEntityWithOptionalForce(entity, false);

				spawnPos = Vec3.createVectorHelper(0.0, 900.0, 0.0);
				player.playerNetServerHandler.setPlayerLocation(spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, entity.rotationYaw, entity.rotationPitch);
				entity.setLocationAndAngles(spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, entity.rotationYaw, entity.rotationPitch);	
				worldNew.updateEntityWithOptionalForce(entity, false);

				LogHandler.log("Server attempting to transfer player " + player.getGameProfile().getName() + " within same dimension " + worldNew.provider.dimensionId);
			}
			
			//Cargo rocket does not needs its location setting here, it will do that itself
		}

		ridingRocket = null;
		//If in a rocket (e.g. with launch controller) set the player to the rocket's position instead of the player's spawn position
		if (ridingRocket != null) {
			entity.setPositionAndRotation(ridingRocket.posX, ridingRocket.posY, ridingRocket.posZ, 0, 0);
			worldNew.updateEntityWithOptionalForce(entity, true);

			worldNew.spawnEntityInWorld(ridingRocket);
			ridingRocket.setWorld(worldNew);

			worldNew.updateEntityWithOptionalForce(ridingRocket, true);
			entity.ridingEntity = ridingRocket;
			ridingRocket.riddenByEntity = entity;
		} else
			if (spawnPos != null) entity.setLocationAndAngles(spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, entity.rotationYaw, entity.rotationPitch);

		//Spawn in a lander if appropriate
		if (entity instanceof EntityPlayerMP) {
			FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayerMP) entity, oldDimID, dimID);
			teleportType(worldNew, (EntityPlayerMP) entity, ridingRocket != null);
		}

		return entity;
	}
	
	private static void removeEntityFromWorld(World var0, Entity var1, boolean directlyRemove)
	{
		if (var1 instanceof EntityPlayer) {
			final EntityPlayer var2 = (EntityPlayer) var1;
			var2.closeScreen();
			var0.playerEntities.remove(var2);
			var0.updateAllPlayersSleepingFlag();
			final int var3 = var1.chunkCoordX;
			final int var4 = var1.chunkCoordZ;

			if (var1.addedToChunk && var0.getChunkProvider().chunkExists(var3, var4)) {
				var0.getChunkFromChunkCoords(var3, var4).removeEntity(var1);
				var0.getChunkFromChunkCoords(var3, var4).isModified = true;
			}

			if (directlyRemove)	{
				var0.loadedEntityList.remove(var1);
				var0.onEntityRemoved(var1);
			}
		}

		var1.isDead = false;
	}
	
	private static void teleportType(World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
		if (!ridingAutoRocket && player instanceof EntityPlayerMP) {

			if (player.capabilities.isFlying) {
				player.capabilities.isFlying = false;
			}

			EntityTest lander = new EntityTest(player);
            lander.setPosition(player.posX, player.posY, player.posZ);

			if (!newWorld.isRemote)
			{
				newWorld.spawnEntityInWorld(lander);
			}
		}
	}
	
	public void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP p_72356_1_, int p_72356_2_, Teleporter teleporter) {
        int j = p_72356_1_.dimension;
        WorldServer worldserver = mcServer.worldServerForDimension(p_72356_1_.dimension);
        p_72356_1_.dimension = p_72356_2_;
        WorldServer worldserver1 = mcServer.worldServerForDimension(p_72356_1_.dimension);
        p_72356_1_.playerNetServerHandler.sendPacket(new S07PacketRespawn(p_72356_1_.dimension, p_72356_1_.worldObj.difficultySetting, p_72356_1_.worldObj.getWorldInfo().getTerrainType(), p_72356_1_.theItemInWorldManager.getGameType()));
        
        worldserver.removeEntity(p_72356_1_);
        //worldserver.removePlayerEntityDangerously(p_72356_1_);
        
        p_72356_1_.isDead = false;
        
        p_72356_1_.setWorld(worldserver1);
        mcServer.getConfigurationManager().func_72375_a(p_72356_1_, worldserver);
        mcServer.getConfigurationManager().transferEntityToWorld(p_72356_1_, j, worldserver, worldserver1, teleporter);
        //mcServer.getConfigurationManager().func_72375_a(p_72356_1_, worldserver);
        p_72356_1_.playerNetServerHandler.setPlayerLocation(p_72356_1_.posX, p_72356_1_.posY, p_72356_1_.posZ, p_72356_1_.rotationYaw, p_72356_1_.rotationPitch);
        p_72356_1_.theItemInWorldManager.setWorld(worldserver1);
        mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(p_72356_1_, worldserver1);
        mcServer.getConfigurationManager().syncPlayerInventory(p_72356_1_);
        Iterator iterator = p_72356_1_.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            p_72356_1_.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(p_72356_1_.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(p_72356_1_, j, p_72356_2_);
    }

}
