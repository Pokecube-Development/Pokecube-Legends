package pokecube.legends.conditions;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.stats.CaptureStats;
import pokecube.core.interfaces.IPokemob;
import thut.api.maths.Vector3;

public class Regigigas extends Condition
{
    @Override
    public boolean canCapture(Entity trainer, IPokemob pokemon)
    {
        if (!canCapture(trainer)) return false;
        boolean regice = CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(),
                Database.getEntry("regice")) > 0;
        boolean registeel = CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(),
                Database.getEntry("registeel")) > 0;
        boolean regirock = CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(),
                Database.getEntry("regirock")) > 0;
                
        String name = "Regice, Registeel, Regirock";
                
        if ((regice && registeel && regirock)) return true;
        if (pokemon != null && !trainer.getEntityWorld().isRemote)
        {
            sendNoTrust(trainer);
            sendLegendExtra(trainer, name);
        }
        return false;
    }

    @Override
    public void onSpawn(IPokemob mob)
    {
        mob = mob.setForSpawn(54500);
        Vector3 location = Vector3.getNewVector().set(mob.getEntity()).add(0, -1, 0);
        ArrayList<Vector3> locations = new ArrayList<Vector3>();
        World world = mob.getEntity().getEntityWorld();
        locations.add(location.add(0, -1, 0));
        locations.add(location.add(0, -2, 0));
        locations.add(location.add(1, -1, 0));
        locations.add(location.add(-1, -1, 0));
        locations.add(location.add(0, -1, -1));
        locations.add(location.add(0, -1, 1));
        locations.add(location.add(0, 0, -1));
        locations.add(location.add(0, 0, 1));
        locations.add(location.add(1, 0, 0));
        locations.add(location.add(-1, 0, 0));
        for (Vector3 v : locations)
        {
            v.setAir(world);
        }
        location.setAir(world);
    }

    @Override
    public boolean canSpawn(Entity trainer, Vector3 location)
    {
        if (!super.canSpawn(trainer, location)) return false;

        ArrayList<Vector3> locations = new ArrayList<Vector3>();
        boolean check = false;
        World world = trainer.getEntityWorld();

        locations.add(location.add(0, -1, 0));
        locations.add(location.add(0, -2, 0));
        locations.add(location.add(-1, -1, 0));
        locations.add(location.add(1, -1, 0));
        check = isBlock(world, locations, Blocks.OBSIDIAN);
        if (check)
        {
            locations.clear();
            locations.add(location.add(-1, 0, 0));
            locations.add(location.add(1, 0, 0));
            check = isBlock(world, locations, Blocks.END_BRICKS);
        }
        else
        {
            locations.clear();
            locations.add(location.add(0, -1, 0));
            locations.add(location.add(0, -2, 0));
            locations.add(location.add(0, -1, 1));
            locations.add(location.add(0, -1, -1));
            check = isBlock(world, locations, Blocks.OBSIDIAN);
            if (check)
            {
                locations.clear();
                locations.add(location.add(0, 0, 1));
                locations.add(location.add(0, 0, -1));
                check = isBlock(world, locations, Blocks.END_BRICKS);
            }
        }
        if (!check)
        {
            String message = "msg.reginotlookright.txt";
            trainer.sendMessage(new TextComponentTranslation(message));
            return false;
        }
        return true;
    }

    @Override
    public PokedexEntry getEntry()
    {
        return Database.getEntry("regigigas");
    }

}
