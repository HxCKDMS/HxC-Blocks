package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumXP {
    public void vacuum(int x, int y, int z, World world) {
        TileEntity tile = world.getTileEntity(x, y, z);
        HxCTile hxCTile = (HxCTile)tile;
        int modifier = hxCTile.inventory[1].stackSize + 1;
        List list  = world.getEntitiesWithinAABB(EntityXPOrb.class, AABBUtils.getAreaBoundingBox(x, y, z, modifier));
        for (EntityXPOrb entity : (List<EntityXPOrb>) list) {
            if (!entity.isDead) {
                hxCTile.XP += entity.getXpValue();
                entity.setDead();
            }
        }
        if (hxCTile.XP > 1000) {
            String BoundPlayer = hxCTile.inventory[0].getTagCompound().getString("Player");
            try {
                EntityPlayer player = world.getPlayerEntityByName(BoundPlayer);
                player.addExperience(hxCTile.XP);
                hxCTile.XP = 0;
            } catch (Exception ignored){}
        }
    }
}
