package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumXP {
    public void vacuum(int[] coords, World world) {
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileXPAbsorber HxCTile = (TileXPAbsorber)tile;
        int modifier = HxCTile.Range + 1;
        List list  = world.getEntitiesWithinAABB(EntityXPOrb.class, AABBUtils.getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (EntityXPOrb entity : (List<EntityXPOrb>) list) {
            if (!entity.isDead) {
                HxCTile.XP += entity.getXpValue();
                entity.setDead();
            }
        }
        if (HxCTile.XP > 1000){
            String BoundPlayer = HxCTile.Item.getTagCompound().getString("Player");
            try {
                System.out.println(BoundPlayer);
                EntityPlayer player = world.getPlayerEntityByName(BoundPlayer);
                player.addExperience(HxCTile.XP);
                HxCTile.XP = 0;
            } catch (Exception ignored){}
        }
    }
}
