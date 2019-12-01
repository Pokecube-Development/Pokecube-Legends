package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB7 extends Biome
{
	//Guzzlord
	@SuppressWarnings({ "unused", "deprecation" })
	public UltraUB7() 
	{
		super(new BiomeProperties("UB07").setBaseHeight(0.1f).setRainfall(0.1F).setWaterColor(-16750951).setTemperature(0.5f).setHeightVariation(0.5f));
		
		topBlock = Blocks.CONCRETE_POWDER.getStateFromMeta(12);
		fillerBlock = BlockInit.ULTRA_STONE.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.flowersPerChunk = 0;
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
		return -16751053;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -16751053;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -10027162;
	}
}
