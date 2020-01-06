package pokecube.legends.worldgen.biomes;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB2 extends Biome
{	
	//Buzzwole/Poipole/Kartana
	@SuppressWarnings("unused")
	public UltraUB2() 
	{
		super(new BiomeProperties("UB02").setRainfall(1.0f).setBaseHeight(0.2f).setTemperature(0.8f).setHeightVariation(0.5f));
		
		topBlock = BlockInit.ULTRA_GRASSJUN.getDefaultState();
		fillerBlock = BlockInit.ULTRA_DIRTJUN.getDefaultState();
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
	public int getFoliageColorAtPos(BlockPos pos) {
		return -16737997;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -16750900;
	}
}
