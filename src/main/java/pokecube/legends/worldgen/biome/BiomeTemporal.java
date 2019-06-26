package pokecube.legends.worldgen.biome;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import pokecube.legends.init.BlockInit;
import pokecube.legends.worldgen.worldgen.WorldGenDesconstrutedTree;

public class BiomeTemporal extends Biome 
{	
	
	protected static final WorldGenAbstractTree TREE = new WorldGenDesconstrutedTree();
	
	public BiomeTemporal()
	{
		super(new BiomeProperties("Temporal").setBaseHeight(1.5F).setHeightVariation(1.2F).setTemperature(0.6F).setWaterColor(789516));
		topBlock = BlockInit.TEMPORAL_DIRT.getDefaultState();
		fillerBlock = BlockInit.TEMPORAL_STONE.getDefaultState();
		
		this.decorator.treesPerChunk = 4;
    }
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) 
	{
		return 0x4C186B;
	}
	
	@Override
	public int getSkyColorByTemp(float currentTemperature) 
	{
		return 0x000000;
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) 
	{
		return 0x4C186B;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		return TREE;
	}
	
	@Override
    public BiomeDecorator createBiomeDecorator()
    {
        BiomeDecorator biomeDecorator = new BiomeDecoratorDist();
        
        biomeDecorator.extraTreeChance = 1F;
        biomeDecorator.flowersPerChunk = 10;
        biomeDecorator.grassPerChunk = 1;
        biomeDecorator.deadBushPerChunk = 0;
        biomeDecorator.gravelPatchesPerChunk = 1;
        biomeDecorator.sandPatchesPerChunk = 3;
        biomeDecorator.clayPerChunk = 1;
        biomeDecorator.bigMushroomsPerChunk = 0;
        biomeDecorator.generateFalls = true;

        return getModdedBiomeDecorator(biomeDecorator);
    }

	@Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        int seaLevel = worldIn.getSeaLevel();
        IBlockState surfaceBlock = topBlock;
        IBlockState mainBlock = fillerBlock;
        int j = -1;
        int noise = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        MutableBlockPos pos = new MutableBlockPos();

        for (int primerY = 255; primerY >= 0; --primerY)
        {
            // lay down bedrock layer
            if (primerY <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, BEDROCK);
            }
            else
            {
                IBlockState blockAtPosition = chunkPrimerIn.getBlockState(chunkX, primerY, chunkZ);

                if (blockAtPosition.getMaterial() == Material.AIR)
                {
                    j = -1;
                }
                else if (blockAtPosition.getBlock() == fillerBlock)
                {
                    if (j == -1)
                    {
                        // create area for ocean
                        if (noise <= 0)
                        {
                            surfaceBlock = AIR;
                            mainBlock = fillerBlock;
                        }
                        // handle near sea level
                        else if (primerY >= seaLevel - 4 && primerY <= seaLevel + 1)
                        {
                            surfaceBlock = topBlock;
                            mainBlock = fillerBlock;
                        }
                        
                        // area exposed to air will be ocean
                        if (primerY < seaLevel && (surfaceBlock == null || surfaceBlock.getMaterial() == Material.AIR))
                        {
                            if (getTemperature(pos.setPos(x, primerY, z)) < 0.15F)
                            {
                                surfaceBlock = ICE;
                            }
                            else
                            {
                                surfaceBlock = WATER;
                            }
                        }

                        j = noise;

                        if (primerY >= seaLevel - 1)
                        {
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, surfaceBlock);
                        }
                        // fill in ocean bottom
                        else if (primerY < seaLevel - 7 - noise)
                        {
                            surfaceBlock = AIR;
                            mainBlock = fillerBlock;
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, fillerBlock);
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, mainBlock);
                        }
                    }
                    else if (j > 0) // fill in terrain with main block
                    {
                        --j;
                        chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, mainBlock);
                    }
                }
            }
        }
    }
}