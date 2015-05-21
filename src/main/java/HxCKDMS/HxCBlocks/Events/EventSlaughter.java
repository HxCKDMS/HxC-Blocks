package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import HxCKDMS.HxCCore.Entity.HxCFakePlayer;
import HxCKDMS.HxCCore.HxCCore;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventSlaughter {
    public void Slaughter(int[] coords, World world){
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)tile;
        int modifier = 1;
        if (HxCTile.inventory[1] != null) modifier += HxCTile.inventory[1].stackSize;
        List list  = world.getEntitiesWithinAABB(EntityLiving.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (EntityLiving entity : (List<EntityLiving>) list) {
            if (!entity.isDead && entity.deathTime == 0) {
                HxCFakePlayer pla = new HxCFakePlayer(HxCCore.server.worldServerForDimension(world.provider.dimensionId));
                pla.setItemInHand(HxCTile.inventory[0]);
                pla.attackEntityAsMob(entity);
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - mod, y - mod, z - mod,
        /** Indented because CDO :P **/    x + mod, y + mod, z + mod);
    }
}
