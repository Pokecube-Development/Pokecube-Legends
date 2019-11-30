package pokecube.legends.handlers;	

import net.minecraft.world.Teleporter;	
import net.minecraft.world.WorldServer;	
import pokecube.legends.init.BlockInit;	
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;	
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;	
import net.minecraft.entity.Entity;	
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;	
import net.minecraft.util.math.ChunkPos;	
import net.minecraft.util.math.MathHelper;	

public class TeleportUltraSpace extends Teleporter 	
{	
	private final Long2ObjectMap<TeleportUltraSpace.PortalPosition> destinationCache = new Long2ObjectOpenHashMap<>(4096);	

	public TeleportUltraSpace(WorldServer worldIn) {	
		super(worldIn);	
	}	

	/*@Override	
    public void placeInPortal(Entity entityIn, float rotationYaw)	
        {	
            if (this.world.provider.getDimensionType().getId() != 2)	
            {	
                if (!this.placeInExistingPortal(entityIn, rotationYaw))	
                {	
                    this.makePortal(entityIn);	
                    this.placeInExistingPortal(entityIn, rotationYaw);	

                }	
            }	
            else	
            {	
                int i = MathHelper.floor(entityIn.posX);	
                int j = MathHelper.floor(entityIn.posY) - 1;	
                int k = MathHelper.floor(entityIn.posZ);	
                //int l = 1;	
                //int i1 = 0;	

                for (int j1 = -2; j1 <= 2; ++j1)	
                {	
                    for (int k1 = -2; k1 <= 2; ++k1)	
                    {	
                        for (int l1 = -1; l1 < 3; ++l1)	
                        {	
                            int i2 = i + k1 * 1 + j1 * 0;	
                            int j2 = j + l1;	
                            int k2 = k + k1 * 0 - j1 * 1;	
                            boolean flag = l1 < 0;	
                            this.world.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.STONE.getDefaultState() : Blocks.AIR.getDefaultState());	
                        }	
                    }	
                }	

                entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);	
                entityIn.motionX = 0.0D;	
                entityIn.motionY = 0.0D;	
                entityIn.motionZ = 0.0D;	
            }	
        }	*/

    @Override	
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {	
        double distance = -1;	
        BlockPos entityPos = entity.getPosition();	
        long chunkPos = ChunkPos.asLong(MathHelper.floor(entity.posX), MathHelper.floor(entity.posZ));	

        if (this.destinationCache.containsKey(chunkPos)) {	
        	PortalPosition portalPosition = this.destinationCache.get(chunkPos);	
            distance = 0;	
            entityPos = portalPosition;	
            portalPosition.lastUpdateTime = this.world.getTotalWorldTime();	
        } else {	
            for (int x = -128; x <= 128; x++) {	
                BlockPos portalPos;	
                for (int z = -128; z <= 128; z++) {	
                    for (BlockPos blockpos = entityPos.add(x, this.world.getActualHeight() - 1 - entityPos.getY(), z); blockpos.getY() >= 0; blockpos = portalPos) {	
                        portalPos = blockpos.down();	

                        if (this.world.getBlockState(blockpos).getBlock() == BlockInit.ULTRASPACE_PORTAL) {	
                            while (this.world.getBlockState(portalPos = blockpos.down()).getBlock() == BlockInit.ULTRASPACE_PORTAL) {	
                                blockpos = portalPos;	
                            }	
                            double newDistance = blockpos.distanceSq(entityPos);	
                            if (distance < 0 || newDistance < distance) {	
                                distance = newDistance;	
                                entityPos = blockpos;	
                            }	
                        }	
                    }	
                }	
            }	
            if (distance >= 0) {	
                this.destinationCache.put(chunkPos, new PortalPosition(entityPos, this.world.getTotalWorldTime()));	
            }	
        }	

        if (distance >= 0) {	
            entity.motionX = 0;	
            entity.motionZ = 0;	

            if (entity instanceof EntityPlayerMP) {	
                ((EntityPlayerMP) entity).connection.setPlayerLocation(entityPos.getX() + 0.5, entityPos.getY() + 0.5, entityPos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);	
            } else {	
                entity.setLocationAndAngles(entityPos.getX() + 0.5, entityPos.getY() + 0.5, entityPos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);	
            }	
            return true;	
        } else {	
            return false;	
        }	
    }	

    @Override	
    public boolean makePortal(Entity entityIn) {	
        return true;	
    }	

    @Override	
    public void removeStalePortalLocations(long worldTime) {	
    }	
}