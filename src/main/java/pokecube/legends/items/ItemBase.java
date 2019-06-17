package pokecube.legends.items;

import net.minecraft.item.Item;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.IHasModel;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.ModItems;

public class ItemBase extends Item implements IHasModel{
	
	public ItemBase(String name) {
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
