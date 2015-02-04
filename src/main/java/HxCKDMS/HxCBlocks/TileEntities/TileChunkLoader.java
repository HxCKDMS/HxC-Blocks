package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCCore.Utils.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;

import java.io.IOException;

public class TileChunkLoader extends TileEntity implements IChunkLoader{
//    public int modifier;
//    public boolean AllowUpdate;
    public String BoundPlayer;

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
//        par1.setBoolean("Enabled", AllowUpdate);
//        par1.setInteger("Mod", modifier);
        if (BoundPlayer != null) par1.setString("BoundPlayer", BoundPlayer);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
//        this.modifier = par1.getInteger("Mod");
//        this.AllowUpdate = par1.getBoolean("Enabled");
        this.BoundPlayer = par1.getString("BoundPlayer");
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote/* && AllowUpdate*/)
            try {
                loadChunk(worldObj, xCoord, zCoord);
            } catch (IOException ignore) {
                LogHelper.error("Could not Chunkload chunk at coords x: " + xCoord + " z:" + zCoord, References.MOD_ID);
            }
    }

    @Override
    public Chunk loadChunk(World world, int x, int z) throws IOException {
        return world.getChunkFromBlockCoords(x, z);
    }

    @Override
    public void saveChunk(World world, Chunk chunk) throws MinecraftException, IOException {

    }

    @Override
    public void saveExtraChunkData(World p_75819_1_, Chunk p_75819_2_) {

    }

    @Override
    public void chunkTick() {

    }

    @Override
    public void saveExtraData() {

    }
}