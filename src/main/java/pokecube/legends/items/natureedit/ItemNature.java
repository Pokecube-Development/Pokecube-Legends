package pokecube.legends.items.natureedit;

import java.util.List;

import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.core.interfaces.Nature;
import pokecube.legends.Reference;

public class ItemNature extends Item
{
	/*public MintAdamant(String name, int num) {
		super(name, num);
	}*/
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced)
    {
        list.add(type.name());
    }
    
    public static boolean isNature(ItemStack stackIn)
    {
        return stackIn != null && stackIn.getItem() instanceof ItemNature;
    }

    public final Nature type;

    public ItemNature(Nature type)
    {
        super();
        String name = type.name().equals("???") ? "unknown" : type.name();
        this.setRegistryName(Reference.ID, "mint_" + name);
        this.setUnlocalizedName("mint_" + name);
        this.type = type;
    }
}
