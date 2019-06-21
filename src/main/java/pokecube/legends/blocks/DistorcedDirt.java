package pokecube.legends.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class DistorcedDirt extends BlockBase 
{
	public DistorcedDirt(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.GROUND);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("shovel", 2);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) 
	{
		return true;
	}
}
