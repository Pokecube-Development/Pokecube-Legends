package pokecube.legends.worldgen.structuregen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import pokecube.legends.init.BlockInit;
import pokecube.legends.worldgen.biomes.UltraAlpha;
import pokecube.legends.worldgen.biomes.UltraBeta;
import pokecube.legends.worldgen.biomes.UltraUB1;
import pokecube.legends.worldgen.biomes.UltraUB2;
import pokecube.legends.worldgen.biomes.UltraUB3;
import pokecube.legends.worldgen.biomes.UltraUB4;
import pokecube.legends.worldgen.biomes.UltraUB5;
import pokecube.legends.worldgen.biomes.UltraUB6;
import pokecube.legends.worldgen.biomes.UltraUB7;
import scala.actors.threadpool.Arrays;

public class WorldGenCustomStrucute implements IWorldGenerator
{	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
	   
	   switch(world.provider.getDimension())
		{
		case 2:
			//Nihilego
			this.generateStructure(new WorldGenStructure("ub01_1"), world, random, chunkX, chunkZ, 5, Blocks.GRASS, UltraUB1.class);
			this.generateStructure(new WorldGenStructure("ub01_2"), world, random, chunkX, chunkZ, 35, Blocks.GRASS, UltraUB1.class);
			this.generateStructure(new WorldGenStructure("ub01_3"), world, random, chunkX, chunkZ, 3, Blocks.GRASS, UltraUB1.class);
			this.generateStructure(new WorldGenStructure("ub01_4"), world, random, chunkX, chunkZ, 4, Blocks.GRASS, UltraUB1.class);
			this.generateStructure(new WorldGenStructure("ub01_5"), world, random, chunkX, chunkZ, 35, Blocks.GRASS, UltraUB1.class);
			
			//Buzzwole
			this.generateStructure(new WorldGenStructure("ub02_1"), world, random, chunkX, chunkZ, 15, Blocks.GRASS, UltraUB2.class);
			this.generateStructure(new WorldGenStructure("ub02_2"), world, random, chunkX, chunkZ, 24, Blocks.GRASS, UltraUB2.class);
			this.generateStructure(new WorldGenStructure("ub02_3"), world, random, chunkX, chunkZ, 2, Blocks.GRASS, UltraUB2.class);
			this.generateStructure(new WorldGenStructure("ub02_4"), world, random, chunkX, chunkZ, 5, BlockInit.ULTRA_STONE, UltraUB2.class);
			this.generateStructure(new WorldGenStructure("ub02_5"), world, random, chunkX, chunkZ, 4, Blocks.GRASS, UltraUB2.class);
			this.generateStructure(new WorldGenStructure("ub02_6"), world, random, chunkX, chunkZ, 17, Blocks.GRASS, UltraUB2.class);
			
			//Pheromosa
			this.generateStructure(new WorldGenStructure("ub03_1"), world, random, chunkX, chunkZ, 40, Blocks.SAND, UltraUB3.class);
			this.generateStructure(new WorldGenStructure("ub03_2"), world, random, chunkX, chunkZ, 12, Blocks.SAND, UltraUB3.class);
			this.generateStructure(new WorldGenStructure("ub03_3"), world, random, chunkX, chunkZ, 20, Blocks.SAND, UltraUB3.class);
			this.generateStructure(new WorldGenStructure("ub03_4"), world, random, chunkX, chunkZ, 3, Blocks.SAND, UltraUB3.class);
			this.generateStructure(new WorldGenStructure("ub03_5"), world, random, chunkX, chunkZ, 4, Blocks.SAND, UltraUB3.class);
			this.generateStructure(new WorldGenStructure("ub03_6"), world, random, chunkX, chunkZ, 5, Blocks.SAND, UltraUB3.class);
			
			//Xurkitree
			this.generateStructure(new WorldGenStructure("ub04_1"), world, random, chunkX, chunkZ, 20, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_2"), world, random, chunkX, chunkZ, 3, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_3"), world, random, chunkX, chunkZ, 13, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_4"), world, random, chunkX, chunkZ, 5, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_5"), world, random, chunkX, chunkZ, 23, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_6"), world, random, chunkX, chunkZ, 45, Blocks.COAL_BLOCK, UltraUB4.class);
			this.generateStructure(new WorldGenStructure("ub04_7"), world, random, chunkX, chunkZ, 20, Blocks.COAL_BLOCK, UltraUB4.class);
			
			//Celesteela
			this.generateStructure(new WorldGenStructure("ub05_1"), world, random, chunkX, chunkZ, 10, Blocks.CONCRETE_POWDER, UltraUB5.class);
			this.generateStructure(new WorldGenStructure("ub05_2"), world, random, chunkX, chunkZ, 15, Blocks.CONCRETE_POWDER, UltraUB5.class);
			this.generateStructure(new WorldGenStructure("ub05_3"), world, random, chunkX, chunkZ, 5, Blocks.CONCRETE_POWDER, UltraUB5.class);
			this.generateStructure(new WorldGenStructure("ub05_4"), world, random, chunkX, chunkZ, 4, Blocks.CONCRETE_POWDER, UltraUB5.class);
			
			//Kartana
			this.generateStructure(new WorldGenStructure("ub06_1"), world, random, chunkX, chunkZ, 6, Blocks.GRASS, UltraUB6.class);
			this.generateStructure(new WorldGenStructure("ub06_2"), world, random, chunkX, chunkZ, 64, Blocks.GRASS, UltraUB6.class);
			
			//Guzzlord
			this.generateStructureUnderground(new WorldGenStructure("ub07_1"), world, random, chunkX, chunkZ, 15, Blocks.CONCRETE_POWDER, UltraUB7.class);
			this.generateStructureUnderground(new WorldGenStructure("ub07_2"), world, random, chunkX, chunkZ, 24, Blocks.CONCRETE_POWDER, UltraUB7.class);
			this.generateStructureUnderground(new WorldGenStructure("ub07_3"), world, random, chunkX, chunkZ, 15, Blocks.CONCRETE_POWDER, UltraUB7.class);
			this.generateStructureUnderground(new WorldGenStructure("ub07_4"), world, random, chunkX, chunkZ, 19, Blocks.CONCRETE_POWDER, UltraUB7.class);
			
			//Alpha
			this.generateStructureFly(new WorldGenStructure("ub_alpha1"), world, random, chunkX, chunkZ, 10, Blocks.END_STONE, UltraAlpha.class);
			this.generateStructureFly(new WorldGenStructure("ub_alpha2"), world, random, chunkX, chunkZ, 24, Blocks.END_STONE, UltraAlpha.class);
			
			//Beta
			this.generateStructureFly(new WorldGenStructure("ub_beta1"), world, random, chunkX, chunkZ, 20, Blocks.NETHERRACK, UltraBeta.class);
			this.generateStructureFly(new WorldGenStructure("float"), world, random, chunkX, chunkZ, 13, Blocks.NETHERRACK, UltraBeta.class);
			this.generateStructureFly(new WorldGenStructure("float1"), world, random, chunkX, chunkZ, 16, Blocks.NETHERRACK, UltraBeta.class);
			
			break;
		case 1:
			break;
		case 0:			
			break;
		case -1:
			break;
		}
	}
	
	//Generate normal Structures
	@SuppressWarnings("unchecked")
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}
	
	//Generate Float Strutures
	@SuppressWarnings("unchecked")
	private void generateStructureFly(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock) + 17;
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}
	
	//Generate Underground Structures
	@SuppressWarnings("unchecked")
	private void generateStructureUnderground(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock) - 5;
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
}
