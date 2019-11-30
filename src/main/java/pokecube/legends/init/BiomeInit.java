package pokecube.legends.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import pokecube.legends.worldgen.biomes.UltraUB1;
import pokecube.legends.worldgen.biomes.UltraBeta;
import pokecube.legends.worldgen.biomes.UltraUB2;
import pokecube.legends.worldgen.biomes.UltraUB3;
import pokecube.legends.worldgen.biomes.UltraUB4;
import pokecube.legends.worldgen.biomes.UltraUB5;
import pokecube.legends.worldgen.biomes.UltraUB6;
import pokecube.legends.worldgen.biomes.UltraUB7;
import pokecube.legends.worldgen.biomes.UltraUB8;
import pokecube.legends.worldgen.biomes.UltraAlpha;

public class BiomeInit 
{
	public static final Biome BIOME_UB01 = new UltraUB1();
	public static final Biome BIOME_UB02 = new UltraUB2();
	public static final Biome BIOME_UB03 = new UltraUB3();
	public static final Biome BIOME_UB04 = new UltraUB4();
	public static final Biome BIOME_UB05 = new UltraUB5();
	public static final Biome BIOME_UB06 = new UltraUB6();
	public static final Biome BIOME_UB07 = new UltraUB7();
	public static final Biome BIOME_UB08 = new UltraUB8();
	public static final Biome BIOME_UB09 = new UltraAlpha();
	public static final Biome BIOME_UB10 = new UltraBeta();
	
	public static void registerBiomes()
	{
		initBiome(BIOME_UB01, "Biome UB01", BiomeType.ICY, Type.MOUNTAIN, Type.DENSE, Type.SPOOKY);
		initBiome(BIOME_UB02, "Biome UB02", BiomeType.COOL, Type.JUNGLE, Type.DENSE, Type.HILLS);
		initBiome(BIOME_UB03, "Biome UB03", BiomeType.WARM, Type.SANDY, Type.HOT, Type.BEACH);
		initBiome(BIOME_UB04, "Biome UB04", BiomeType.ICY, Type.DRY, Type.LUSH, Type.CONIFEROUS);
		initBiome(BIOME_UB05, "Biome UB05", BiomeType.WARM, Type.HILLS, Type.DRY, Type.MESA);
		initBiome(BIOME_UB06, "Biome UB06", BiomeType.COOL, Type.FOREST, Type.HILLS, Type.PLAINS);
		initBiome(BIOME_UB07, "Biome UB07", BiomeType.DESERT, Type.DEAD, Type.HILLS, Type.WASTELAND);
		initBiome(BIOME_UB08, "Biome UB08", BiomeType.WARM, Type.MOUNTAIN, Type.PLAINS, Type.MAGICAL);
		
		//Alpha and Beta Biomes
		initBiome(BIOME_UB09, "Biome UB09", BiomeType.ICY, Type.MAGICAL, Type.END, Type.VOID);
		initBiome(BIOME_UB10, "Biome UB10", BiomeType.DESERT, Type.DENSE, Type.NETHER, Type.HOT);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType bType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		//BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}