package pokecube.legends.items.tools;

import net.minecraft.item.ItemSword;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.IHasModel;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.ModItems;

public class ToolSword extends ItemSword implements IHasModel
{
	public ToolSword(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(PokecubeMod.creativeTabPokecube);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		PokecubeLegends.proxy.registerItemRenderer(this, 0, "inventory");		
	}
}
