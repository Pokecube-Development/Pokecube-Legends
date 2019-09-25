package pokecube.legends.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeInit 
{
	public static void init() 
	{
		GameRegistry.addSmelting(BlockInit.TEMPORAL_COBBLESTONE, new ItemStack(BlockInit.TEMPORAL_STONE, 1), 1.3F);
		
	}
}
