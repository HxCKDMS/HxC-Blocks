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

                double newMotionX;
                if(xCoord - entity.posX > 0)
                    newMotionX = -entity.motionX - 0.08;
                else
                    newMotionX = -entity.motionX + 0.08;

                double newMotionZ;
                if(zCoord - entity.posZ > 0)
                    newMotionZ = -entity.motionZ - 0.08;
                else
                    newMotionZ = -entity.motionZ + 0.08;

                System.out.println("newMotionZ = " + newMotionZ);
                System.out.println("newMotionX = " + newMotionX);

                /*double newMotionX = -entity.motionX + (-entity.motionX < 0 ? -0.1 : 0.1);
                   double newMotionZ = -entity.motionZ + (-entity.motionZ < 0 ? -0.1 : 0.1);*/

                entity.addVelocity(newMotionX, -entity.motionY, newMotionZ);

                //entity.addVelocity(-entity.motionX - 0.07, -entity.motionY, -entity.motionZ - 0.07);
                //TODO: Make this properly working >_> Karel
            }
        }
    }
    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.1, y+4, z + 1.1);
    }
}