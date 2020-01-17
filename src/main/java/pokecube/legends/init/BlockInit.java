package pokecube.legends.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pokecube.legends.blocks.BlockBase;
import pokecube.legends.blocks.CobbleUltraBlock;
import pokecube.legends.blocks.GrassJungleBlock;
import pokecube.legends.blocks.GrassMussBlock;
import pokecube.legends.blocks.KeldeoBlock;
import pokecube.legends.blocks.LegendaryBlock;
import pokecube.legends.blocks.NatureCoreBlock;
import pokecube.legends.blocks.PortalWarp;
import pokecube.legends.blocks.RegiceCoreBlock;
import pokecube.legends.blocks.RegigigaCoreBlock;
import pokecube.legends.blocks.RegirockCoreBlock;
import pokecube.legends.blocks.RegisteelCoreBlock;
import pokecube.legends.blocks.RubyOre;
import pokecube.legends.blocks.SandUltraBlock;
import pokecube.legends.blocks.SapphireOre;
import pokecube.legends.blocks.SpaceCoreBlock;
import pokecube.legends.blocks.TemporalCrystal;
import pokecube.legends.blocks.UltraSpacePortal;
import pokecube.legends.blocks.VictiniBlock;
import pokecube.legends.blocks.XerneasTree;
import pokecube.legends.blocks.YveltalEgg;
import pokecube.legends.blocks.plants.MussPlant;
import pokecube.legends.blocks.plants.MussPlant1;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block RUBY_BLOCK = new BlockBase("ruby_block", Material.IRON);
	public static final Block SAPPHIRE_BLOCK = new BlockBase("sapphire_block", Material.IRON);
	public static final Block TEMPORAL_CRYSTAL = new TemporalCrystal("temporal_crystal", Material.GLASS);
	
	//Decorative_Blocks
	public static final Block OCEAN_BRICK = new BlockBase("oceanbrick", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	public static final Block SKY_BRICK = new BlockBase("skybrick", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	public static final Block SPATIAN_BRICK = new BlockBase("spatianbrick", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	public static final Block MAGMA_BRICK = new BlockBase("magmabrick", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	public static final Block CRYSTAL_BRICK = new BlockBase("crystalbrick", Material.PACKED_ICE).setHardness(0.5F);
	public static final Block DARKSKY_BRICK = new BlockBase("darkskybrick", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	
	//Dimension and Decoration
	public static final Block ULTRA_MAGNETIC = new BlockBase("ultramagnetic", Material.GLASS).setHardness(0.5F);
	public static final Block ULTRA_SANDSTONE = new BlockBase("ultrasandstone", Material.SAND).setHardness(0.8F);
	public static final Block ULTRA_COBBLES = new CobbleUltraBlock("ultracobbles", Material.ROCK);
	public static final Block ULTRA_GRASSMUSS = new GrassMussBlock("ultragrass1", Material.GRASS);
	public static final Block ULTRA_DIRTMUSS = new BlockBase("ultradirt1", Material.CLAY).setHardness(0.5F);
	public static final Block ULTRA_GRASSJUN = new GrassJungleBlock("ultragrass2", Material.GRASS);
	public static final Block ULTRA_DIRTJUN = new BlockBase("ultradirt2", Material.CLAY).setHardness(0.5F);
	public static final Block ULTRA_STONE = new BlockBase("ultrastone", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
	public static final Block ULTRA_METAL = new BlockBase("ultrablock", Material.IRON).setHardness(5.0F).setResistance(10.0F);
	public static final Block ULTRA_SAND = new SandUltraBlock("ultrasand", Material.SAND);
	
	//public static final Block ULTRA_MUSS1 = new MussPlant("mussplant1", Material.PLANTS);
	//public static final Block ULTRA_MUSS2 = new MussPlant1("mussplant2", Material.PLANTS);
	
	//Portal
	public static final Block ULTRASPACE_PORTAL = new UltraSpacePortal("ultraspace_portal", Material.GLASS, 0D, 0, 0, 1D, 3D, 1D);
	public static final Block BLOCK_PORTALWARP = new PortalWarp("portal", Material.ANVIL, 0D, 0, 0, 1D, 3D, 1D);
	//public static final Block BLOCK_MAXRAID = new MaxRaidSpawnBlock("maxraidspawn", Material.ROCK);
	
	//Legendary Spawns
	public static final Block LEGENDARY_SPAWN = new LegendaryBlock("legendaryspawn", Material.IRON);
	public static final Block REGISTEEL_CORE = new RegisteelCoreBlock("registeel_spawn", Material.IRON);
	public static final Block REGICE_CORE = new RegiceCoreBlock("regice_spawn", Material.PACKED_ICE);
	public static final Block REGIROCK_CORE = new RegirockCoreBlock("regirock_spawn", Material.ROCK);
	public static final Block REGIGIGA_CORE = new RegigigaCoreBlock("regigiga_spawn", Material.IRON);
	public static final Block TIMESPACE_CORE = new SpaceCoreBlock("timerspawn", Material.GLASS);
	public static final Block NATURE_CORE = new NatureCoreBlock("naturespawn", Material.ROCK);
	public static final Block KELDEO_CORE = new KeldeoBlock("keldeoblock", Material.ROCK);
	public static final Block VICTINI_CORE = new VictiniBlock("victiniblock", Material.IRON);
	public static final Block YVELTAL_CORE = new YveltalEgg("yveltal_egg", Material.IRON);
	public static final Block XERNEAS_CORE = new XerneasTree("xerneas_tree", Material.WOOD);
	
	//Ores
	public static final Block RUBY_ORE = new RubyOre("ruby_ore", Material.ROCK);
	public static final Block SAPPHIRE_ORE = new SapphireOre("sapphire_ore", Material.ROCK);
	
}
