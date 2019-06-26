package pokecube.legends.worldgen.dimensions;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import pokecube.legends.init.BiomeInit;
import pokecube.legends.init.DimensionInit;
import pokecube.legends.worldgen.chunk.ChunkGeneratorExp;

public class DimensionTemporal extends WorldProvider
{
	public DimensionTemporal() 
	{
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.DISTORTED_PLACE);
		this.hasSkyLight = true;
	}
	
	@Override
	public DimensionType getDimensionType() 
	{
		return DimensionInit.TEMPORAL_PLACE;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() 
	{
		return new ChunkGeneratorExp(world, true, world.getSeed(), this.getSpawnPoint());
	}
	
	@Override
	public boolean canRespawnHere() 
	{
		return false;
	}
	
	@Override
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.0, 0.0, 0.0);
	}
	
	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}
}
