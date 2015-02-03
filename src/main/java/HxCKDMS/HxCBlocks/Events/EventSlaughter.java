package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventSlaughter {
    public void Slaughter(int[] coords, World world){
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)tile;
        int modifier = HxCTile.modifier;
        List list  = world.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (EntityLiving entity : (List<EntityLiving>) list) {
            if (!entity.isDead) {
//                String BoundPlayer = HxCTile.BoundPlayer;
                entity.attackEntityFrom(new DamageSource("SlaughterBlock"), 300f);
                if (!entity.worldObj.isRemote) entity.worldObj.spawnEntityInWorld(new EntityXPOrb(world, tile.xCoord, tile.yCoord+1, tile.zCoord, 1));
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - (0.5 + mod), y - (2 + mod), z - (0.5 + mod),
                /** Indented because CDO :P **/    (x + 0.5) + mod, (y + 2) + mod, z +(0.5 + mod));
    }
}
