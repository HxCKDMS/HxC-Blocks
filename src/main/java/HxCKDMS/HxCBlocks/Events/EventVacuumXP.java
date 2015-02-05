package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventVacuumXP {
    public void vacuum(int[] coords, World world) {
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileXPAbsorber HxCTile = (TileXPAbsorber)tile;
        int modifier = HxCTile.modifier;
        List list  = world.getEntitiesWithinAABB(EntityXPOrb.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (EntityXPOrb entity : (List<EntityXPOrb>) list) {
            if (!entity.isDead) {
                HxCTile.XP = (entity.getXpValue()+HxCTile.XP);
                entity.setDead();
            }
        }
        if (HxCTile.XP > 1000){
            String BoundPlayer = HxCTile.BoundPlayer;
            try {
                EntityPlayer player = world.getPlayerEntityByName(BoundPlayer);
                player.addExperience(HxCTile.XP);
                HxCTile.XP = 0;
            } catch (Exception ignored){}
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - 1 + mod, y - 1 + mod, z - 1 + mod,
        /** Indented because CDO :P **/    x + 0.5 + mod, y + 1 + mod, z +1 + mod);
    }
}
