package pokecube.legends.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NMoon extends ItemBase {

	public NMoon(String name, int num) {
		super(name, num);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add("Use this item in 'Lunala' to change it.");
	}
}
