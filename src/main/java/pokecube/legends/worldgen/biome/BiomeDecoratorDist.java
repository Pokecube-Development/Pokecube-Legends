/**
    Copyright (C) 2017 by jabelar

    This file is part of jabelar's Minecraft Forge modding examples; as such,
    you can redistribute it and/or modify it under the terms of the GNU
    General Public License as published by the Free Software Foundation,
    either version 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    For a copy of the GNU General Public License see <http://www.gnu.org/licenses/>.
*/
package pokecube.legends.worldgen.biome;

import java.util.Random;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import pokecube.legends.init.BlockInit;

public class BiomeDecoratorDist extends BiomeDecorator
{ 
    // This predicate is used by WorldGen constructors to define
    // whether a block can be replaced by ore generation.
    Predicate<IBlockState> replaceablePredicate = new TempPredicate();
    
    // If you want to make these configurable, you'll need a ChunkGeneratorSettings
    // instance and use the fields from there instead.
    private int gravelSize = 33;
    private int graniteSize = 33;
    private int andesiteSize = 25;
    private int coalSize = 17;
    private int ironSize = 9;
    private int goldSize = 5;

    private int gravelCount = 8;
    private int graniteCount = 10;
    private int andesiteCount = 10;
    private int coalCount = 20;
    private int ironCount = 20;
    private int goldCount = 2;

    private int oreGenMinHeight = 0;

    private int gravelMaxHeight = 255;
    private int graniteMaxHeight = 80;
    private int andesiteMaxHeight = 80;
    private int coalMaxHeight = 126;
    private int ironMaxHeight = 64;
    private int goldMaxHeight = 22;

