package pokecube.legends.init;

import static pokecube.core.PokecubeItems.registerItemTexture;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import pokecube.core.PokecubeItems;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.Nature;
import pokecube.core.items.ItemHeldItems;

public class ItemGeneratorNature
{
    public static interface INatureModifier
    {
        void processHeldItemUse(Nature natureUse, IPokemob mob, ItemStack held);
    }

    public static Map<Predicate<ItemStack>, INatureModifier> ITEMMODIFIERS  = Maps.newHashMap();

    public static ArrayList<String>                        variants       = Lists.newArrayList();

    public static void makeHeldItems(Object registry)
    {
        for (String type : variants)
        {
            ItemHeldItems item = new ItemHeldItems(type);
            PokecubeItems.register(item, registry);
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            {
                registerItemTexture(item, 0, new ModelResourceLocation("pokecube:" + type, "inventory"));
            }
            ItemStack stack = new ItemStack(item, 1, 0);
            PokecubeItems.addToHoldables(stack);
        }
    }
    
    public static void processHeldItemUse(Nature natureUse, IPokemob mob, ItemStack held)
    {
        for (Map.Entry<Predicate<ItemStack>, INatureModifier> entry : ITEMMODIFIERS.entrySet())
        {
            if (entry.getKey().test(held))
            {
                entry.getValue().processHeldItemUse(natureUse, mob, held);
            }
        }
    }
}
