package pokecube.legends.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.IHasModel;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.ItemInit;

public class ItemBase extends Item implements IHasModel{
	
	public ItemBase(String name, int num) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(PokecubeMod.creativeTabPokecube);
		setMaxStackSize(num);;
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		PokecubeLegends.proxy.registerItemRenderer(this, 0, "inventory");		
	}
}