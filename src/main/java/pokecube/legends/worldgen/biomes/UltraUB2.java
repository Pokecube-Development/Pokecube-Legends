package pokecube.legends.worldgen.biomes;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB2 extends Biome
{	
	//Buzzwole
	@SuppressWarnings("unused")
	public UltraUB2() 
	{
		super(new BiomeProperties("UB02").setRainfall(0.1f).setBaseHeight(0.1f).setTemperature(0.5f).setHeightVariation(0.5f));
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = BlockInit.ULTRA_STONE.getDefaultState();
		this.decorator.treesPerChunk = 20;
		this.decorator.flowersPerChunk = 10;
		this.decorator.grassPerChunk = 24;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		Random rand = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return -16737997;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -16737997;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -16750900;
	}
}
