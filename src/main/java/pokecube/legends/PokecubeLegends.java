package pokecube.legends;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
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
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.IPokecube.DefaultPokecubeBehavior;
import pokecube.legends.conditions.LegendaryConditions;
import pokecube.legends.handlers.RegistryHandler;
import pokecube.legends.init.PokecubeBeast;
import pokecube.legends.init.RecipeInit;
import pokecube.legends.proxy.CommonProxy;
import pokecube.legends.worldgen.gen.ModWorldGen;

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

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Ore Registry
    	GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
    	OBJLoader.INSTANCE.addDomain(Reference.ID);
        Configuration config = PokecubeCore.instance.getPokecubeConfig(event);
        config.load();
        enabled = config.getBoolean("legends_enabled", Configuration.CATEGORY_GENERAL, true,
                "whether legends is enabled.");
        config.save();
    }
    
    @EventHandler
	public void initRegistries(FMLInitializationEvent e)
	{
    	RegistryHandler.initRegistries(e);
    	//Furnace Recipe
    	RecipeInit.init();
	}
    
    @SubscribeEvent
    public void postPostInit(PostPostInit e)
    {
        if (enabled) new LegendaryConditions();
    }
    
    @SubscribeEvent
    public void registerPokecubes(RegisterPokecubes event)
    {
        final PokecubeBeast helper = new PokecubeBeast();
        
        event.behaviors.add(new DefaultPokecubeBehavior()
        {
	        @Override
		    public double getCaptureModifier(IPokemob mob)
		    {
		        return helper.beast(mob);
		    }
		}.setRegistryName("pokecube_legends", "beast"));
	}
}
