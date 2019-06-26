package pokecube.legends.handlers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.init.BiomeInit;
import thut.api.terrain.TerrainManager;
import thut.api.terrain.TerrainSegment;

@EventBusSubscriber
public class EventHandlerSetter 
{
	public static class TemporalAreaSetter
    {
        Map<Integer, List<BlockPos>> toProcess = Maps.newHashMap();

        public TemporalAreaSetter()
        {
            MinecraftForge.EVENT_BUS.register(this);
        }

        @SubscribeEvent
        public void tickEvent(WorldTickEvent evt)
        {
            if (evt.phase == Phase.END && evt.side != Side.CLIENT)
            {
                List<BlockPos> thisTick = toProcess.get(evt.world.provider.getDimension());
                if (thisTick == null || thisTick.isEmpty()) return;
                int i = 0;
                int num = 0;

                for (i = 0; i < Math.min(1000, thisTick.size()); i++)
                {
                    BlockPos pos = thisTick.get(i);
                    TerrainSegment seg = TerrainManager.getInstance().getTerrain(evt.world, pos);
                    seg.setBiome(pos, BiomeInit.getType());
                    num = i + 1;
                }
                if (PokecubeMod.debug) PokecubeMod.log(Level.INFO, "Processed " + num + " blocks as meteor.");
                for (i = 0; i < Math.min(num, thisTick.size()); i++)
                    thisTick.remove(0);
            }
        }

        public void addBlocks(Collection<BlockPos> toAdd, int dimension)
        {
            List<BlockPos> blocks = toProcess.get(dimension);
            if (blocks == null) toProcess.put(dimension, blocks = Lists.newArrayList());
            blocks.addAll(toAdd);
        }

        public void clear()
        {
            toProcess.clear();
        }
    }
}
