package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

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
        if (worldObj != null && !worldObj.isRemote) {
            List list  = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(xCoord, yCoord, zCoord, modifier));
            for (EntityLiving entity : (List<EntityLiving>) list) {
                if (entity.posX > xCoord) {entity.motionX += 0.1;}
                if (entity.posX < xCoord) {entity.motionX -= 0.1;}
                if (entity.posZ > zCoord) {entity.motionZ += 0.1;}
                if (entity.posZ < zCoord) {entity.motionZ -= 0.1;}
            }
        }
    }
    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x-0.25, y-0.25, z-0.25, x+0.25, y+1+mod, z+0.25);
    }
}