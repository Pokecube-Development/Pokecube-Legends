package pokecube.legends.init.function;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.IPokemobUseable;
import pokecube.core.interfaces.Nature;
import pokecube.legends.Reference;
import pokecube.legends.items.natureedit.ItemNature;

public class UsableItemNatureEffects
{
    public static class NatureUsable implements IPokemobUseable, ICapabilityProvider
    {
        private final Nature nature;

        public NatureUsable(Nature nature)
        {
            this.nature = nature;
        }

        /** Called when this item is "used". Normally this means via right
         * clicking the pokemob with the itemstack. It can also be called via
         * onTick or onMoveTick, in which case user will be pokemob.getEntity()
         * 
         * @param user
         * @param pokemob
         * @param stack
         * @return something happened */

        @Override
        public ActionResult<ItemStack> onUse(IPokemob pokemob, ItemStack stack, EntityLivingBase user)
        {
            if (user != pokemob.getOwner()) return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
            boolean used = pokemob.getNature() != nature;
            if (used)
            {
                pokemob.setNature(nature);
                stack.splitStack(1);
            }
            return new ActionResult<ItemStack>(used ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, stack);
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == IPokemobUseable.USABLEITEM_CAP;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            return hasCapability(capability, facing) ? USABLEITEM_CAP.cast(this) : null;
        }
    }

    public static final ResourceLocation USABLE = new ResourceLocation(Reference.ID, "usables");

    /** 1.12 this needs to be ItemStack instead of item. */
    public static void registerCapabilities(AttachCapabilitiesEvent<ItemStack> event)
    {
        if (event.getCapabilities().containsKey(USABLE)) return;
        Item item = event.getObject().getItem();
        if (item instanceof ItemNature)
        {
            event.addCapability(USABLE, new NatureUsable(((ItemNature) item).type));
        }
    }
}