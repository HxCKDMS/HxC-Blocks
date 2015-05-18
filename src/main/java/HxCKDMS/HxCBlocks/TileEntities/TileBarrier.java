package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;
@SuppressWarnings("unchecked")
public class TileBarrier extends TileEntity{
    public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote) {
            List list  = worldObj.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(xCoord, yCoord, zCoord));
            for (EntityLiving entity : (List<EntityLiving>) list) {
                if (entity.posX > xCoord) {entity.motionX += 0.15;}
                if (entity.posX < xCoord) {entity.motionX -= 0.15;}
                if (entity.posZ > zCoord) {entity.motionZ += 0.15;}
                if (entity.posZ < zCoord) {entity.motionZ -= 0.15;}
            }
        }
    }
    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x+0.99, y+1, z+0.99);
    }
}