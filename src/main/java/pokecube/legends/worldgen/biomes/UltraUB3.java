package pokecube.legends.worldgen.biomes;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB3 extends Biome
{
	//Pheromosa/Stakataka/Celestila
	@SuppressWarnings("unused")
	public UltraUB3() 
	{
		super(new BiomeProperties("UB03").setRainDisabled().setBaseHeight(0.3f).setTemperature(1.0f).setHeightVariation(0.2f));
		
		topBlock = BlockInit.ULTRA_SAND.getDefaultState();
		fillerBlock = BlockInit.ULTRA_SANDSTONE.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.cactiPerChunk = 4;
		this.decorator.deadBushPerChunk = 2;
		this.decorator.clayPerChunk = 8;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		Random rand = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return -10511132;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -10511132;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -6697729;
	}
}
