package pokecube.legends.spawns;

import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import pokecube.core.handlers.PokecubePlayerDataHandler;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import pokecube.core.utils.Tools;
import thut.api.maths.Vector3;

public class LegendarySpawn
{
    private final Predicate<ItemStack> heldItemChecker;
    private final Predicate<IBlockState> targetBlockChecker;
    private final PokedexEntry entry;
    
    public LegendarySpawn(String entry, Item held, Block target)
    {
        this(Database.getEntry(entry), (c)->c.getItem()==held, (b)->b.getBlock()==target);
    }
    
    public LegendarySpawn(PokedexEntry entry, Item held, Block target)
    {
        this(entry, (c)->c.getItem()==held, (b)->b.getBlock()==target);
    }
    
    public LegendarySpawn(PokedexEntry entry, Predicate<ItemStack> heldItemChecker, Predicate<IBlockState> targetBlockChecker)
    {
        this.heldItemChecker = heldItemChecker;
        this.targetBlockChecker = targetBlockChecker;
        this.entry = entry;
    }
    
    /** Uses player interact here to also prevent opening of inventories.
     * 
     * @param evt */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void interactRightClickBlock(PlayerInteractEvent.RightClickBlock evt)
    {
        boolean invalid = !evt.getEntityPlayer().isSneaking() || heldItemChecker.test(evt.getItemStack()) || evt.getWorld().isRemote;
        if (invalid) return;
        EntityPlayer playerIn = evt.getEntityPlayer();
        World worldIn = evt.getWorld();
        BlockPos pos = evt.getPos();
        IBlockState state = evt.getWorld().getBlockState(evt.getPos());
        if (targetBlockChecker.test(state))
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
                   PokecubePlayerDataHandler.getCustomDataTag(playerIn).setBoolean("spwn:" + entry.getTrimmedName(),
                           true);
                    entity.setHealth(entity.getMaxHealth());
                   location.add(0, 1, 0).moveEntity(entity);
                   spawnCondition.onSpawn(pokemob);
                   playerIn.getHeldItemMainhand().setCount(0);
                   worldIn.setBlockToAir(pos);
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
