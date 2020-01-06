package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB4 extends Biome
{
	//Guzzlord
	@SuppressWarnings({ "unused" })
	public UltraUB4() 
	{
		super(new BiomeProperties("UB04").setBaseHeight(0.3f).setRainfall(1.0F).setTemperature(0.1f).setHeightVariation(0.3f));
		
		topBlock = BlockInit.ULTRA_COBBLES.getDefaultState();
		fillerBlock = BlockInit.ULTRA_STONE.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.flowersPerChunk = 0;
		this.decorator.mushroomsPerChunk = 0;
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
		return -12959190;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -12959190;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -16777114;
	}
}
