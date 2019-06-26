package pokecube.legends.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RegisteelCoreBlock extends BlockBase 
{
	public RegisteelCoreBlock(String name, Material material) 
	{
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 3);
	}
}
