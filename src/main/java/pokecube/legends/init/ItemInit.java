package pokecube.legends.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import pokecube.legends.items.AdamantOrb;
import pokecube.legends.items.BlueOrb;
import pokecube.legends.items.GrayOrb;
import pokecube.legends.items.GreenOrb;
import pokecube.legends.items.GriseousOrb;
import pokecube.legends.items.ItemBase;
import pokecube.legends.items.LegendaryOrb;
import pokecube.legends.items.LustrousOrb;
import pokecube.legends.items.OceanOrb;
import pokecube.legends.items.LightStone;
import pokecube.legends.items.RedOrb;
import pokecube.legends.items.DarkStone;
import pokecube.legends.items.tools.ToolSword;

public class ItemInit 
{
	
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
	
	//Gens_ores
	public static final Item SAPPHIRE = new ItemBase("sapphire", 64);
	public static final Item RUBY = new ItemBase("ruby", 64);
	
	//Extra
	public static final Item SILVER_WING = new ItemBase("silver_wing", 5);
	public static final Item RAINBOW_WING = new ItemBase("rainbow_wing", 5);
	public static final Item CRYSTAL_SHARD = new ItemBase("crystal_shard", 35);
	public static final Item GRISEOUS_ORB = new GriseousOrb("griseousorb", 1);
	public static final Item ZYGARDE_CUBE = new ItemBase("zygardecube", 1);
	public static final Item PRISION_BOTTLE= new ItemBase("prisonbottle", 1);
	public static final Item REVEAL_GLASS = new ItemBase("revealglass", 1);
	public static final Item DNA_SPLICERA = new ItemBase("dna_splicera", 1);
	public static final Item DNA_SPLICERB = new ItemBase("dna_splicerb", 1);
	public static final Item GRACIDEA = new ItemBase("gracidea", 10);
	//Tools
	public static final ItemSword RAINBOW_SWORD = new ToolSword("rainbow_sword", MATERIAL_RAINBOW);
}
