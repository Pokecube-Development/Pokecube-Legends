package pokecube.legends.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import pokecube.legends.items.ItemBase;
import pokecube.legends.items.tools.ToolSword;

public class ModItems 
{
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_RAINBOW = EnumHelper.addToolMaterial("material_rainbow", 5, 1600, 9.0F, 4.0F, 10);
	
	//Orbs
	public static final Item BLUEORB = new ItemBase("blueorb");
	public static final Item GREENORB = new ItemBase("greenorb");
	public static final Item REDORB = new ItemBase("redorb");
	public static final Item GRAYORB = new ItemBase("grayorb");
	public static final Item LEGENDARYORB = new ItemBase("legendaryorb");
	
	//Gens_ores
	public static final Item SAPPHIRE = new ItemBase("sapphire");
	public static final Item RUBY = new ItemBase("ruby");
	
	//Extra
	public static final Item SILVER_WING = new ItemBase("silver_wing");
	public static final Item RAINBOW_WING = new ItemBase("rainbow_wing");

	//Tools
	public static final ItemSword RAINBOW_SWORD = new ToolSword("rainbow_sword", MATERIAL_RAINBOW);
}
