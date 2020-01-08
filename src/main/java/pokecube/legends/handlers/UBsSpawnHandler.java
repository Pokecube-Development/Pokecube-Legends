package pokecube.legends.handlers;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import pokecube.core.database.Database;
import pokecube.legends.PokecubeLegends;

public class UBsSpawnHandler
{
    @SubscribeEvent
    public void TickEvent(WorldTickEvent evt)
    {
        if (evt.phase == Phase.END && evt.side != Side.CLIENT && !Database.spawnables.isEmpty())
        {
            if (evt.world.getTotalWorldTime() % PokecubeLegends.instance.ticksPerUBPortalSpawn == 0) tick(evt.world);
        }
    }

    public void tick(World world)
    {
//        List<Object> players = new ArrayList<Object>(world.playerEntities);
//        if (players.size() < 1) return;
//        Random rand = new Random();
//        Entity player = (Entity) players.get(rand.nextInt(players.size()));
//        int distance = PokecubeCore.core.getConfig().maxSpawnRadius;
//        int dx = rand.nextInt(distance) - distance / 2;
//        int dz = rand.nextInt(distance) - distance / 2;
//        Vector3 v = Vector3.getNewVector().set(player).add(dx, 0, dz);
//        v.x += dx;
//        v.z += dz;
//        v.y = world.getHeight((int) v.x, (int) v.z) + 5;
//        if (v.isAir(world)) world.setBlockState(v.getPos(), BlockInit.BLOCK_UBPORTAL.getDefaultState());

    }
}
