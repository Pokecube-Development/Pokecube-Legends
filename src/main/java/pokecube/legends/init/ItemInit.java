package pokecube.legends.init;

import static pokecube.core.PokecubeItems.register;
import static pokecube.core.PokecubeItems.registerItemTexture;
import static pokecube.core.interfaces.PokecubeMod.creativeTabPokecube;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import pokecube.core.PokecubeItems;
import pokecube.core.handlers.ItemGenerator;
import pokecube.core.interfaces.Nature;
import pokecube.core.utils.PokeType;
import pokecube.legends.items.ItemBase;
import pokecube.legends.items.LegendaryOrb;
import pokecube.legends.items.RainbowSword;
import pokecube.legends.items.natureedit.ItemNature;
import pokecube.legends.items.zmove.ItemZCrystal;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_RAINBOW = EnumHelper.addToolMaterial("material_rainbow", 5, 1600, 9.0F, 4.0F, 10);
	
	//Orbs
	public static final Item BLUEORB = new ItemBase("blueorb", 1);
	public static final Item GREENORB = new ItemBase("greenorb", 1);
	public static final Item REDORB = new ItemBase("redorb", 1);
	public static final Item GRAYORB = new ItemBase("grayorb", 1);
	public static final Item LEGENDARYORB = new LegendaryOrb("legendaryorb", 1);
	public static final Item LUSTROUSORB = new ItemBase("lustrousorb", 1);
	public static final Item ADAMANTORB = new ItemBase("adamantorb", 1);
	public static final Item OCEANORB = new ItemBase("oceanorb", 1);
	public static final Item LIGHTSTONE = new ItemBase("lightstone", 1);
	public static final Item DARKSTONE = new ItemBase("darkstone", 1);
	public static final Item ROCKCORE = new ItemBase("rockcore", 1);
	public static final Item ICECORE = new ItemBase("icecore", 1);
	public static final Item STEELCORE = new ItemBase("steelcore", 1);
	public static final Item EMBLEM = new ItemBase("emblem", 1);
	public static final Item MAGMA_CORE = new ItemBase("magmacore", 1);
	public static final Item LIFEORB = new ItemBase("lifeorb", 1);
	public static final Item DESTRUCTORB = new ItemBase("destructorb", 1);
	public static final Item ORANGE_RUNE = new ItemBase("orange_rune", 1);
	public static final Item BLUE_RUNE = new ItemBase("blue_rune", 1);
	public static final Item GREEN_RUNE = new ItemBase("green_rune", 1);
	public static final Item REGIS_ORB = new ItemBase("regisorb", 1);
	
	//Dynamax
	//public static final Item FRAGMENTDYN = new FragmentsDyna("fragmentdyn", 32);
	//public static final Item DYNAMAXSHARD = new DynamaxShard("dynamaxshard", 16);
	//public static final Item GIGANTAMAXSHARD = new GigantamaxShard("gigantamaxshard", 6);
	
	//Gens_ores
	public static final Item SAPPHIRE = new ItemBase("sapphire", 64);
	public static final Item RUBY = new ItemBase("ruby", 64);
	
	//Extra
	public static final Item SILVER_WING = new ItemBase("silver_wing", 5);
	public static final Item RAINBOW_WING = new ItemBase("rainbow_wing", 5);
	public static final Item CRYSTAL_SHARD = new ItemBase("crystal_shard", 35);
	public static final Item GRISEOUS_ORB = new ItemBase("griseousorb", 1);
	public static final Item ZYGARDE_CUBE = new ItemBase("zygardecube", 1);
	public static final Item PRISION_BOTTLE = new ItemBase("prisonbottle", 1);
	public static final Item REVEAL_GLASS = new ItemBase("revealglass", 1);
	public static final Item DNA_SPLICERA = new ItemBase("dna_splicera", 1).setTooltipName("dnasplicer");
	public static final Item DNA_SPLICERB = new ItemBase("dna_splicerb", 1).setTooltipName("dnasplicer");
	public static final Item GRACIDEA = new ItemBase("gracidea", 10);
	public static final Item METEORITE = new ItemBase("meteorite", 16);
	public static final Item NSUN = new ItemBase("n_sun", 1);
	public static final Item NMOON = new ItemBase("n_moon", 1);
	public static final Item AZURE_FLUTE = new ItemBase("azure_flute", 1);
	public static final Item RSHIELD = new ItemBase("rustedshield", 1);
	public static final Item RSWORD = new ItemBase("rustedsword", 1);
	public static final Item CHPOT = new ItemBase("chippedpot", 1);
	public static final Item CRPOT = new ItemBase("crackedpot", 1);
	
	//Mint Nature
	//public static final Item MINT_1 = new MintLonely("mint_lonely", 1);
	
	//Tools
	public static final ItemSword RAINBOW_SWORD = new RainbowSword("rainbow_sword", MATERIAL_RAINBOW);
	
	//Nature Item
	public static void addMint(Object registry)
    {
        for (Nature type : Nature.values())
        {
            Item mind = new ItemNature(type).setCreativeTab(creativeTabPokecube);
            register(mind, registry);
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            {
                String name = type.name().equals("???") ? "unknown" : type.name();
                registerItemTexture(mind, 0,
                        new ModelResourceLocation("pokecube_legends:" + "mint_" + name, "inventory"));
            }
            ItemStack stack = new ItemStack(mind, 1, 0);
            PokecubeItems.addToHoldables(stack);
        }
    }
	
	//Z-Crystal Item
		public static void addZCrystal(Object registry)
	    {
	        for (PokeType type : PokeType.values())
	        {
	            Item crystal = new ItemZCrystal(type).setCreativeTab(creativeTabPokecube);
	            register(crystal, registry);
	            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
	            {
	                String name = type.name().equals("???") ? "unknown" : type.name();
	                registerItemTexture(crystal, 0,
	                        new ModelResourceLocation("pokecube_legends:" + "z_" + name, "inventory"));
	            }
	            ItemStack stack = new ItemStack(crystal, 1, 0);
	            PokecubeItems.addToHoldables(stack);
	        }
	    }
	
    public static void registerItems(Object registry) {
        addMint(registry);
        
        ItemGenerator.ITEMMODIFIERS.put(new Predicate<ItemStack>()
        {
            @Override
            public boolean test(ItemStack t)
            {
                return ItemNature.isNature(t);
            }
        }, null);
        
        addZCrystal(registry);
        
        ItemGenerator.ITEMMODIFIERS.put(new Predicate<ItemStack>()
        {
            @Override
            public boolean test(ItemStack t)
            {
                return ItemZCrystal.isZCrystal(t);
            }
        }, null);
     }
}
