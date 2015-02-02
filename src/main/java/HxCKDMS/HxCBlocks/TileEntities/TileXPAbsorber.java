package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

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
        if(worldObj != null && !worldObj.isRemote && AllowUpdate) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
    }
}