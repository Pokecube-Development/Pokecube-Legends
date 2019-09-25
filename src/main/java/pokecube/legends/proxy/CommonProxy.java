package pokecube.legends.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{

	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void preinit(FMLPreInitializationEvent evt) {}
	
}
