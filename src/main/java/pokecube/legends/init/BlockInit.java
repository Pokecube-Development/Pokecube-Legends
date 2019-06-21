package pokecube.legends.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pokecube.legends.blocks.BlockBase;
import pokecube.legends.blocks.DistorcedDirt;
import pokecube.legends.blocks.DistorcedStone;
import pokecube.legends.blocks.LegendaryBlock;
import pokecube.legends.blocks.RubyOre;
import pokecube.legends.blocks.SapphireOre;
import pokecube.legends.blocks.TeleportBlockCrystal;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block RUBY_BLOCK = new BlockBase("ruby_block", Material.IRON);	
	public static final Block LEGENDARY_SPAWN = new LegendaryBlock("legendaryspawn", Material.IRON);
	public static final Block SAPPHIRE_BLOCK = new BlockBase("sapphire_block", Material.IRON);
	public static final Block DISTORCED_BLOCK = new DistorcedStone("distorced_block", Material.ROCK);
	public static final Block DISTORCED_COBBLESTONE = new BlockBase("distorced_cobblestone", Material.ROCK);
	public static final Block DISTORCED_CRYSTAL = new BlockBase("distorced_crystal", Material.GLASS);
	public static final Block DISTORCED_DIRT = new DistorcedDirt("distorced_dirt", Material.GRASS);
	public static final Block ULTRA_BLOCK = new BlockBase("ultra_block", Material.IRON);
	
	//Decorative_Blocks
	public static final Block TEMPORAL_BLOCK = new BlockBase("temporalblock", Material.ROCK);
	public static final Block OCEAN_BLOCK = new BlockBase("oceanblock", Material.ROCK);
	public static final Block SKY_BLOCK = new BlockBase("skyblock", Material.ROCK);
	public static final Block SPATIAN_BLOCK = new BlockBase("spatianblock", Material.ROCK);
	
	//Portal
	public static final Block TELEPORT_BLOCK_CRYSTAL = new TeleportBlockCrystal("distorced_portal", Material.GLASS);
	
	//Ores
	public static final Block RUBY_ORE = new RubyOre("ruby_ore", Material.ROCK);
	public static final Block SAPPHIRE_ORE = new SapphireOre("sapphire_ore", Material.ROCK);
	
}
