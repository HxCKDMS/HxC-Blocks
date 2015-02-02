package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

@SuppressWarnings("unchecked")
public class TileXPAbsorber extends TileEntity{
    public int x;
    public int y;
    public int z;
    public int XP;
    public int modifier;
    public boolean AllowUpdate;
    public boolean Enabled;
    public String BoundPlayer;
    public String player;
    private int[] coords = new int[3];
    EventVacuumXP event = new EventVacuumXP();
    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("x", x);
        par1.setInteger("y", y);
        par1.setInteger("z", z);
        par1.setInteger("Mod", modifier);
        par1.setInteger("XP", XP);
        if (!BoundPlayer.isEmpty()) par1.setString("BoundPlayer", BoundPlayer);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.x = par1.getInteger("x");
        this.y = par1.getInteger("y");
        this.z = par1.getInteger("z");
        this.modifier = par1.getInteger("Mod");
        this.XP = par1.getInteger("XP");
        this.Enabled = par1.getBoolean("Enabled");
        this.player = par1.getString("BoundPlayer");
    }

    public void updateEntity(){
        worldObj.markBlockForUpdate(x, y, z);
        coords[0] = x; coords[1] = y; coords[2] = z;
        if(worldObj != null && !worldObj.isRemote && Enabled){
            event.vacuum(coords, worldObj);
        }
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) { readFromNBT(packet.func_148857_g()); }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }
}