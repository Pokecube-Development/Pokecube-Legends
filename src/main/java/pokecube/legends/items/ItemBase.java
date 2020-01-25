package pokecube.legends.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.legends.IHasModel;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.ItemInit;

public class ItemBase extends Item implements IHasModel
{
    String tooltipname;

    public ItemBase(String name, int num)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(PokecubeMod.creativeTabPokecube);
        setMaxStackSize(num);
        ;
        this.tooltipname = name;
        ItemInit.ITEMS.add(this);
    }

    public ItemBase setTooltipName(String tooltipname)
    {
        this.tooltipname = tooltipname;
        return this;
    }

    /** allows items to add custom lines of information to the mouseover
     * description */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced)
    {
        if (GuiScreen.isShiftKeyDown()) list.add(I18n.format("legends." + tooltipname + ".tooltip"));
        else list.add(I18n.format("pokecube.tooltip.advanced"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels()
    {
        PokecubeLegends.proxy.registerItemRenderer(this, 0, "inventory");
    }
}