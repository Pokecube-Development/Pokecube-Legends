package pokecube.legends.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB1 extends Biome
{
	//Nhihilego
	@SuppressWarnings("unused")
	public UltraUB1() 
	{
		super(new BiomeProperties("UB01").setRainDisabled().setBaseHeight(0.1f).setTemperature(0.5f).setHeightVariation(0.2f));
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = BlockInit.ULTRA_STONE.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.flowersPerChunk = 0;
		this.decorator.mushroomsPerChunk = 2;
		this.decorator.bigMushroomsPerChunk = 7;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		Random rand = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return -16182234;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -16445938;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -14460312;
	}
}
