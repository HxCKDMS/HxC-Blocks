package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileVacuum extends TileEntity{
    public int XP;
    public int modifier;
    public boolean AllowUpdate;
    public String BoundPlayer;

    EventVacuumItems event = new EventVacuumItems();

    private ItemStack[] slots = new ItemStack[5];

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("Mod", modifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
        this.AllowUpdate = par1.getBoolean("Enabled");
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote && AllowUpdate) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
    }

    public int getSizeInventory()
    {
        return slots.length;
    }

    public ItemStack getStackInSlot(int slot)
    {
        return slots[slot];
    }

    public ItemStack decrStackSize(int slot, int remain)
    {
        if (slots[slot] != null)
        {
            ItemStack itemstack;

            if (slots[slot].stackSize <= remain)
            {
                itemstack = slots[slot];
                slots[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = slots[slot].splitStack(remain);

                if (slots[slot].stackSize == 0)
                {
                    slots[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public int getInventoryStackLimit()
    {
        return 128;
    }
    
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) { readFromNBT(packet.func_148857_g()); }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }
}