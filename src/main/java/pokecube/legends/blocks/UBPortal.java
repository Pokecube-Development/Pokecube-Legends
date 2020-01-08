package pokecube.legends.blocks;

import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.init.function.UBActiveFunction;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

public class UBPortal extends BlockBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	protected double sizeX;
	protected double sizeY;
	protected double sizeZ;
	protected double posX;
	protected double posY;
	protected double posZ;
	
	public UBPortal(String name, Material material, double posX, double posY, double posZ, double sizeX, double sizeY, double sizeZ) 
	{
		super(name, Material.ROCK);
		setSoundType(SoundType.METAL);
		setHardness(1F);
		setResistance(10F);
		setLightLevel(0.9F);
		setLightOpacity(4);
		setBlockUnbreakable();
		
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	 {
		 return new AxisAlignedBB(posX, posY, posZ, sizeX, sizeY, sizeZ);
	 }
	
	/** allows items to add custom lines of information to the mouseover
     * description */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> list, ITooltipFlag advanced)
    {
        if (GuiScreen.isShiftKeyDown()) list.add(I18n.format("legends.ubportal.tooltip"));
        else list.add(I18n.format("pokecube.tooltip.advanced"));
    }

	//Rotation Block
		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{FACING});
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing) state.getValue(FACING)).getIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
				EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		}
    //
			
	//time for spawn
	@Override
	public int tickRate(World world) {
		return 800;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		{
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", x);
			dependencies.put("y", y);
			dependencies.put("z", z);
			dependencies.put("world", world);
			UBActiveFunction.executeProcedure(dependencies);
		}
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		Minecraft.getMinecraft();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int i = x;
		int j = y;
		int k = z;
		if (true)
			for (int l = 0; l < 1; ++l) {
				double d0 = (i + random.nextFloat());
				double d1 = (j + random.nextFloat());
				double d2 = (k + random.nextFloat());
				random.nextInt(2);
				double d3 = (random.nextFloat() - 0.5D) * 0.5D;
				double d4 = (random.nextFloat() - 0.5D) * 0.5D;
				double d5 = (random.nextFloat() - 0.5D) * 0.5D;
				world.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}
		if (random.nextInt(100) == 0) {
            world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.AMBIENT_CAVE, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }
	}
}