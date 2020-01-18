package pokecube.legends.worldgen.biomes;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.BlockInit;

public class UltraUB1 extends Biome
{
	//Nhihilego/Xurkitree/Blacephalun
	@SuppressWarnings("unused")
	public UltraUB1() 
	{
		super(new BiomeProperties("UB01").setRainDisabled().setBaseHeight(0.1f).setTemperature(0.8f).setHeightVariation(0.1f));
		
		topBlock = BlockInit.ULTRA_GRASSMUSS.getDefaultState();
		fillerBlock = BlockInit.ULTRA_DIRTMUSS.getDefaultState();
		this.decorator.treesPerChunk = 0;
		this.decorator.grassPerChunk = 2;
		this.decorator.flowersPerChunk = 0;
		this.decorator.mushroomsPerChunk = 2;
		this.decorator.bigMushroomsPerChunk = 5;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		Random rand = new Random();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return -11394970;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return -11394970;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return -14460312;
	}
}
