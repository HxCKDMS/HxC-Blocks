package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Events.Boom;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class TileLeBomb extends TileEntity {
    int delay = Configurations.LeBombDelay;
    Random rand = new Random();
    int range = rand.nextInt(Configurations.MaxLeBombRange);
    Boom boom;
    @Override
    public void updateEntity() {
        if (isPowered()) {
            if (delay == 0) {
                if (!worldObj.isRemote) {
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                    boom = new Boom(worldObj, xCoord, yCoord, zCoord, range);
                }
                if (worldObj.isRemote) worldObj.playSound(xCoord, yCoord, zCoord, "mob.wither.spawn", 1, 0.5f, true);
            } else delay--;
        }
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
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
