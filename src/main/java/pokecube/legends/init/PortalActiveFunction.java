package pokecube.legends.init;

import net.minecraft.world.WorldServer;
import pokecube.core.database.Database;
import pokecube.core.database.PokedexEntry;
import pokecube.core.interfaces.IPokemob;
import pokecube.core.interfaces.PokecubeMod;
import pokecube.core.interfaces.capabilities.CapabilityPokemob;
import thut.api.maths.Vector3;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;


/** Uses player interact here to also prevent opening of inventories.
 * 
 * @param dependencies */
public class PortalActiveFunction {
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
		if (!world.isRemote) {
			int max = Database.getSortedFormes().size(); 
			int min = 1;
			Random r = new Random();
			
			int i = r.nextInt(max) - min;
			
			PokedexEntry entityToSpawn = Database.getSortedFormes().get(i);
			BlockPos pos = null;
			EntityLiving entity = (EntityLiving) PokecubeMod.core.createPokemob(entityToSpawn, world);
			Vector3 location = Vector3.getNewVector().set(pos);
			if (entity != null) {
	            IPokemob pokemob = CapabilityPokemob.getPokemobFor(entity);
				entity.setHealth(entity.getMaxHealth());
                location.add(0, 1, 0).moveEntity(entity);
				entity.setPosition(x, y, z);
				world.spawnEntity(entity);
			}
		}
		world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
		if (world instanceof WorldServer)
			((WorldServer) world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x, y, z, (int) 15, 3, 3, 3, 1, new int[0]);
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.wither.death")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}