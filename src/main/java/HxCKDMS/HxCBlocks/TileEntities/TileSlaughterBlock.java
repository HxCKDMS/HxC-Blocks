package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventSlaughter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileSlaughterBlock extends TileEntity{
    public int modifier;
    //Hostile, Neutral, Passive, Boss, Pets
    public int[] Targets = new int[]{0,0,0,0,0};

    EventSlaughter event = new EventSlaughter();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("Mod", modifier);
        par1.setIntArray("Targets", Targets);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        modifier = par1.getInteger("Mod");
        Targets = par1.getIntArray("Targets");
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote && !powered) event.Slaughter(new int[]{xCoord, yCoord, zCoord}, worldObj);
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