package com.disney.customcraft.testing;

import java.util.List;

import com.disney.customcraft.CustomCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

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
		
		setPosition(x, y, z);
		setSize(0.98F, 4F);
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
	public AxisAlignedBB getCollisionBox(Entity p_70114_1_)
    {
        return p_70114_1_.boundingBox;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox()
    {
		return boundingBox;
    }
	
	@Override
	public boolean canBeCollidedWith()
    {
        return !this.isDead;
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
        return (double)this.height * 0.0D + 1.9D;
    }
	
	@Override
	public boolean shouldRiderSit() {
        return false;
    }
	
	@Override
	public void onUpdate() {
		//super.onUpdate();
		
		boolean oldthrust;
		
		if (!this.worldObj.isRemote)
		{
			if(thrust) {
				moveEntity(0, 0.1, 0);
			} else {
				if(!onGround) {
					moveEntity(0, -0.1, 0);
            	}
			}
		} else {
			oldthrust = thrust;
			
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
	        {
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
	        }
			
			if(oldthrust != thrust) {
        		CustomCraft.networkHandler.sendToServer(new PacketEntity(getEntityId(), thrust));
        	}
		}
		
		//this.prevPosX = this.posX;
        //this.prevPosY = this.posY;
        //this.prevPosZ = this.posZ;
	}
	
	public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_)
    {
        this.posX = p_70107_1_;
        this.posY = p_70107_3_;
        this.posZ = p_70107_5_;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.boundingBox.setBounds(p_70107_1_ - (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize + (double)f1, p_70107_5_ + (double)f);
        
        updateRiderPosition();
    }
	
	@SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        this.setRotation(p_70056_7_, p_70056_8_);
        /*List list = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.contract(0.03125D, 0.0D, 0.03125D));

        if (!list.isEmpty())
        {
            double d3 = 0.0D;

            for (int j = 0; j < list.size(); ++j)
            {
                AxisAlignedBB axisalignedbb = (AxisAlignedBB)list.get(j);

                if (axisalignedbb.maxY > d3)
                {
                    d3 = axisalignedbb.maxY;
                }
            }

            p_70056_3_ += d3 - this.boundingBox.minY;
            this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        }*/
    }
	
	public static Entity transferEntityToDimension(Entity entity, int dimensionID, WorldServer world, EntityTest rocket) {
		if (!world.isRemote) {
			GalacticraftCore.packetPipeline.sendToAll(new PacketSimple(EnumSimplePacket.C_UPDATE_PLANETS_LIST, WorldUtil.getPlanetList()));
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (server != null) {
				final WorldServer worldServer = server.worldServerForDimension(dimensionID);
				if (worldServer == null) {
					System.err.println("Cannot Transfer Entity to Dimension: Could not get World for Dimension " + dimensionID);
					return null;
				}
				return WorldUtil.teleportEntity(var6, entity, dimensionID, type, transferInv, ridingRocket);
			}
		}
		return null;
	}
	
	private static Entity teleportEntity(World worldNew, Entity entity, int dimID, EntityTest ridingRocket) {
		if(entity.ridingEntity != null	&& entity.ridingEntity instanceof EntityTest) {
			entity.mountEntity(entity.ridingEntity);
		}
		
		boolean dimChange = entity.worldObj != worldNew;
		entity.worldObj.updateEntityWithOptionalForce(entity, false);
		
		if(rockete != null) {
			
		}
		
		int oldDimID = entity.worldObj.provider.dimensionId;
		if (ridingRocket != null) {
			NBTTagCompound nbt = new NBTTagCompound();
			ridingRocket.isDead = false;
			ridingRocket.riddenByEntity = null;
			ridingRocket.writeToNBTOptional(nbt);
			((WorldServer) ridingRocket.worldObj).getEntityTracker()
					.removeEntityFromAllTrackingPlayers(ridingRocket);
			ridingRocket.worldObj.loadedEntityList.remove(ridingRocket);
			ridingRocket.worldObj.onEntityRemoved(ridingRocket);
			ridingRocket = (EntityAutoRocket) EntityList.createEntityFromNBT(
					nbt, worldNew);
			if (ridingRocket != null) {
				ridingRocket.setWaitForPlayer(true);
				if (ridingRocket instanceof IWorldTransferCallback) {
					((IWorldTransferCallback) ridingRocket)
							.onWorldTransferred(worldNew);
				}
			}
		}
		if (dimChange) {
			if (entity instanceof GCEntityPlayerMP) {
				player = (GCEntityPlayerMP) entity;
				World worldOld = player.worldObj;
				if (ConfigManagerCore.enableDebug) {
					GCLog.info("DEBUG: Attempting to remove player from old dimension "
							+ oldDimID);
					((WorldServer) worldOld).getPlayerManager().removePlayer(
							player);
					GCLog.info("DEBUG: Successfully removed player from old dimension "
							+ oldDimID);
				} else
					((WorldServer) worldOld).getPlayerManager().removePlayer(
							player);
				player.closeScreen();
				player.getPlayerStats().usingPlanetSelectionGui = false;
				player.dimension = dimID;
				if (ConfigManagerCore.enableDebug) {
					GCLog.info("DEBUG: Sending respawn packet to player for dim "
							+ dimID);
				}
				player.playerNetServerHandler.sendPacket(new S07PacketRespawn(
						dimID, player.worldObj.difficultySetting,
						player.worldObj.getWorldInfo().getTerrainType(),
						player.theItemInWorldManager.getGameType()));
				if (worldNew.provider instanceof WorldProviderOrbit) {
					((WorldProviderOrbit) worldNew.provider)
							.sendPacketsToClient(player);
					if (WorldUtil.registeredSpaceStations.contains(dimID))
					// TODO This has never been effective before due to the
					// earlier bug - what does it actually do?
					{
						NBTTagCompound var2 = new NBTTagCompound();
						SpaceStationWorldData.getStationData(worldNew, dimID,
								player).writeToNBT(var2);
						GalacticraftCore.packetPipeline
								.sendTo(new PacketSimple(
										EnumSimplePacket.C_UPDATE_SPACESTATION_DATA,
										new Object[] { dimID, var2 }), player);
					}
				}
				worldOld.playerEntities.remove(player);
				worldOld.updateAllPlayersSleepingFlag();
				if (player.addedToChunk
						&& worldOld.getChunkProvider().chunkExists(
								player.chunkCoordX, player.chunkCoordZ)) {
					Chunk chunkOld = worldOld.getChunkFromChunkCoords(
							player.chunkCoordX, player.chunkCoordZ);
					chunkOld.removeEntity(player);
					chunkOld.isModified = true;
				}
				worldOld.loadedEntityList.remove(player);
				worldOld.onEntityRemoved(player);
				worldNew.spawnEntityInWorld(entity);
				entity.setWorld(worldNew);
				spawnPos = type.getPlayerSpawnLocation(
						(WorldServer) entity.worldObj, player);
				ChunkCoordIntPair pair = worldNew.getChunkFromChunkCoords(
						spawnPos.intX(), spawnPos.intZ())
						.getChunkCoordIntPair();
				if (ConfigManagerCore.enableDebug) {
					GCLog.info("DEBUG: Loading first chunk in new dimension.");
				}
				((WorldServer) worldNew).theChunkProviderServer.loadChunk(
						pair.chunkXPos, pair.chunkZPos);
				// entity.setLocationAndAngles(spawnPos.x, spawnPos.y,
				// spawnPos.z, entity.rotationYaw, entity.rotationPitch);
				worldNew.updateEntityWithOptionalForce(entity, false);
				entity.setLocationAndAngles(spawnPos.x, spawnPos.y, spawnPos.z,
						entity.rotationYaw, entity.rotationPitch);
				player.mcServer.getConfigurationManager().func_72375_a(player,
						(WorldServer) worldNew);
				player.playerNetServerHandler.setPlayerLocation(spawnPos.x,
						spawnPos.y, spawnPos.z, entity.rotationYaw,
						entity.rotationPitch);
				// worldNew.updateEntityWithOptionalForce(entity, false);
				GCLog.info("Server attempting to transfer player "
						+ player.getGameProfile().getName() + " to dimension "
						+ worldNew.provider.dimensionId);
				player.theItemInWorldManager.setWorld((WorldServer) worldNew);
				player.mcServer.getConfigurationManager()
						.updateTimeAndWeatherForPlayer(player,
								(WorldServer) worldNew);
				player.mcServer.getConfigurationManager().syncPlayerInventory(
						player);
				for (Object o : player.getActivePotionEffects()) {
					PotionEffect var10 = (PotionEffect) o;
					player.playerNetServerHandler
							.sendPacket(new S1DPacketEntityEffect(player
									.getEntityId(), var10));
				}
				// player.playerNetServerHandler.sendPacketToPlayer(new
				// Packet43Experience(player.experience, player.experienceTotal,
				// player.experienceLevel));
			} else
			// Non-player entity transfer i.e. it's an EntityCargoRocket
			{
				WorldUtil.removeEntityFromWorld(entity.worldObj, entity, true);
				NBTTagCompound nbt = new NBTTagCompound();
				entity.isDead = false;
				entity.writeToNBTOptional(nbt);
				entity.isDead = true;
				entity = EntityList.createEntityFromNBT(nbt, worldNew);
				if (entity == null) {
					return null;
				}
				if (entity instanceof IWorldTransferCallback) {
					((IWorldTransferCallback) entity)
							.onWorldTransferred(worldNew);
				}
				worldNew.spawnEntityInWorld(entity);
				entity.setWorld(worldNew);
				worldNew.updateEntityWithOptionalForce(entity, false);
			}
		} else {
			// Same dimension player transfer
			if (entity instanceof GCEntityPlayerMP) {
				player = (GCEntityPlayerMP) entity;
				player.closeScreen();
				player.getPlayerStats().usingPlanetSelectionGui = false;
				worldNew.updateEntityWithOptionalForce(entity, false);
				spawnPos = type.getPlayerSpawnLocation(
						(WorldServer) entity.worldObj, (EntityPlayerMP) entity);
				player.playerNetServerHandler.setPlayerLocation(spawnPos.x,
						spawnPos.y, spawnPos.z, entity.rotationYaw,
						entity.rotationPitch);
				entity.setLocationAndAngles(spawnPos.x, spawnPos.y, spawnPos.z,
						entity.rotationYaw, entity.rotationPitch);
				worldNew.updateEntityWithOptionalForce(entity, false);
				GCLog.info("Server attempting to transfer player "
						+ player.getGameProfile().getName()
						+ " within same dimension "
						+ worldNew.provider.dimensionId);
			}
			// Cargo rocket does not needs its location setting here, it will do
			// that itself
		}
		// Update PlayerStatsGC
		if (player != null) {
			GCPlayerStats playerStats = player.getPlayerStats();
			if (ridingRocket == null
					&& type.useParachute()
					&& playerStats.extendedInventory.getStackInSlot(4) != null
					&& playerStats.extendedInventory.getStackInSlot(4)
							.getItem() instanceof ItemParaChute) {
				GCPlayerHandler.setUsingParachute(player, playerStats, true);
			} else {
				GCPlayerHandler.setUsingParachute(player, playerStats, false);
			}
			if (playerStats.rocketStacks != null
					&& playerStats.rocketStacks.length > 0) {
				for (int stack = 0; stack < playerStats.rocketStacks.length; stack++) {
					if (transferInv) {
						if (playerStats.rocketStacks[stack] == null) {
							if (stack == playerStats.rocketStacks.length - 1) {
								if (playerStats.rocketItem != null) {
									playerStats.rocketStacks[stack] = new ItemStack(
											playerStats.rocketItem, 1,
											playerStats.rocketType);
								}
							} else if (stack == playerStats.rocketStacks.length - 2) {
								playerStats.rocketStacks[stack] = playerStats.launchpadStack;
								playerStats.launchpadStack = null;
							}
						}
					} else {
						playerStats.rocketStacks[stack] = null;
					}
				}
			}
			if (transferInv && playerStats.chestSpawnCooldown == 0) {
				playerStats.chestSpawnVector = type.getParaChestSpawnLocation(
						(WorldServer) entity.worldObj, player, new Random());
				playerStats.chestSpawnCooldown = 200;
			}
		}
		// If in a rocket (e.g. with launch controller) set the player to the
		// rocket's position instead of the player's spawn position
		if (ridingRocket != null) {
			entity.setPositionAndRotation(ridingRocket.posX, ridingRocket.posY,
					ridingRocket.posZ, 0, 0);
			worldNew.updateEntityWithOptionalForce(entity, true);
			worldNew.spawnEntityInWorld(ridingRocket);
			ridingRocket.setWorld(worldNew);
			worldNew.updateEntityWithOptionalForce(ridingRocket, true);
			entity.ridingEntity = ridingRocket;
			ridingRocket.riddenByEntity = entity;
		} else if (spawnPos != null)
			entity.setLocationAndAngles(spawnPos.x, spawnPos.y, spawnPos.z,
					entity.rotationYaw, entity.rotationPitch);
		// Spawn in a lander if appropriate
		if (entity instanceof EntityPlayerMP) {
			FMLCommonHandler.instance().firePlayerChangedDimensionEvent(
					(EntityPlayerMP) entity, oldDimID, dimID);
			type.onSpaceDimensionChanged(worldNew, (EntityPlayerMP) entity,
					ridingRocket != null);
		}
		return entity;
	}

}
