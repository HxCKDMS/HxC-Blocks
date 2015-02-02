package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventSlaughter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileSlaughterBlock extends TileEntity{
    public int modifier;
    public boolean AllowUpdate;
    public String BoundPlayer;

    EventSlaughter event = new EventSlaughter();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("Mod", modifier);
        if (BoundPlayer != null) par1.setString("BoundPlayer", BoundPlayer);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
        this.AllowUpdate = par1.getBoolean("Enabled");
        this.BoundPlayer = par1.getString("BoundPlayer");
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote && AllowUpdate) event.Slaughter(new int[]{xCoord, yCoord, zCoord}, worldObj);
    }
}