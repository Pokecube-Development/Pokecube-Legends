package pokecube.legends.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import pokecube.legends.worldgen.biome.BiomeTemporal;

public class BiomeInit 
{
	public static final Biome DISTORTED_PLACE = new BiomeTemporal();
	
	public static void registerBiomes() 
	{
		initBiome(DISTORTED_PLACE, "Distorted", BiomeType.DESERT, Type.FOREST, Type.SPOOKY, Type.MAGICAL);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... type) 
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, type);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addVillageBiome(biome, true);
		BiomeManager.addStrongholdBiome(biome);
		BiomeDictionary.getTypes(biome);
		return biome;
	}

	public static int getType() {
		return 0;
	}
}
