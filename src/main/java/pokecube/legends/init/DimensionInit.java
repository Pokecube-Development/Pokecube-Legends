package pokecube.legends.init;

import javax.annotation.Nullable;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import pokecube.legends.worldgen.DimensionTypeUltraSpace;

public class DimensionInit {
	
	public static void MainRegistry() {
		registerDimension();
	}
	
	public static final int ultraspaceDimensionID = findFreeDimensionID();
	public static final DimensionType ULTRASPACE = DimensionType.register("Ultra_Space", "_ultraspace", ultraspaceDimensionID, DimensionTypeUltraSpace.class, false);
	
	public static void registerDimension() {
		DimensionManager.registerDimension(ultraspaceDimensionID, ULTRASPACE);
	}
	
	@Nullable
    private static Integer findFreeDimensionID()
    {
        for (int i=2; i<Integer.MAX_VALUE; i++)
        {
            if (!DimensionManager.isDimensionRegistered(i))
            {
                // DEBUG
                System.out.println("Found free dimension ID = "+i);
                return i;
            }
        }
        
        // DEBUG
        System.out.println("ERROR: Could not find free dimension ID");
        return null;
    }

}
