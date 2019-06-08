package pokecube.legends;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import pokecube.core.PokecubeCore;
import pokecube.core.events.PostPostInit;
import pokecube.legends.conditions.LegendaryConditions;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPSTRING, acceptableRemoteVersions = "*")
public class PokecubeLegends
{
    @Instance(value = Reference.ID)
    public static PokecubeLegends instance;

    public boolean                enabled = true;

    public PokecubeLegends()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(FMLCommonSetupEvent event)
    {
        Configuration config = PokecubeCore.instance.getPokecubeConfig(event);
        config.load();
        enabled = config.getBoolean("legends_enabled", Configuration.CATEGORY_GENERAL, true,
                "whether legends is enabled.");
        config.save();
    }

    @SubscribeEvent
    public void postPostInit(PostPostInit e)
    {
        if (enabled) new LegendaryConditions();
    }
}
