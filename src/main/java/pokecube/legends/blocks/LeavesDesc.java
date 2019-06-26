package pokecube.legends.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class LeavesDesc extends BlockBase 
{
	public LeavesDesc(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.PLANT);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("shovel", 1);
	}
}