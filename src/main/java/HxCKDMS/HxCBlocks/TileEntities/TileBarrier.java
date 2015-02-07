package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;
@SuppressWarnings("unchecked")
public class TileBarrier extends TileEntity implements IUpdatePlayerListBox {
    public int modifier;

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("Mod", modifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
    }

    public void update() {
        if (worldObj != null && !worldObj.isRemote && !powered) {
            int nx; int ny; int nz;
            if (pos.getX() >= 0) nx = pos.getX() + 1;
            else nx = pos.getX() - 1;
            if (pos.getY() >= 0) ny = pos.getY() + 1;
            else ny = pos.getY() - 1;
            if (pos.getZ() >= 0) nz = pos.getZ() + 1;
            else nz = pos.getZ() - 1;
            List list  = worldObj.getEntitiesWithinAABB(Entity.class, getAreaBoundingBox(pos.getX(), pos.getY(), pos.getZ(), nx, ny, nz, modifier));
            for (EntityLiving entity : (List<EntityLiving>) list) {
                if (entity.posX > pos.getX()) {entity.motionX += 0.15;}
                if (entity.posX < pos.getX()) {entity.motionX -= 0.15;}
                if (entity.posZ > pos.getZ()) {entity.motionZ += 0.15;}
                if (entity.posZ < pos.getZ()) {entity.motionZ -= 0.15;}
            }
        }
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(int mx, int my, int mz, int Mx, int My, int Mz, int mod) {
        if (mx > 0) mx += mod; else mx -= mod;
        if (my > 0) my += mod; else my -= mod;
        if (mz > 0) mz += mod; else mz -= mod;
        if (Mx > 0) Mx += mod; else Mx -= mod;
        if (My > 0) My += mod; else My -= mod;
        if (Mz > 0) Mz += mod; else Mz -= mod;
        return AxisAlignedBB.fromBounds(mx, my, mz, Mx, My, Mz);
    }

    public boolean isPowered() {
        return isPoweringTo(worldObj, pos.getX(), pos.getY() + 1, pos.getZ(), EnumFacing.UP) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY() - 1, pos.getZ(), EnumFacing.DOWN) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() + 1, EnumFacing.SOUTH) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() - 1, EnumFacing.NORTH) ||
                isPoweringTo(worldObj, pos.getX() + 1, pos.getY(), pos.getZ(), EnumFacing.EAST) ||
                isPoweringTo(worldObj, pos.getX() - 1, pos.getY(), pos.getZ(), EnumFacing.WEST);
    }

    protected boolean powered = false;
    public static boolean isPoweringTo(World world, int x, int y, int z, EnumFacing side) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock().isProvidingWeakPower(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)), side) > 0;
    }
}