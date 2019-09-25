package pokecube.legends.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pokecube.legends.blocks.BlockBase;
import pokecube.legends.blocks.TemporalDirt;
import pokecube.legends.blocks.TemporalStone;
import pokecube.legends.blocks.TimerSpaceCoreBlock;
import pokecube.legends.blocks.LegendaryBlock;
import pokecube.legends.blocks.PortalWarp;
import pokecube.legends.blocks.RegiceCoreBlock;
import pokecube.legends.blocks.RegigigaCoreBlock;
import pokecube.legends.blocks.RegirockCoreBlock;
import pokecube.legends.blocks.RegisteelCoreBlock;
import pokecube.legends.blocks.RubyOre;
import pokecube.legends.blocks.SapphireOre;
//import pokecube.legends.blocks.TeleportBlockCrystal;
import pokecube.legends.blocks.TemporalCrystal;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block RUBY_BLOCK = new BlockBase("ruby_block", Material.IRON);
	public static final Block SAPPHIRE_BLOCK = new BlockBase("sapphire_block", Material.IRON);
	public static final Block TEMPORAL_STONE = new TemporalStone("temporal_stone", Material.ROCK);
	public static final Block TEMPORAL_COBBLESTONE = new BlockBase("temporal_cobblestone", Material.ROCK);
	public static final Block TEMPORAL_CRYSTAL = new TemporalCrystal("temporal_crystal", Material.GLASS);
	public static final Block TEMPORAL_DIRT = new TemporalDirt("temporal_dirt", Material.GRASS);
	public static final Block ULTRA_BLOCK = new BlockBase("ultra_block", Material.IRON);
	
	//Decorative_Blocks
	public static final Block TEMPORAL_BRICK = new BlockBase("temporalbrick", Material.ROCK);
	public static final Block OCEAN_BRICK = new BlockBase("oceanbrick", Material.ROCK);
	public static final Block SKY_BRICK = new BlockBase("skybrick", Material.ROCK);
	public static final Block SPATIAN_BRICK = new BlockBase("spatianbrick", Material.ROCK);
	public static final Block MAGMA_BRICK = new BlockBase("magmabrick", Material.ROCK);
	public static final Block CRYSTAL_BRICK = new BlockBase("crystalbrick", Material.PACKED_ICE);
	public static final Block DARKSKY_BRICK = new BlockBase("darkskybrick", Material.ROCK);

	//Portal
	//public static final Block TELEPORT_BLOCK_CRYSTAL = new TeleportBlockCrystal("temporal_portal", Material.GLASS);
	public static final Block BLOCK_PORTALWARP = new PortalWarp("portal", Material.ANVIL);
	
	//Legendary Spawns
	public static final Block LEGENDARY_SPAWN = new LegendaryBlock("legendaryspawn", Material.IRON);
	public static final Block REGISTEEL_CORE = new RegisteelCoreBlock("registeel_spawn", Material.IRON);
	public static final Block REGICE_CORE = new RegiceCoreBlock("regice_spawn", Material.PACKED_ICE);
	public static final Block REGIROCK_CORE = new RegirockCoreBlock("regirock_spawn", Material.ROCK);
	public static final Block REGIGIGA_CORE = new RegigigaCoreBlock("regigiga_spawn", Material.IRON);
	public static final Block TIMESPACE_CORE = new TimerSpaceCoreBlock("timerspawn", Material.GLASS);
	
	//Ores
	public static final Block RUBY_ORE = new RubyOre("ruby_ore", Material.ROCK);
	public static final Block SAPPHIRE_ORE = new SapphireOre("sapphire_ore", Material.ROCK);
	
}
