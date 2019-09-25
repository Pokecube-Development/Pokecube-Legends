package pokecube.legends.spawns;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.PokedexEntry.SpawnData;
import pokecube.core.database.SpawnBiomeMatcher;
import pokecube.core.database.stats.ISpecialCaptureCondition;
import pokecube.core.database.stats.ISpecialSpawnCondition;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import pokecube.core.utils.Tools;
import pokecube.legends.init.BlockInit;
import pokecube.legends.items.AdamantOrb;
import thut.api.maths.Vector3;
import thut.lib.CompatWrapper;

public class DialgaSpawn
 {
    /** Uses player interact here to also prevent opening of inventories.
     * 
     * @param evt */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void interactRightClickBlock(PlayerInteractEvent.RightClickBlock evt)
    {
        boolean invalid = !evt.getEntityPlayer().isSneaking() || !CompatWrapper.isValid(evt.getItemStack())
                || !(evt.getItemStack().getItem() instanceof AdamantOrb /*ItemPokedex*/) || evt.getWorld().isRemote;
        if (invalid) return;
        Block block = null;
        EntityPlayer playerIn = evt.getEntityPlayer();
        World worldIn = evt.getWorld();
        BlockPos pos = evt.getPos();
        IBlockState state = evt.getWorld().getBlockState(evt.getPos());
        block = state.getBlock();
        PokedexEntry entry = Database.getEntry("dialga");
        if (block == BlockInit.TIMESPACE_CORE && entry != null)
        {
            SpawnData data = entry.getSpawnData();
            if (data != null) for (SpawnBiomeMatcher matcher : data.matchers.keySet())
            {
                if (data.getWeight(matcher) > 0) return;
            }
            ISpecialSpawnCondition spawnCondition = ISpecialSpawnCondition.spawnMap.get(entry);
            ISpecialCaptureCondition captureCondition = ISpecialCaptureCondition.captureMap.get(entry);
            if (spawnCondition != null)
            {
                Vector3 location = Vector3.getNewVector().set(pos);
                if (spawnCondition.canSpawn(playerIn, location))
                {
                    EntityLiving entity = (EntityLiving) PokecubeMod.core.createPokemob(entry, worldIn);
                    IPokemob pokemob = CapabilityPokemob.getPokemobFor(entity);
                   if (captureCondition != null && !captureCondition.canCapture(playerIn, pokemob))
                   {
                       evt.setCanceled(true);
                        return;
                    }
                    entity.setHealth(entity.getMaxHealth());
                   location.add(0, 1, 0).moveEntity(entity);
                   spawnCondition.onSpawn(pokemob);
                   playerIn.getHeldItemMainhand().setCount(0);
                   if (pokemob.getExp() < 100)
                   {
                        entity = pokemob.setForSpawn(Tools.levelToXp(entry.getEvolutionMode(), 50)).getEntity();
                  }
                    worldIn.spawnEntity(entity);
               }
            }
            evt.setCanceled(true);
        }
    }
}
