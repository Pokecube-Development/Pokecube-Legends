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
import pokecube.core.events.SpawnEvent;
import pokecube.core.events.handlers.SpawnHandler;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.DimensionInit;
import thut.api.maths.Vector3;

public class WormHoleSpawnHandler
{
    @SubscribeEvent
    public void tickEvent(WorldTickEvent evt)
    {
        if (evt.phase == Phase.END && evt.side != Side.CLIENT && !Database.spawnables.isEmpty())
        {
            if (evt.world.getTotalWorldTime() % PokecubeLegends.instance.ticksPerPortalSpawn == 0)
                portalSpawnTick(evt.world);
        }
    }

    @SubscribeEvent
    /** @param event */
    public void spawnEventPick(SpawnEvent.Pick.Pre event)
    {

    }

    public void mobSpawnTick(World world)
    {
        // TODO put in the code for spawning UBs here relevant to the biome.
    }

    public void portalSpawnTick(World world)
    {
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
        v.y = world.getHeight((int) v.x, (int) v.z) + 10;
        if (v.isAir(world)) world.setBlockState(v.getPos(), BlockInit.ULTRASPACE_PORTAL.getDefaultState());

    }
}
