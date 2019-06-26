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
        OreDictionary.registerOre("oreSapphire", BlockInit.SAPPHIRE_ORE);
        OreDictionary.registerOre("gemRuby", ItemInit.RUBY);
        OreDictionary.registerOre("gemSapphire", ItemInit.SAPPHIRE);
        OreDictionary.registerOre("plankWood", BlockInit.PLANKS_DESCONSTRUTED);
    }
}
