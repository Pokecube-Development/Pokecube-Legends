package pokecube.legends.worldgen.dimension.library;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import pokecube.legends.init.BiomeInit;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.DimensionInit;
import pokecube.legends.worldgen.chunk.ChunkGeneratorExp;

public class DimensionDistorted extends WorldProvider
{
	public DimensionDistorted() 
	{
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.DISTORTED_PLACE);
		this.hasSkyLight = false;
	}
	
	@Override
	public DimensionType getDimensionType() 
	{
		return DimensionInit.DISTORTED_PLACE;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() 
	{
		return new ChunkGeneratorExp(world, true, world.getSeed(), null);
	}
	
	@Override
	public boolean canRespawnHere() 
	{
		return false;
	}
	
	@Override
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.1, 0.1, 0.1);
	}
	
	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}
}
