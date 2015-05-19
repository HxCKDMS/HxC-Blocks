package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
import HxCKDMS.HxCCore.Utils.IOHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumItems {
    public void vacuum(int[] coords, World world) {
        int modifier = 0;
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileVacuum HxCTile = (TileVacuum)tile;
        if (HxCTile.inventory[12] != null) modifier = HxCTile.inventory[12].stackSize;
        List list  = world.getEntitiesWithinAABB(EntityItem.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
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

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - 1 - mod, y - 1 - mod, z - 1 - mod,
        /** Indented because CDO :P **/    x + 1 + mod, y + 1 + mod, z + 1 + mod);
    }
}
