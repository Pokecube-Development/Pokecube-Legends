package pokecube.legends.conditions;

import net.minecraft.entity.Entity;
import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.stats.CaptureStats;
import pokecube.core.database.stats.KillStats;
import pokecube.core.interfaces.IPokemob;

public class Giratina extends Condition
{
    @Override
    public boolean canCapture(Entity trainer, IPokemob pokemon)
    {
        if (!canCapture(trainer)) return false;
        boolean dialga = CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(),
                Database.getEntry("dialga")) > 0;
        boolean palkia = CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(),
                Database.getEntry("palkia")) > 0;
        if ((dialga && palkia)) return true;
        if (pokemon != null && !trainer.getEntityWorld().isRemote)
        {
            sendNoTrust(trainer);
        }
        return false;
    }
    
    @Override
    public boolean canSpawn(Entity trainer)
    {
        if (CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(), getEntry()) > 0) return false;
        if (KillStats.getTotalNumberOfPokemobKilledBy(trainer.getUniqueID(), getEntry()) > 0) return false;
        return true;
    }
    
    @Override
    public PokedexEntry getEntry()
    {
        return Database.getEntry("giratina");
    }

}
