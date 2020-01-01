package pokecube.legends.init.function;

import net.minecraft.world.WorldServer;
//import pokecube.core.interfaces.IPokemob;
//import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.player.EntityPlayer;


/** Uses player interact here to also prevent opening of inventories.
 * 
 * @param dependencies */
public class WormHoleActiveFunction {
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for PortalActive!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for PortalActive!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for PortalActive!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for PortalActive!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
		if (world instanceof WorldServer)
			((WorldServer) world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y+1, z, (int) 15, 6, 6, 6, 0.4, new int[0]);
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.wither.death")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}