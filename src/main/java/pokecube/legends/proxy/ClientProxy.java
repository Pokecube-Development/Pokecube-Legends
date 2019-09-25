package pokecube.legends.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pokecube.legends.Reference;

public class ClientProxy extends CommonProxy
{
	public void registerItemRenderer(Item item,int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void preinit(FMLPreInitializationEvent evt) {
        OBJLoader.INSTANCE.addDomain(Reference.ID);
	}
}
