package pokecube.legends.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import pokecube.legends.worldgen.dimensions.DimensionTemporal;

public class DimensionInit 
{
	public static final DimensionType TEMPORAL_PLACE = DimensionType.register("temporal", "_temporal", 2, DimensionTemporal.class, false);
	
	public static void registerDimensions() 
	{
		DimensionManager.registerDimension(2, TEMPORAL_PLACE);
	}
}
