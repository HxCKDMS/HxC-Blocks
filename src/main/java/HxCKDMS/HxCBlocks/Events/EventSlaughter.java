package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import HxCKDMS.HxCCore.Entity.HxCFakePlayer;
import HxCKDMS.HxCCore.HxCCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class EventSlaughter {
    public void Slaughter(BlockPos pos, World world){
        TileEntity tile = world.getTileEntity(pos);
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)tile;
        int modifier = HxCTile.modifier;
        int nx; int ny; int nz;
        if (pos.getX() >= 0) nx = pos.getX() + 1;
        else nx = pos.getX() - 1;
        if (pos.getY() >= 0) ny = pos.getY() + 1;
        else ny = pos.getY() - 1;
        if (pos.getZ() >= 0) nz = pos.getZ() + 1;
        else nz = pos.getZ() - 1;
        List list  = world.getEntitiesWithinAABB(Entity.class, getAreaBoundingBox(pos.getX(), pos.getY(), pos.getZ(), nx, ny, nz, modifier));
        for (EntityLiving entity : (List<EntityLiving>) list) {
            if (!entity.isDead && entity.deathTime == 0) {
                EntityPlayer pla = new HxCFakePlayer(HxCCore.server.worldServerForDimension(world.provider.getDimensionId()));
                entity.attackEntityFrom(DamageSource.causePlayerDamage(pla), 300f);
            }
        }
    }

    protected AxisAlignedBB getAreaBoundingBox(int mx, int my, int mz, int Mx, int My, int Mz, int mod) {
        if (mx > 0) mx += mod; else mx -= mod;
        if (my > 0) my += mod; else my -= mod;
        if (mz > 0) mz += mod; else mz -= mod;
        if (Mx > 0) Mx += mod; else Mx -= mod;
        if (My > 0) My += mod; else My -= mod;
        if (Mz > 0) Mz += mod; else Mz -= mod;
        return AxisAlignedBB.fromBounds(mx, my, mz, Mx, My, Mz);
    }
}