    /**
     * Instantiates a new biome decorator cloud.
     */
    public BiomeDecoratorDist()
    {
        super();
        
        // Must use predicate version if you wnat to replace custom blocks, otherwise will
        // only replace Blocks.STONE.
        gravelOreGen = new WorldGenMinable(BlockInit.TEMPORAL_STONE.getDefaultState(), gravelSize, replaceablePredicate);
        graniteGen = new WorldGenMinable(BlockInit.TEMPORAL_STONE.getDefaultState(), graniteSize, replaceablePredicate);
        andesiteGen = new WorldGenMinable(BlockInit.TEMPORAL_STONE.getDefaultState(), andesiteSize, replaceablePredicate);
        coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), coalSize, replaceablePredicate);
        ironGen = new WorldGenMinable(BlockInit.TEMPORAL_CRYSTAL.getDefaultState(), ironSize, replaceablePredicate);
        goldGen = new WorldGenMinable(BlockInit.ULTRA_BLOCK.getDefaultState(), goldSize, replaceablePredicate);
    }

    /**
     *  
     * This is the function where ore generation and things like flowers are generated.
     *
     * @param worldIn the world in
     * @param random the random
     * @param biome the biome
     * @param pos the pos
     */
    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        if (decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            chunkPos = pos;
            genDecorations(biome, worldIn, random);
            decorating = false;
        }
    }
    
    /**
     * This is where things like trees are generated.
     *
     * @param biomeIn the biome in
     * @param worldIn the world in
     * @param random the random
     */
    @SuppressWarnings("deprecation")
	@Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldIn, random, chunkPos));

        generateOres(worldIn, random);

        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND, sandGen, sandPatchesPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CLAY, clayGen, clayPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2, gravelGen, gravelPatchesPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2, gravelGen, gravelPatchesPerChunk);
        generateFlowers(worldIn, biomeIn, random, chunkPos);
        generateGrass(worldIn, biomeIn, random, chunkPos);

        if (generateFalls)
        {
            generateFalls(worldIn, random, chunkPos);
        }
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldIn, random, chunkPos));
    }
    
    @SuppressWarnings({ "unused", "deprecation" })
	private void generateTrees(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        int treesToGen = treesPerChunk;

        if (random.nextFloat() < extraTreeChance)
        {
            ++treesToGen;
        }

        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.TREE))
        for (int numTreesGenerated = 0; numTreesGenerated < treesToGen; ++numTreesGenerated)
        {
            int treeX = random.nextInt(16) + 8;
            int treeZ = random.nextInt(16) + 8;
            WorldGenAbstractTree treeGen = biomeIn.getRandomTreeFeature(random);
            treeGen.setDecorationDefaults();
            BlockPos blockpos = worldIn.getHeight(chunkPos.add(treeX, 0, treeZ));

            if (treeGen.generate(worldIn, random, blockpos))
            {
                treeGen.generateSaplings(worldIn, random, blockpos);
            }
        }
    }
    @SuppressWarnings("deprecation")
	private void generateFlowers(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.FLOWERS))
        for (int numFlowersGenerated = 0; numFlowersGenerated < flowersPerChunk; ++numFlowersGenerated)
        {
            int flowerX = random.nextInt(16) + 8;
            int flowerZ = random.nextInt(16) + 8;
            int yRange = worldIn.getHeight(chunkPos.add(flowerX, 0, flowerZ)).getY() + 32;
            
            if (yRange > 0)
            {
                int flowerY = random.nextInt(yRange);
                BlockPos flowerBlockPos = chunkPos.add(flowerX, flowerY, flowerZ);
                flowerGen.generate(worldIn, random, flowerBlockPos);
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	private void generateGrass(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.GRASS))
        for (int numGrassPerChunk = 0; numGrassPerChunk < grassPerChunk; ++numGrassPerChunk)
        {
            int grassX = random.nextInt(16) + 8;
            int grassZ = random.nextInt(16) + 8;
            int yRange = worldIn.getHeight(chunkPos.add(grassX, 0, grassZ)).getY() * 2;

            if (yRange > 0)
            {
                int grassY = random.nextInt(yRange);
                biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, chunkPos.add(grassX, grassY, grassZ));
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	private void generateFalls(World worldIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER))
        for (int k5 = 0; k5 < 50; ++k5)
        {
            int i10 = random.nextInt(16) + 8;
            int l13 = random.nextInt(16) + 8;
            int i17 = random.nextInt(248) + 8;

            if (i17 > 0)
            {
                int k19 = random.nextInt(i17);
                BlockPos blockpos6 = chunkPos.add(i10, k19, l13);
                (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldIn, random, blockpos6);
            }
        }

        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA))
        for (int l5 = 0; l5 < 20; ++l5)
        {
            int j10 = random.nextInt(16) + 8;
            int i14 = random.nextInt(16) + 8;
            int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
            BlockPos blockpos3 = chunkPos.add(j10, j17, i14);
            (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
        }
    }

    @SuppressWarnings("deprecation")
	private void generate(World worldIn, Random random, BlockPos chunkPos, EventType eventType, WorldGenerator generator, int countPerChunk)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, eventType))
        {
            for (int count = 0; count < countPerChunk; ++count)
            {
                int randX = random.nextInt(16) + 8;
                int randZ = random.nextInt(16) + 8;
                generator.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(chunkPos.add(randX, 0, randZ)));
            }
        }
    }

    /**
     * Generates ores in the current chunk.
     *
     * @param worldIn the world in
     * @param random the random
     */
    @Override
    protected void generateOres(World worldIn, Random random)
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, random, chunkPos));
        if (TerrainGen.generateOre(worldIn, random, gravelOreGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRAVEL))
        genStandardOre1(worldIn, random, gravelCount, gravelOreGen, oreGenMinHeight, gravelMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, graniteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GRANITE))
        genStandardOre1(worldIn, random, graniteCount, graniteGen, oreGenMinHeight, graniteMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, andesiteGen, chunkPos, OreGenEvent.GenerateMinable.EventType.ANDESITE))
        genStandardOre1(worldIn, random, andesiteCount, andesiteGen, oreGenMinHeight, andesiteMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, coalGen, chunkPos, OreGenEvent.GenerateMinable.EventType.COAL))
        genStandardOre1(worldIn, random, coalCount, coalGen, oreGenMinHeight, coalMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, ironGen, chunkPos, OreGenEvent.GenerateMinable.EventType.IRON))
        genStandardOre1(worldIn, random, ironCount, ironGen, oreGenMinHeight, ironMaxHeight);
        if (TerrainGen.generateOre(worldIn, random, goldGen, chunkPos, OreGenEvent.GenerateMinable.EventType.GOLD))
        genStandardOre1(worldIn, random, goldCount, goldGen, oreGenMinHeight, goldMaxHeight);
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, random, chunkPos));
    }

    static class TempPredicate implements Predicate<IBlockState>
    {
        private TempPredicate()
        {
        }

        /* (non-Javadoc)
         * @see com.google.common.base.Predicate#apply(java.lang.Object)
         */
        @Override
        public boolean apply(IBlockState parBlockState)
        {
            if (parBlockState != null && parBlockState.getBlock() == BlockInit.TEMPORAL_DIRT)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
