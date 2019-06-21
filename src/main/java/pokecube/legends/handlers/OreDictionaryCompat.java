package pokecube.legends.handlers;

import net.minecraftforge.oredict.OreDictionary;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ItemInit;

public class OreDictionaryCompat 
{
	public static void registerOreDictionaryEntries()
    {
        /*
         * Look in the OreDictionary class to check the strings for vanilla items.
         */
        OreDictionary.registerOre("oreRuby", BlockInit.RUBY_ORE);
        OreDictionary.registerOre("oresapphire", BlockInit.SAPPHIRE_ORE);
        OreDictionary.registerOre("genRuby", ItemInit.RUBY);
        OreDictionary.registerOre("genSapphire", ItemInit.SAPPHIRE);
    }
}
