package pokecube.legends.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import pokecube.legends.init.DimensionInit;

public class TeleportUltraSpace extends Teleporter
{
    public TeleportUltraSpace(WorldServer worldIn)
    {
        super(worldIn);
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float rotationYaw)
    {
        BlockPos entityPos = entity.getPosition();

        entity.motionX = 0;
        entity.motionZ = 0;

        int destY = entityPos.getY();

        // This should always be the case, but check anyway...
        if (entity.getEntityWorld() instanceof WorldServer)
        {
            WorldServer old = (WorldServer) entity.getEntityWorld();
            WorldServer dest;
            // This transfers between dim 0 and ultraspace.

            // In ultraspace, so going to overworld.
            if (old.provider.getDimension() == DimensionInit.ULTRASPACE.getId())
            {
                dest = old.getMinecraftServer().getWorld(0);
            }
            // Otherwise going to ultraspace
            else
            {
                dest = old.getMinecraftServer().getWorld(DimensionInit.ULTRASPACE.getId());
            }
            // forces the chunk to load
            dest.getChunkFromBlockCoords(entityPos);
            // finds surface height for location.
            destY = dest.getHeight(entityPos.getX(), entityPos.getZ()) + 2;
        }

        if (entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP) entity).connection.setPlayerLocation(entityPos.getX() + 0.5, destY + 0.5,
                    entityPos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
        }
        else
        {
            entity.setLocationAndAngles(entityPos.getX() + 0.5, destY + 0.5, entityPos.getZ() + 0.5, entity.rotationYaw,
                    entity.rotationPitch);
        }
        return true;
    }

    @Override
    public boolean makePortal(Entity entityIn)
    {
        return true;
    }

    @Override
    public void removeStalePortalLocations(long worldTime)
    {
    }
}