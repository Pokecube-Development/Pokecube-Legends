package pokecube.legends.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import pokecube.legends.worldgen.dimension.library.DimensionDistorted;

public class DimensionInit 
{
	public static final DimensionType DISTORTED_PLACE = DimensionType.register("distorted", "_distorted", 2, DimensionDistorted.class, false);
	
	public static void registerDimensions() 
	{
		DimensionManager.registerDimension(2, DISTORTED_PLACE);
	}
}
