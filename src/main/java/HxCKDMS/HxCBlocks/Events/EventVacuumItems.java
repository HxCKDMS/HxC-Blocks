package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.IOHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumItems {
    public void vacuum(int x, int y, int z, World world) {
        int modifier;
        TileEntity tile = world.getTileEntity(x, y, z);
        HxCTile hxCTile = (HxCTile)tile;
        if (hxCTile.inventory[12] != null) {
            modifier = hxCTile.inventory[12].stackSize + 1;
            List list  = world.getEntitiesWithinAABB(EntityItem.class, AABBUtils.getAreaBoundingBox(x, y, z, modifier));
            for (EntityItem entity : (List<EntityItem>) list) {
                if (!entity.isDead) {
                    ItemStack s = IOHelper.insert(hxCTile, entity.getEntityItem(), ForgeDirection.UNKNOWN, true);
                    if (s == null) {
                        IOHelper.insert(hxCTile, entity.getEntityItem(), ForgeDirection.UNKNOWN, false);
                        entity.setDead();
                    }
                }
            }
            hxCTile.exportItem(64);
        }
    }
}
