package pokecube.legends.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import pokecube.core.PokecubeCore;
import pokecube.core.database.Database;
import pokecube.core.events.handlers.SpawnHandler;
import pokecube.core.events.handlers.SpawnHandler.ForbidReason;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.BlockInit;
import thut.api.maths.Vector3;

public class PortalSpawnHandler
{

    @SubscribeEvent
    public void TickEvent(WorldTickEvent evt)
    {
        if (evt.phase == Phase.END && evt.side != Side.CLIENT && !Database.spawnables.isEmpty())
        {
            if (evt.world.getTotalWorldTime() % PokecubeLegends.instance.ticksPerMirageSpawn == 0) tick(evt.world);
        }
    }

    public void tick(World world)
    {
        // Only do this on surface worlds.
        if (!world.provider.isSurfaceWorld()) return;

        List<Object> players = new ArrayList<Object>(world.playerEntities);
        if (players.size() < 1) return;
        Random rand = new Random();
        Entity player = (Entity) players.get(rand.nextInt(players.size()));
        int distance = PokecubeCore.core.getConfig().maxSpawnRadius;
        int dx = rand.nextInt(distance) - distance / 2;
        int dz = rand.nextInt(distance) - distance / 2;
        Vector3 v = Vector3.getNewVector().set(player).add(dx, 0, dz);
        v.x += dx;
        v.z += dz;
        v.y = world.getHeight((int) v.x, (int) v.z) + 3;
        // No spawning on void.
        if (v.y < 4) return;
        // No spawning near repels.
        if (SpawnHandler.getNoSpawnReason(world, v.intX(), v.intY(), v.intZ()) == ForbidReason.REPEL) return;
        // Only spawn if air.
        if (v.isAir(world)) world.setBlockState(v.getPos(), BlockInit.BLOCK_PORTALWARP.getDefaultState());

    }
}
