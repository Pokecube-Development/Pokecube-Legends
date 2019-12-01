package pokecube.legends.items.dynamax;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pokecube.legends.items.ItemBase;

public class DynamaxShard extends ItemBase {

	public DynamaxShard(String name, int num) {
		super(name, num);
		setMaxStackSize(16);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add("Put on your Pokemob to use Dynamax!");
	}
}
