package HxCKDMS.HxCBlocks.TileEntities;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TileSpawnerAccelerator extends TileEntity implements IUpdatePlayerListBox {
    public String Mob = null;
    public void update() {
        if (worldObj != null && !worldObj.isRemote) {
            TileEntity tile  = worldObj.getTileEntity(pos.add(0, 1, 0));
            if (tile instanceof TileEntityMobSpawner && powered) {
                TileEntityMobSpawner spawner = (TileEntityMobSpawner)tile;
                spawner.getSpawnerBaseLogic().spawnDelay = 0;
                if (Mob != null)rename(spawner);
                worldObj.markBlockForUpdate(pos);
            }
            boolean nowPowered = isPowered();
            if (powered != nowPowered) {
                powered = nowPowered;
            }
        }
    }
    public void rename(TileEntityMobSpawner spawner) {
        spawner.getSpawnerBaseLogic().setEntityName(Mob);
    }

    public boolean isPowered() {
        return isPoweringTo(worldObj, pos.getX(), pos.getY() + 1, pos.getZ(), EnumFacing.UP) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY() - 1, pos.getZ(), EnumFacing.DOWN) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() + 1, EnumFacing.SOUTH) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() - 1, EnumFacing.NORTH) ||
                isPoweringTo(worldObj, pos.getX() + 1, pos.getY(), pos.getZ(), EnumFacing.EAST) ||
                isPoweringTo(worldObj, pos.getX() - 1, pos.getY(), pos.getZ(), EnumFacing.WEST);
    }

    protected boolean powered = false;
    public static boolean isPoweringTo(World world, int x, int y, int z, EnumFacing side) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock().isProvidingWeakPower(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)), side) > 0;
    }
}