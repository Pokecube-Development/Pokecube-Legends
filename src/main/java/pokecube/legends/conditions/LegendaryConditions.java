package pokecube.legends.conditions;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.MinecraftForge;
import pokecube.core.database.Pokedex;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.stats.ISpecialCaptureCondition;
import pokecube.core.database.stats.ISpecialSpawnCondition;
import pokecube.core.database.stats.SpecialCaseRegister;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.handlers.GeneProtector;
import pokecube.legends.spawns.ArceusSpawn;
import pokecube.legends.spawns.CelebiSpawn;
import pokecube.legends.spawns.DialgaSpawn;
import pokecube.legends.spawns.GroudonSpawn;
import pokecube.legends.spawns.HeatranSpawn;
import pokecube.legends.spawns.HoohSpawn;
import pokecube.legends.spawns.KeldeoSpawn;
import pokecube.legends.spawns.KyogreSpawn;
import pokecube.legends.spawns.LandorusSpawn;
import pokecube.legends.spawns.LugiaSpawn;
import pokecube.legends.spawns.PalkiaSpawn;
import pokecube.legends.spawns.RegiceSpawn;
import pokecube.legends.spawns.RegigigasSpawn;
import pokecube.legends.spawns.RegirockSpawn;
import pokecube.legends.spawns.RegisteelSpawn;
import pokecube.legends.spawns.ReshiramSpawn;
import pokecube.legends.spawns.ThundurusSpawn;
import pokecube.legends.spawns.TornadusSpawn;
import pokecube.legends.spawns.VictiniSpawn;
import pokecube.legends.spawns.XerneasSpawn;
import pokecube.legends.spawns.YveltalSpawn;
import pokecube.legends.spawns.ZekromSpawn;
import thut.lib.CompatParser.ClassFinder;

public class LegendaryConditions
{
    public static List<PokedexEntry> entries = Lists.newArrayList();

    @SuppressWarnings("unchecked")
    public LegendaryConditions()
    {
    	//Registring Event Lengendary Spawns
        MinecraftForge.EVENT_BUS.register(new HoohSpawn());
        MinecraftForge.EVENT_BUS.register(new LugiaSpawn());
        MinecraftForge.EVENT_BUS.register(new DialgaSpawn());
        MinecraftForge.EVENT_BUS.register(new RegisteelSpawn());
        MinecraftForge.EVENT_BUS.register(new RegirockSpawn());
        MinecraftForge.EVENT_BUS.register(new RegiceSpawn());
        MinecraftForge.EVENT_BUS.register(new RegigigasSpawn());
        MinecraftForge.EVENT_BUS.register(new CelebiSpawn());
        MinecraftForge.EVENT_BUS.register(new PalkiaSpawn());
        MinecraftForge.EVENT_BUS.register(new GroudonSpawn());
        MinecraftForge.EVENT_BUS.register(new KyogreSpawn());
        MinecraftForge.EVENT_BUS.register(new ArceusSpawn());
        MinecraftForge.EVENT_BUS.register(new HeatranSpawn());
        MinecraftForge.EVENT_BUS.register(new KeldeoSpawn());
        MinecraftForge.EVENT_BUS.register(new LandorusSpawn());
        MinecraftForge.EVENT_BUS.register(new ReshiramSpawn());
        MinecraftForge.EVENT_BUS.register(new ThundurusSpawn());
        MinecraftForge.EVENT_BUS.register(new TornadusSpawn());
        MinecraftForge.EVENT_BUS.register(new VictiniSpawn());
        MinecraftForge.EVENT_BUS.register(new XerneasSpawn());
        MinecraftForge.EVENT_BUS.register(new YveltalSpawn());
        MinecraftForge.EVENT_BUS.register(new ZekromSpawn());
        //
        
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
