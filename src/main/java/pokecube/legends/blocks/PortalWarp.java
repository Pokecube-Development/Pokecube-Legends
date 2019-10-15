package pokecube.legends.blocks;

import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.PortalActiveFunction;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import java.util.Random;

public class PortalWarp extends BlockBase {

	public PortalWarp(String name, Material material) 
	{
		super(name, Material.ROCK);
		setSoundType(SoundType.METAL);
		setHardness(1F);
		setResistance(10F);
		setLightLevel(0.9F);
		setLightOpacity(4);
		setBlockUnbreakable();
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public int tickRate(World world) {
		return 600;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(-1D, 0D, 0D, 2D, 3D, 1D);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		{
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", x);
			dependencies.put("y", y);
			dependencies.put("z", z);
			dependencies.put("world", world);
			PortalActiveFunction.executeProcedure(dependencies);
		}
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		Minecraft.getMinecraft();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int i = x;
		int j = y;
		int k = z;
		if (true)
			for (int l = 0; l < 1; ++l) {
				double d0 = (i + random.nextFloat());
				double d1 = (j + random.nextFloat());
				double d2 = (k + random.nextFloat());
				random.nextInt(2);
				double d3 = (random.nextFloat() - 0.5D) * 0.5D;
				double d4 = (random.nextFloat() - 0.5D) * 0.5D;
				double d5 = (random.nextFloat() - 0.5D) * 0.5D;
				world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}
		if (random.nextInt(100) == 0) {
            world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.AMBIENT_CAVE, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }
	}
}