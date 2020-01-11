package pokecube.legends.init;

import javax.annotation.Nullable;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import pokecube.legends.worldgen.DimensionTypeUltraSpace;

public class DimensionInit
{
    public static int           ultraspaceDimensionID;
    public static int           ultraspacDimensionTypeID;
    public static DimensionType ULTRASPACE;

    public static void registerDimension()
    {
        ultraspaceDimensionID = findFreeDimensionID();
        ultraspacDimensionTypeID = findFreeDimensionTypeID();
        ULTRASPACE = DimensionType.register("Ultra_Space", "_ultraspace", ultraspacDimensionTypeID,
                DimensionTypeUltraSpace.class, false);
        DimensionManager.registerDimension(ultraspaceDimensionID, ULTRASPACE);
    }

    @Nullable
    private static Integer findFreeDimensionTypeID()
    {
        for (int i = 2; i < Integer.MAX_VALUE; i++)
        {
            try
            {
                DimensionType.getById(i);
            }
            catch (Exception e)
            {
                // Unused dimension type!
                return i;
            }
        }

        // DEBUG
        System.out.println("ERROR: Could not find free dimension ID");
        return null;
    }

    @Nullable
    private static Integer findFreeDimensionID()
    {
        for (int i = 2; i < Integer.MAX_VALUE; i++)
        {
            if (!DimensionManager.isDimensionRegistered(i))
            {
                // DEBUG
                System.out.println("Found free dimension ID = " + i);
                return i;
            }
        }

        // DEBUG
        System.out.println("ERROR: Could not find free dimension ID");
        return null;
    }

}
