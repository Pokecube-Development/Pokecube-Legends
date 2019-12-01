package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB8 extends Biome
{
	//Poipole
	@SuppressWarnings({ "unused" })
	public UltraUB8() 
	{
		super(new BiomeProperties("UB08").setBaseHeight(0.1f).setRainfall(0.1F).setWaterColor(-6619141).setTemperature(0.5f).setHeightVariation(0.5f));
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = BlockInit.ULTRA_STONE.getDefaultState();
		this.decorator.treesPerChunk = 1;
		this.decorator.flowersPerChunk = 16;
		this.decorator.grassPerChunk = 14;
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
		return -13864697;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -13864697;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -5313281;
	}
}
