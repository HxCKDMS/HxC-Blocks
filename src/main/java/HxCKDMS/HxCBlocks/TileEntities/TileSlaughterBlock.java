package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventSlaughter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileSlaughterBlock extends TileEntity{
    public int modifier;

    EventSlaughter event = new EventSlaughter();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("Mod", modifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote) event.Slaughter(new int[]{xCoord, yCoord, zCoord}, worldObj);
    }
}