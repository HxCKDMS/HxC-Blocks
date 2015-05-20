package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;
@SuppressWarnings("unchecked")
public class TileBarrier extends TileEntity{
    public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote) {
            List list  = worldObj.getEntitiesWithinAABB(Entity.class, getAreaBoundingBox(xCoord, yCoord, zCoord));
            for (Entity entity : (List<Entity>) list) {
                entity.motionX = -entity.motionX;
                entity.motionZ = -entity.motionZ;
            }
        }
    }
    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 0.99, y+2.99, z + 0.99);
    }
}