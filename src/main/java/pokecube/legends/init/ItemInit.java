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
import pokecube.legends.items.AdamantOrb;
import pokecube.legends.items.AzureFlute;
import pokecube.legends.items.BlueOrb;
import pokecube.legends.items.BlueRune;
import pokecube.legends.items.ChippedPot;
import pokecube.legends.items.CrackedPot;
import pokecube.legends.items.DNASplicerA;
import pokecube.legends.items.DNASplicerB;
import pokecube.legends.items.DarkStone;
import pokecube.legends.items.DestructOrb;
import pokecube.legends.items.Emblem;
import pokecube.legends.items.Gracidea;
import pokecube.legends.items.GrayOrb;
import pokecube.legends.items.GreenOrb;
import pokecube.legends.items.GreenRune;
import pokecube.legends.items.GriseousOrb;
import pokecube.legends.items.IceCore;
import pokecube.legends.items.ItemBase;
import pokecube.legends.items.LegendaryOrb;
import pokecube.legends.items.LifeOrb;
import pokecube.legends.items.LightStone;
import pokecube.legends.items.LustrousOrb;
import pokecube.legends.items.MagmaCore;
import pokecube.legends.items.Meteorite;
import pokecube.legends.items.NMoon;
import pokecube.legends.items.NSun;
import pokecube.legends.items.OceanOrb;
import pokecube.legends.items.OrangeRune;
import pokecube.legends.items.PrisionBottle;
import pokecube.legends.items.RainbowSword;
import pokecube.legends.items.RedOrb;
import pokecube.legends.items.RegisOrb;
import pokecube.legends.items.RevealGlass;
import pokecube.legends.items.RockCore;
import pokecube.legends.items.RustedShield;
import pokecube.legends.items.RustedSword;
import pokecube.legends.items.SteelCore;
import pokecube.legends.items.ZygardeCube;
import pokecube.legends.items.natureedit.ItemNature;
import pokecube.legends.items.zmove.ItemZCrystal;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_RAINBOW = EnumHelper.addToolMaterial("material_rainbow", 5, 1600, 9.0F, 4.0F, 10);
	
	//Orbs
	public static final Item BLUEORB = new BlueOrb("blueorb", 1);
	public static final Item GREENORB = new GreenOrb("greenorb", 1);
	public static final Item REDORB = new RedOrb("redorb", 1);
	public static final Item GRAYORB = new GrayOrb("grayorb", 1);
	public static final Item LEGENDARYORB = new LegendaryOrb("legendaryorb", 1);
	public static final Item LUSTROUSORB = new LustrousOrb("lustrousorb", 1);
	public static final Item ADAMANTORB = new AdamantOrb("adamantorb", 1);
	public static final Item OCEANORB = new OceanOrb("oceanorb", 1);
	public static final Item LIGHTSTONE = new LightStone("lightstone", 1);
	public static final Item DARKSTONE = new DarkStone("darkstone", 1);
	public static final Item ROCKCORE = new RockCore("rockcore", 1);
	public static final Item ICECORE = new IceCore("icecore", 1);
	public static final Item STEELCORE = new SteelCore("steelcore", 1);
	public static final Item EMBLEM = new Emblem("emblem", 1);
	public static final Item MAGMA_CORE = new MagmaCore("magmacore", 1);
	public static final Item LIFEORB = new LifeOrb("lifeorb", 1);
	public static final Item DESTRUCTORB = new DestructOrb("destructorb", 1);
	public static final Item ORANGE_RUNE = new OrangeRune("orange_rune", 1);
	public static final Item BLUE_RUNE = new BlueRune("blue_rune", 1);
	public static final Item GREEN_RUNE = new GreenRune("green_rune", 1);
	public static final Item REGIS_ORB = new RegisOrb("regisorb", 1);
	
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
	public static final Item GRISEOUS_ORB = new GriseousOrb("griseousorb", 1);
	public static final Item ZYGARDE_CUBE = new ZygardeCube("zygardecube", 1);
	public static final Item PRISION_BOTTLE = new PrisionBottle("prisonbottle", 1);
	public static final Item REVEAL_GLASS = new RevealGlass("revealglass", 1);
	public static final Item DNA_SPLICERA = new DNASplicerA("dna_splicera", 1);
	public static final Item DNA_SPLICERB = new DNASplicerB("dna_splicerb", 1);
	public static final Item GRACIDEA = new Gracidea("gracidea", 10);
	public static final Item METEORITE = new Meteorite("meteorite", 16);
	public static final Item NSUN = new NSun("n_sun", 1);
	public static final Item NMOON = new NMoon("n_moon", 1);
	public static final Item AZURE_FLUTE = new AzureFlute("azure_flute", 1);
	public static final Item RSHIELD = new RustedShield("rustedshield", 1);
	public static final Item RSWORD = new RustedSword("rustedsword", 1);
	public static final Item CHPOT = new ChippedPot("chippedpot", 1);
	public static final Item CRPOT = new CrackedPot("crackedpot", 1);
	
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
