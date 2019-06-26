package pokecube.legends.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PlanksDesc extends BlockBase 
{
	public PlanksDesc(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.WOOD);
		setHardness(2.5F);
		setResistance(4.0F);
		setHarvestLevel("axe", 2);
	}
}