package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCCore.Entity.HxCFakePlayer;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventSlaughter {
    public void Slaughter(int x, int y, int z, World world){
        TileEntity tile = world.getTileEntity(x, y, z);
        HxCTile hxCTile = (HxCTile)tile;
        int modifier = 1;
        if (hxCTile.inventory[1] != null) modifier += hxCTile.inventory[1].stackSize;
        List list  = world.getEntitiesWithinAABB(EntityLiving.class, AABBUtils.getAreaBoundingBox(x, y, z, modifier));
        for (EntityLiving entity : (List<EntityLiving>) list) {
            if (!entity.isDead && entity.deathTime == 0) {
                HxCFakePlayer pla = new HxCFakePlayer(HxCCore.server.worldServerForDimension(world.provider.dimensionId));
                pla.setItemInHand(hxCTile.inventory[0]);
                pla.attackTargetEntityWithCurrentItem(entity);
            }
        }
    }
}
