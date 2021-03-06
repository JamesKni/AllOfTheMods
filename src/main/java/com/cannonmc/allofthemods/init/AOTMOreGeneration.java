package com.cannonmc.allofthemods.init;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AOTMOreGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionId()) {
		case 0:
			generateOverworld(world, random, chunkX, chunkZ);
			
			break;
		case 1:
			generateEnd(world, random, chunkX, chunkZ);
			break;
		case -1:
			generateNether(world, random, chunkX, chunkZ);
			break;

		}

	} 
	
	public static void init() {
		GameRegistry.registerWorldGenerator(new AOTMOreGeneration(), 0);
	}
	
	public void generateEnd(World world, Random rand, int x, int z) {

	}

	public void generateOverworld(World world, Random rand, int x, int z) {
		generateOre(AOTMBlocks.the_ore, world, rand, x, z, 3, 8, 10, 5, 40, Blocks.stone);
		generateOre(AOTMBlocks.the_sky_rock, world, rand, x, z, 1, 4, 1, 240, 250, Blocks.air);
	}

	public void generateNether(World world, Random rand, int x, int z) {

	}
	
	public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, 
			int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), vienSize, BlockHelper.forBlock(generateIn));
		for(int i = 0; i < chance; i++) {
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			BlockPos blockPos = new BlockPos(xRand, yRand, zRand);
			gen.generate(world, random, blockPos);
		}
	}
	
	

}
