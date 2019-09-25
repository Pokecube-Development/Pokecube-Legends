package pokecube.legends.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import pokecube.legends.init.BlockInit;

public class TemporalStone extends BlockBase 
{
	public TemporalStone(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockInit.TEMPORAL_COBBLESTONE);
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
}
