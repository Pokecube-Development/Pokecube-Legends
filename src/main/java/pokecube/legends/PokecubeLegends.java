package pokecube.legends;

// import java.io.IOException;
// import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pokecube.core.PokecubeCore;
import pokecube.core.events.PostPostInit;
import pokecube.core.events.onload.RegisterPokecubes;
// import pokecube.core.interfaces.PokecubeMod;
import pokecube.core.interfaces.IPokecube.DefaultPokecubeBehavior;
import pokecube.core.interfaces.IPokemob;
import pokecube.legends.conditions.LegendaryConditions;
import pokecube.legends.handlers.PortalSpawnHandler;
import pokecube.legends.handlers.RegistryHandler;
import pokecube.legends.handlers.WormHoleSpawnHandler;
import pokecube.legends.init.BiomeInit;
import pokecube.legends.init.DimensionInit;
import pokecube.legends.init.ItemInit;
import pokecube.legends.init.PokecubeDim;
import pokecube.legends.proxy.CommonProxy;
// import pokecube.legends.worldgen.gencustom.TemplateManager;
import pokecube.legends.worldgen.genlayer.OreWorldGen;
import pokecube.legends.worldgen.structuregen.WorldGenCustomStrucute;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPSTRING, acceptableRemoteVersions = "*")
public class PokecubeLegends
{
    @Instance(value = Reference.ID)
    public static PokecubeLegends instance;

    // Enabla Condition
    public boolean                enabledcondition      = true;

    // mirage spot
    public boolean                enabledmirage         = true;
    public int                    ticksPerMirageSpawn   = 7000;

    // ultra space portal
    public boolean                enabledportal         = true;
    public int                    ticksPerPortalSpawn   = 9000;

    public PokecubeLegends()
    {
        MinecraftForge.EVENT_BUS.register(this);
        DimensionInit.initDimension();
    }

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Registry
        DimensionInit.registerDimension();
        BiomeInit.registerBiomes();
        //

        MinecraftForge.EVENT_BUS.register(this);

        GameRegistry.registerWorldGenerator(new WorldGenCustomStrucute(), 0);

        proxy.preinit(event);

        Configuration config = PokecubeCore.instance.getPokecubeConfig(event);
        config.load();

        enabledcondition = config.getBoolean("legends_enabled", Configuration.CATEGORY_GENERAL, true,
                "enable the capture condition of the legends.");

        enabledmirage = config.getBoolean("legends_enabled_miragespot", Configuration.CATEGORY_GENERAL, true,
                "whether legends is enabled.");
        enabledportal = config.getBoolean("legends_enabled_wormhole", Configuration.CATEGORY_GENERAL, true,
                "whether legends is enabled.");

        ticksPerMirageSpawn = config.getInt("ticks_per_mirage_spawn", Configuration.CATEGORY_GENERAL, 6000, 0,
                Integer.MAX_VALUE, "Time to Mirage Spot Generation, 0 to disable");

        ticksPerPortalSpawn = config.getInt("ticks_per_portal_spawn", Configuration.CATEGORY_GENERAL, 9000, 0,
                Integer.MAX_VALUE, "Time for Ultra Wormhole Generation, 0 to disable");

        config.save();

        // Ore Registry
        GameRegistry.registerWorldGenerator(new OreWorldGen(), 3);

        if (enabledmirage) if (ticksPerMirageSpawn > 0)
        {
            MinecraftForge.EVENT_BUS.register(new PortalSpawnHandler());
        }

        if (enabledportal) if (ticksPerPortalSpawn > 0)
        {
            MinecraftForge.EVENT_BUS.register(new WormHoleSpawnHandler());
        }
    }

    @EventHandler
    public void initRegistries(FMLInitializationEvent e)
    {
        /*
         * try { TemplateManager.initTemplates(); } catch (IOException e1) {
         * PokecubeMod.log(Level.SEVERE, "Error copying legends templates", e1);
         * }
         */
        RegistryHandler.initRegistries(e);
    }

    // Registry Item Nature/Z-Move Crystal
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> evt)
    {
        ItemInit.registerItems(evt.getRegistry());
    }

    @SubscribeEvent
    public void postPostInit(PostPostInit e)
    {
        if (enabledcondition) new LegendaryConditions();
    }

    @SubscribeEvent
    public void registerPokecubes(RegisterPokecubes event)
    {
        final PokecubeDim helper = new PokecubeDim();

        event.behaviors.add(new DefaultPokecubeBehavior()
        {
            @Override
            public double getCaptureModifier(IPokemob mob)
            {
                return helper.beast(mob);
            }
        }.setRegistryName("pokecube_legends", "beast"));

        // Pokecube Capture example dynamax resize
        /*
         * event.behaviors.add(new DefaultPokecubeBehavior() {
         * @Override public double getCaptureModifier(IPokemob mob) { return
         * helper.dynamax(mob); } }.setRegistryName("pokecube_legends",
         * "dynamax"));
         */
    }
}
