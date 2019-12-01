package pokecube.legends.init.function;

import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import thut.api.maths.Vector3;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import com.google.common.collect.Lists;
import net.minecraft.entity.EntityLiving;

public class MaxRaidActivationFunction
{
	//Max Size Pokemob Raids
    public static float h = 24;
      
	public static Field form_field;
    static
    {
        try
        {
            form_field = PokedexEntry.class.getDeclaredField("forms");
            form_field.setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException e)
        {
            e.printStackTrace();
            form_field = null;
        }
    }

    public static PokedexEntry getRandomEntry()
    {
        PokedexEntry ret = null;
        int n = 0;
        Random rand = new Random();
        while (ret == null)
        {
            // Pick a random number from 1 to just below database size, this
            // ensures no missingnos
            int num = rand.nextInt(Database.baseFormes.size() - 1) + 1;
            ret = Database.getEntry(num);
            // If we took too many tries, just throw a missingno...
            if (ret == null && n++ > 10) ret = Database.missingno;
        }
        // Select a random sub-forme of this mob
        try
        {
            @SuppressWarnings("unchecked")
            Map<String, PokedexEntry> forms = (Map<String, PokedexEntry>) form_field.get(ret);
            if (!forms.isEmpty())
            {
                List<PokedexEntry> values = Lists.newArrayList(forms.values());
                Collections.shuffle(values);
                int num = values.size();
                if (num == 0) return ret;
                PokedexEntry val = values.get(0);
                if (!(val.dummy || val.isMega) || num == 1) return val;
                for (int i = 1; i < num; i++)
                {
                    val = values.get(i);
                    if (!(val.dummy || val.isMega)) break;
                }
                return val;
            }
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            PokecubeMod.log(Level.WARNING, "Error finding subforms for " + ret, e);
        }

        return ret;
    }
    
    public static void executeProcedure(java.util.HashMap<String, Object> dependencies)
    {
        if (dependencies.get("x") == null)
        {
            System.err.println("Failed to load dependency x for RaidActive!");
            return;
        }
        if (dependencies.get("y") == null)
        {
            System.err.println("Failed to load dependency y for RaidActive!");
            return;
        }
        if (dependencies.get("z") == null)
        {
            System.err.println("Failed to load dependency z for RaidActive!");
            return;
        }
        if (dependencies.get("world") == null)
        {
            System.err.println("Failed to load dependency world for RaidActive!");
            return;
        }
        
        int x = (int) dependencies.get("x");
        int y = (int) dependencies.get("y");
        int z = (int) dependencies.get("z");
        World world = (World) dependencies.get("world");
        if (!world.isRemote)
        {
            PokedexEntry entityToSpawn = getRandomEntry();
            BlockPos pos = null;
            EntityLiving entity = (EntityLiving) PokecubeMod.core.createPokemob(entityToSpawn, world);
            Vector3 location = Vector3.getNewVector().set(pos);
            IPokemob pokemob = CapabilityPokemob.getPokemobFor(entity);
            if (entity != null && !entityToSpawn.hasMegaForm)
            {   
                entity.setHealth(entity.getMaxHealth() + 50f);
                location.add(0, 6, 0).moveEntity(entity);
                entity.setPosition(x, y, z);
                world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
                //entity.tags.add("raid");

                //Size for MaxPokemob
                pokemob.setSize(h);
                pokemob.getLevel();
                //pokemob.setHeldItem(new ItemStack(ItemInit.FRAGMENTDYN));
                world.spawnEntity(entity);
            }
        }
    }
}