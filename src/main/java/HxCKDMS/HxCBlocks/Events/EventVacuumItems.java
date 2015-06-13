package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
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
    public void vacuum(int[] coords, World world) {
        int modifier;
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileVacuum HxCTile = (TileVacuum)tile;
        if (HxCTile.inventory[12] != null) {
            modifier = HxCTile.inventory[12].stackSize + 1;
            List list  = world.getEntitiesWithinAABB(EntityItem.class, AABBUtils.getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
            for (EntityItem entity : (List<EntityItem>) list) {
                if (!entity.isDead) {
                    ItemStack s = IOHelper.insert(HxCTile, entity.getEntityItem(), ForgeDirection.UNKNOWN, true);
                    if (s == null) {
                        IOHelper.insert(HxCTile, entity.getEntityItem(), ForgeDirection.UNKNOWN, false);
                        entity.setDead();
                    }
                }
            }
        }
    }
}
