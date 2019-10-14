package pokecube.legends.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pokecube.legends.items.tools.ToolSword;

public class RainbowSword extends ToolSword {

	public RainbowSword(String name, ToolMaterial material) {
		super(name, material);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add("Use this item on 'Legendary Spawn' to Spawn Keldeo");
	}
}
