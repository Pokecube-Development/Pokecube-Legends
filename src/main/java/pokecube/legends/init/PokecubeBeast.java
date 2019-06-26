package pokecube.legends.init;

import net.minecraft.entity.Entity;
import pokecube.core.interfaces.IPokemob;

public class PokecubeBeast
{
	public double beast(IPokemob mob)
    {
        double x = 1;
        Entity entity = mob.getEntity();
        if (entity.dimension == 2) {
        		x = 3.7;
        		//System.console().printf("Dimensão base");
        } 
        else if (entity.dimension == 0){
        		x = 1;
        		//System.console().printf("Dimensão base");
        }
        return x;
    }
}
