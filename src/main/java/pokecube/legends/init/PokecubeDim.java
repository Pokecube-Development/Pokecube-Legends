package pokecube.legends.init;

import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
import pokecube.core.interfaces.IPokemob;
//import pokecube.core.interfaces.capabilities.CapabilityPokemob;

public class PokecubeDim
{	
	public double beast(IPokemob mob)
    {
        double x = 1;
        Entity entity = mob.getEntity();
        if (entity.dimension != 0) {
        		x = 3.7;
        		//System.console().printf("Dimensão base");
        } 
        else if (entity.dimension == 0){
        		x = 1;
        		//System.console().printf("Dimensão base");
        }
        return x;
    }
	
	//Dynamax
	/*public double dynamax(IPokemob mob)
    {
		double x = 0.1;
		EntityLiving entity = mob.getEntity();
		mob = CapabilityPokemob.getPokemobFor(entity);
    	if (entity != null)
        {
			mob.setSize(1f);
			mob.setHealth(entity.getMaxHealth());
			x = 60;
			//System.console().printf("ta aki");
        }
        return x;
    }*/
}
