package pokecube.legends.conditions;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import pokecube.core.database.Pokedex;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.stats.ISpecialCaptureCondition;
import pokecube.core.database.stats.ISpecialSpawnCondition;
import pokecube.core.database.stats.SpecialCaseRegister;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.handlers.GeneProtector;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ItemInit;
import pokecube.legends.spawns.LegendarySpawn;
import thut.lib.CompatParser.ClassFinder;

public class LegendaryConditions
{
    public static List<PokedexEntry> entries = Lists.newArrayList();

    @SuppressWarnings("unchecked")
    public LegendaryConditions()
    {
    	//Registring Event Lengendary Spawns

        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("ho-oh", ItemInit.LEGENDARYORB, BlockInit.LEGENDARY_SPAWN));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("lugia", ItemInit.OCEANORB, BlockInit.LEGENDARY_SPAWN));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("celebi", ItemInit.GREENORB, BlockInit.LEGENDARY_SPAWN));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("registeel", ItemInit.STEELCORE, BlockInit.REGISTEEL_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("regirock", ItemInit.ROCKCORE, BlockInit.REGIROCK_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("regice", ItemInit.ICECORE, BlockInit.REGICE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("regigigas", ItemInit.REGIS_ORB, BlockInit.REGIGIGA_CORE));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("groudon", ItemInit.REDORB, BlockInit.LEGENDARY_SPAWN));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("kyogre", ItemInit.BLUEORB, BlockInit.LEGENDARY_SPAWN));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("arceus", ItemInit.AZURE_FLUTE, BlockInit.TIMESPACE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("palkia", ItemInit.LUSTROUSORB, BlockInit.TIMESPACE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("dialga", ItemInit.ADAMANTORB, BlockInit.TIMESPACE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("reshiram", ItemInit.LIGHTSTONE, BlockInit.TIMESPACE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("zekrom", ItemInit.DARKSTONE, BlockInit.TIMESPACE_CORE));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("heatran", ItemInit.MAGMA_CORE, Blocks.MAGMA));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("keldeo", ItemInit.RAINBOW_SWORD, BlockInit.KELDEO_CORE));

        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("landorusincarnate", ItemInit.ORANGE_RUNE, BlockInit.NATURE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("thundurusincarnate", ItemInit.BLUE_RUNE, BlockInit.NATURE_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("tornadusincarnate", ItemInit.GREEN_RUNE, BlockInit.NATURE_CORE));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("victini", ItemInit.EMBLEM, BlockInit.VICTINI_CORE));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("xerneas", ItemInit.LIFEORB, BlockInit.XERNEAS_CORE));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("yveltal", ItemInit.DESTRUCTORB, BlockInit.YVELTAL_CORE));
        
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("zacian", ItemInit.RSWORD, BlockInit.LEGENDARY_SPAWN));
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn("zamazenta", ItemInit.RSHIELD, BlockInit.LEGENDARY_SPAWN));
        
        
        //Register the thng that prevents genetic modification of protected mobs
        MinecraftForge.EVENT_BUS.register(new GeneProtector());
        
        List<Class<?>> foundClasses;
        List<Class<? extends Condition>> conditionclasses = Lists.newArrayList();
        try
        {
            foundClasses = ClassFinder.find(Condition.class.getPackage().getName());
            int num = 0;
            for (Class<?> candidateClass : foundClasses)
            {
                if (Condition.class.isAssignableFrom(candidateClass) && candidateClass != Condition.class)
                {
                    conditionclasses.add((Class<? extends Condition>) candidateClass);
                    num++;
                }
            }
            if (PokecubeMod.debug) PokecubeMod.log("Detected " + num + " Legendary Conditions.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        int num = 0;
        for (Class<? extends Condition> c : conditionclasses)
        {
            try
            {
                Condition cond = c.newInstance();
                PokedexEntry e = cond.getEntry();
                if (Pokedex.getInstance().isRegistered(e))
                {
                    SpecialCaseRegister.register(e.getName(), (ISpecialCaptureCondition) cond);
                    SpecialCaseRegister.register(e.getName(), (ISpecialSpawnCondition) cond);
                    num++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        PokecubeMod.log("Registered " + num + " Legendary Conditions.");
    }
}
