package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UltraAlpha extends Biome
{
	//Possible Stakataka + The End
	@SuppressWarnings({ "unused" })
	public UltraAlpha() 
	{
		super(new BiomeProperties("UB09").setBaseHeight(0.1f).setRainfall(0.1F).setWaterColor(-16777216).setTemperature(0.5f).setHeightVariation(0.5f));
		
		topBlock = Blocks.END_STONE.getDefaultState();
		fillerBlock = Blocks.OBSIDIAN.getDefaultState();
		this.decorator.treesPerChunk = 2;
		this.decorator.flowersPerChunk = 0;
		this.decorator.grassPerChunk = 0;
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
		return -1184577;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -1184577;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -10998371;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new WorldGenShrub(topBlock, topBlock);
	}
}
