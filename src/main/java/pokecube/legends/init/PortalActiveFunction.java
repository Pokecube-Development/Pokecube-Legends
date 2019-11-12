package pokecube.legends.init;

import net.minecraft.world.WorldServer;
import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
// import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.PokecubeMod;
// import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import thut.api.maths.Vector3;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/** Uses player interact here to also prevent opening of inventories.
 * 
 * @param dependencies */
public class PortalActiveFunction
{
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
                List<String> keys = Lists.newArrayList(forms.keySet());
                int randnum = rand.nextInt(keys.size());
                String key = keys.get(randnum);
                PokedexEntry form = forms.get(key);
                n = 0;
                while (form.dummy || form.isMega)
                {
                    key = keys.get(rand.nextInt(keys.size()));
                    form = forms.get(key);
                    //We somehow failed too many times again, lets do a missingno
                    if (n++ > 100) ret = Database.missingno;
                }
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
            System.err.println("Failed to load dependency x for PortalActive!");
            return;
        }
        if (dependencies.get("y") == null)
        {
            System.err.println("Failed to load dependency y for PortalActive!");
            return;
        }
        if (dependencies.get("z") == null)
        {
            System.err.println("Failed to load dependency z for PortalActive!");
            return;
        }
        if (dependencies.get("world") == null)
        {
            System.err.println("Failed to load dependency world for PortalActive!");
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
            if (entity != null)
            {
                // IPokemob pokemob = CapabilityPokemob.getPokemobFor(entity);
                entity.setHealth(entity.getMaxHealth());
                location.add(0, 1, 0).moveEntity(entity);
                entity.setPosition(x, y, z);
                world.spawnEntity(entity);
            }
        }
        world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
        if (world instanceof WorldServer) ((WorldServer) world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y,
                z, (int) 15, 6, 6, 6, 0.4, new int[0]);
        world.playSound((EntityPlayer) null, x, y, z,
                (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
                        .getObject(new ResourceLocation("entity.wither.death")),
                SoundCategory.NEUTRAL, (float) 1, (float) 1);
        if (!world.isRemote)
        {
            int maxD = 3;
            int minD = 1;
            Random rD = new Random();

            int iD = rD.nextInt(maxD) - minD;

            EntityItem entityToSpawn = new EntityItem(world, x, y, z, new ItemStack(ItemInit.CRYSTAL_SHARD, iD));
            entityToSpawn.setPickupDelay(10);
            world.spawnEntity(entityToSpawn);
        }
    }
}