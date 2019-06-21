package pokecube.legends.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.IHasModel;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ItemInit;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(PokecubeMod.creativeTabPokecubeBlocks);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}

	@Override
	public void registerModels() 
	{
		PokecubeLegends.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
