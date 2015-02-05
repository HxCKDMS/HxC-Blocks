package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileXPAbsorber extends TileEntity{
    public int XP;
    public int modifier;
    public boolean AllowUpdate;
    public String BoundPlayer;
    EventVacuumXP event = new EventVacuumXP();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("Mod", modifier);
        par1.setInteger("XP", XP);
        if (BoundPlayer != null) par1.setString("BoundPlayer", BoundPlayer);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
        this.XP = par1.getInteger("XP");
        this.AllowUpdate = par1.getBoolean("Enabled");
        this.BoundPlayer = par1.getString("BoundPlayer");
    }

    public void updateEntity() {
        if(worldObj != null && !worldObj.isRemote && AllowUpdate && !powered) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
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