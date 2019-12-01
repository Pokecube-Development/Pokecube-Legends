package pokecube.legends.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.IHasModel;
import pokecube.legends.Reference;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ItemInit;
import pokecube.legends.items.natureedit.UsableItemNature;

@EventBusSubscriber(modid=Reference.ID)
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
    public void onItemCapabilityAttach(AttachCapabilitiesEvent<ItemStack> event)
    {
        UsableItemNature.registerCapabilities(event);
    }
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ItemInit.ITEMS) 
		{
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block: BlockInit.BLOCKS) 
		{
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		//OreDictionary
    	OreDictionaryCompat.registerOreDictionaryEntries();
    	//Dimension structures
    	
	}
}
