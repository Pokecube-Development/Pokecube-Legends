package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class UltraBeta extends Biome
{
	@SuppressWarnings({ "unused" })
	public UltraBeta() 
	{
		super(new BiomeProperties("UB010").setBaseHeight(0.1f).setRainfall(0.1F).setWaterColor(-3473151).setTemperature(0.5f).setHeightVariation(0.5f));
		
		topBlock = Blocks.NETHERRACK.getDefaultState();
		fillerBlock = Blocks.RED_NETHER_BRICK.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.flowersPerChunk = 0;
		this.decorator.bigMushroomsPerChunk = 0;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator.generateFalls = true;
		Random rand = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return -1103598;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -1103598;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -7120869;
	}
}
