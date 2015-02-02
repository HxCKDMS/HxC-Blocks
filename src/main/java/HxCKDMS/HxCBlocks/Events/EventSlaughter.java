package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.entity.Entity;
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
        world.markBlockForUpdate(coords[0], coords[1], coords[2]);
        TileEntity tile = world.getTileEntity(coords[0], coords[1], coords[2]);
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)tile;
        HxCTile.getDescriptionPacket();
        int modifier = HxCTile.modifier;
        List list  = world.getEntitiesWithinAABB(Entity.class, getAreaBoundingBox(coords[0], coords[1], coords[2], modifier));
        for (Entity entity : (List<Entity>) list) {
            if (entity instanceof EntityLiving && !entity.isDead) {
                String BoundPlayer = HxCTile.player;
                EntityLiving ent = (EntityLiving) entity;
                ent.setLastAttacker(world.getPlayerEntityByName(BoundPlayer));
                ent.attackEntityFrom(new DamageSource("SlaughterBlock"), 300f);
                ent.setLastAttacker(world.getPlayerEntityByName(BoundPlayer));
                ent.worldObj.spawnEntityInWorld(new EntityXPOrb(world, coords[0], coords[1]+0.5, coords[2], 2));
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(float x, float y, float z, int mod) {
        return AxisAlignedBB.getBoundingBox(x - (0.5 + mod), y - (2 + mod), z - (0.5 + mod),
                /** Indented because CDO :P **/    (x + 0.5) + mod, (y + 2) + mod, z +(0.5 + mod));
    }
}
