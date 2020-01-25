package pokecube.legends.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import pokecube.legends.init.ItemInit;

public class RubyOre extends BlockBase 
{
	public RubyOre(String name, Material material) 
	{
		super(name, material);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.RUBY;
	}
	
	@Override
	public int quantityDropped(Random rand) {
		int max = 3;
		int min = 1;
		return rand.nextInt(max) + min;
	}
}
