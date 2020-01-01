package pokecube.legends.conditions;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import pokecube.core.database.PokedexEntry;
import pokecube.core.database.stats.CaptureStats;
import pokecube.core.database.stats.ISpecialCaptureCondition;
import pokecube.core.database.stats.ISpecialSpawnCondition;
import pokecube.core.events.handlers.SpawnHandler;
import pokecube.core.handlers.PokecubePlayerDataHandler;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.utils.Tools;
import thut.api.maths.Vector3;
import thut.api.terrain.TerrainManager;
import thut.api.terrain.TerrainSegment;

public abstract class Condition implements ISpecialCaptureCondition, ISpecialSpawnCondition
{

    protected static boolean isBlock(World world, ArrayList<Vector3> blocks, Block toTest)
    {
        for (Vector3 v : blocks)
        {
            if (v.getBlock(world) != toTest) { return false; }
        }
        return true;
    }

    /** @param world
     * @param blocks
     * @param material
     * @param bool
     *            if true, looks for matches, if false looks for anything that
     *            doesn't match.
     * @return */
    protected static boolean isMaterial(World world, ArrayList<Vector3> blocks, Material material, boolean bool)
    {
        boolean ret = true;
        if (bool)
        {
            for (Vector3 v : blocks)
            {
                if (v.getBlockMaterial(world) != material) return false;
            }
        }
        else
        {
            for (Vector3 v : blocks)
            {
                if (v.getBlockMaterial(world) == material) return false;
            }
        }
        return ret;
    }

    public abstract PokedexEntry getEntry();

    @Override
    public boolean canCapture(Entity trainer)
    {
        if (trainer == null) return false;
        if (CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(), getEntry()) > 0) return false;
        if (trainer instanceof EntityPlayerMP && PokecubePlayerDataHandler.getCustomDataTag((EntityPlayer) trainer)
                .getBoolean("capt:" + getEntry().getTrimmedName()))
            return false;
        return true;
    }

    @Override
    public void onSpawn(IPokemob mob)
    {
    }

    @Override
    public boolean canSpawn(Entity trainer)
    {
        if (trainer == null)
        return false;
        if (CaptureStats.getTotalNumberOfPokemobCaughtBy(trainer.getUniqueID(), getEntry()) > 0) return true;
        if (trainer instanceof EntityPlayerMP && PokecubePlayerDataHandler.getCustomDataTag((EntityPlayer) trainer)
                .getBoolean("spwn:" + getEntry().getTrimmedName()))
            return false;
        return true;
    }

    @Override
    public boolean canSpawn(Entity trainer, Vector3 location)
    {
        if (!canSpawn(trainer)) return false;
        TerrainSegment t = TerrainManager.getInstance().getTerrainForEntity(trainer);
        if (SpawnHandler.canSpawn(t, getEntry().getSpawnData(), location, trainer.getEntityWorld(), false))
        {
            boolean here = Tools.countPokemon(location, trainer.getEntityWorld(), 32, getEntry()) > 0;
            return !here;
        }
        sendNoHere(trainer);
        return false;
    }

    public void sendNoTrust(Entity trainer)
    {
        String message = "msg.notrust.txt";
        ITextComponent component = new TextComponentTranslation(message,
                new TextComponentTranslation(getEntry().getUnlocalizedName()));
        trainer.sendMessage(component);
    }

    public void sendNoHere(Entity trainer)
    {
        String message = "msg.nohere.txt";
        ITextComponent component = new TextComponentTranslation(message,
                new TextComponentTranslation(getEntry().getUnlocalizedName()));
        trainer.sendMessage(component);
    }
    
    //Basic Legend
    public void sendLegend(Entity trainer, String type, float numA, double numB)
    {
        String message = "msg.infolegend.txt";
        ITextComponent component = new TextComponentTranslation(message, type, numA, numB);
        trainer.sendMessage(component);
    }
    
    //Duo Type Legend
    public void sendLegendDuo(Entity trainer, String type, String kill, float numA, double numB, float killa, double killb)
    {
        String message = "msg.infolegendduo.txt";
        ITextComponent component = new TextComponentTranslation(message, type, kill, numA, numB, killa, killb);
        trainer.sendMessage(component);
    }
    
    //Catch specific Legend
    public void sendLegendExtra(Entity trainer, String names)
    {
        String message = "msg.infolegendextra.txt";
        ITextComponent component = new TextComponentTranslation(message, names);
        trainer.sendMessage(component);
    }

    public void sendAngered(Entity trainer)
    {
        String message = "msg.angeredlegend.txt";
        ITextComponent component = new TextComponentTranslation(message,
                new TextComponentTranslation(getEntry().getUnlocalizedName()));
        trainer.sendMessage(component);
    }
}
