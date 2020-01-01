package pokecube.legends.items.zmove;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.core.utils.PokeType;
import pokecube.legends.Reference;
import pokecube.legends.items.natureedit.ItemNature;

public class ItemZCrystal extends Item
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
    
    public static boolean isZCrystal(ItemStack stackIn)
    {
        return stackIn != null && stackIn.getItem() instanceof ItemNature;
    }

    public final PokeType type;

    public ItemZCrystal(PokeType type)
    {
        super();
        String name = type.name.equals("???") ? "unknown" : type.name;
        this.setRegistryName(Reference.ID, "z_" + name);
        this.setUnlocalizedName("z_" + name);
        this.type = type;
    }
}
