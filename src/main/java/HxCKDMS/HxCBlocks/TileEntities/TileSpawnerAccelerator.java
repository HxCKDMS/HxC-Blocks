package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class TileSpawnerAccelerator extends TileEntity {
    public String Mob;
    public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote) {
            TileEntity tile  = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            if (tile instanceof TileEntityMobSpawner && powered) {
                TileEntityMobSpawner spawner = (TileEntityMobSpawner)tile;
                spawner.func_145881_a().spawnDelay = 0;
                if (!Mob.isEmpty()){rename(spawner);Mob = "";}
                worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
            }

            boolean nowPowered = isPowered();
            if (powered != nowPowered) {
                powered = nowPowered;
            }
        }
    }
    public void rename(TileEntityMobSpawner spawner) {
        spawner.func_145881_a().setEntityName(Mob);
    }

    public boolean isPowered() {
        return isPoweringTo(worldObj, xCoord, yCoord + 1, zCoord, 0) ||
                isPoweringTo(worldObj, xCoord, yCoord - 1, zCoord, 1) ||
                isPoweringTo(worldObj, xCoord, yCoord, zCoord + 1, 2) ||
                isPoweringTo(worldObj, xCoord, yCoord, zCoord - 1, 3) ||
                isPoweringTo(worldObj, xCoord + 1, yCoord, zCoord, 4) ||
                isPoweringTo(worldObj, xCoord - 1, yCoord, zCoord, 5);
    }
    protected boolean powered = false;
    public static boolean isPoweringTo(World world, int x, int y, int z, int side) {
        return world.getBlock(x, y, z).isProvidingWeakPower(world, x, y, z, side) > 0;
    }
}