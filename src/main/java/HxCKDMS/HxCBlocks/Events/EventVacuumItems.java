package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumItems {
    public void vacuum(int[] coords, World world) {
        world.markBlockForUpdate(coords[0], coords[1], coords[2]);
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileXPAbsorber HxCTile = (TileXPAbsorber)tile;
        HxCTile.getDescriptionPacket();
        int modifier = HxCTile.modifier;
        List list  = world.getEntitiesWithinAABB(EntityItem.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (EntityItem entity : (List<EntityItem>) list) {
            if (!entity.isDead) {
                Item item = entity.getEntityItem().getItem();
                entity.setDead();
                HxCTile.getDescriptionPacket();
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - (0.5 + mod), y - (2 + mod), z - (0.5 + mod),
        /** Indented because CDO :P **/    (x + 0.5) + mod, (y + 2) + mod, z +(0.5 + mod));
    }
}
