package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
@SuppressWarnings("unchecked")
public class TileBarrier extends TileEntity{
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

    public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote && !powered) {
            List list  = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(xCoord, yCoord, zCoord, modifier));
            for (EntityLiving entity : (List<EntityLiving>) list) {
                if (entity.posX > xCoord) {entity.motionX += 0.1;}
                if (entity.posX < xCoord) {entity.motionX -= 0.1;}
                if (entity.posZ > zCoord) {entity.motionZ += 0.1;}
                if (entity.posZ < zCoord) {entity.motionZ -= 0.1;}
            }
        }
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
    }
    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x-0.5, y-0.5, z-0.5, x+0.5, y+1+mod, z+0.5);
    }

    public boolean isPowered() {
        return isPoweringTo(worldObj, xCoord, yCoord + 1, zCoord, 0) ||
                isPoweringTo(worldObj, xCoord, yCoord - 1, zCoord, 1) ||
                isPoweringTo(worldObj, xCoord, yCoord, zCoord + 1, 2) ||
                isPoweringTo(worldObj, xCoord, yCoord, zCoord - 1, 3) ||
                isPoweringTo(worldObj, xCoord + 1, yCoord, zCoord, 4) ||
                isPoweringTo(worldObj, xCoord - 1, yCoord, zCoord, 5);
    }
    protected boolean powered = false;
    public static boolean isPoweringTo(World world, int x, int y, int z, int side) {
        return world.getBlock(x, y, z).isProvidingWeakPower(world, x, y, z, side) > 0;
    }
}