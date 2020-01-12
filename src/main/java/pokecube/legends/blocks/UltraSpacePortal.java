package pokecube.legends.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pokecube.legends.handlers.TeleportUltraSpace;
import pokecube.legends.init.DimensionInit;
import pokecube.legends.init.function.WormHoleActiveFunction;

public class UltraSpacePortal extends BlockBase
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    EnumParticleTypes                     EnumParticlesTypes;
    protected double                      sizeX;
    protected double                      sizeY;
    protected double                      sizeZ;
    protected double                      posX;
    protected double                      posY;
    protected double                      posZ;

    public UltraSpacePortal(String name, Material material, double posX, double posY, double posZ, double sizeX,
            double sizeY, double sizeZ)
    {
        super(name, material);
        setSoundType(SoundType.GLASS);
        setHardness(5.0F);
        setResistance(15.0F);
        setHarvestLevel("pickace", 2);
        setLightLevel(0.8F);
        setLightOpacity(5);

        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
        if (GuiScreen.isShiftKeyDown()) list.add(I18n.format("legends.ultraportal.tooltip"));
        else list.add(I18n.format("pokecube.tooltip.advanced"));
    }

    // Rotation Block
    @Override
    protected net.minecraft.block.state.BlockStateContainer createBlockState()
    {
        return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[] { FACING });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
            float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    //

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    // Time for Despawn
    @Override
    public int tickRate(World world)
    {
        return 700;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(world, pos, state);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
    {
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
            WormHoleActiveFunction.executeProcedure(dependencies);
        }
        world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
    {
        super.randomDisplayTick(state, world, pos, random);
        Minecraft.getMinecraft();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int i = x;
        int j = y;
        int k = z;
        if (true) for (int l = 0; l < 1; ++l)
        {
            double d0 = (i + random.nextFloat());
            double d1 = (j + random.nextFloat());
            double d2 = (k + random.nextFloat());
            random.nextInt(2);
            double d3 = (random.nextFloat() - 0.5D) * 0.5D;
            double d4 = (random.nextFloat() - 0.5D) * 0.5D;
            double d5 = (random.nextFloat() - 0.5D) * 0.5D;
            world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, d0, d1, d2, d3, d4, d5);
        }
        if (random.nextInt(100) == 0)
        {
            world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.AMBIENT_CAVE,
                    SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }
    }
    //

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
            EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.isRiding() && !playerIn.isBeingRidden() && playerIn.isNonBoss())
        {
            if (!playerIn.world.isRemote && playerIn instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) playerIn;
                MinecraftServer minecraftServer = player.getServer();
                int dimensionId = world.provider.getDimension();
                int dimensionIn;

                if (player.timeUntilPortal > 0)
                {
                    player.timeUntilPortal = 5;
                }
                else
                {
                    player.isInvulnerableDimensionChange();
                    if (dimensionId == DimensionInit.ultraspaceDimensionID)
                    {
                        dimensionIn = 0;
                    }
                    else
                    {
                        dimensionIn = DimensionInit.ultraspaceDimensionID;
                    }
                    
                    WorldServer world1 = DimensionManager.getWorld(dimensionIn);
                    if (world1 == null)
                    {
                        DimensionManager.initDimension(dimensionIn);
                    }

                    player.timeUntilPortal = 5;
                    minecraftServer.getPlayerList().transferPlayerToDimension(player, dimensionIn,
                            new TeleportUltraSpace(minecraftServer.getWorld(dimensionIn)));
                }
            }
        }
        return false;
    }

}