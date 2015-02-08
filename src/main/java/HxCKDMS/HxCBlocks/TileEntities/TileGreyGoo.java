package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

@SuppressWarnings("unchecked")
public class TileGreyGoo extends TileEntity{
    public boolean goo;
    int timer = -1;
    public void updateEntity() {
        timer++;
        if (worldObj != null && !worldObj.isRemote && !goo && timer > 60) {
            timer = -1;
            if (worldObj.getBlock(xCoord-1, yCoord, zCoord) != Blocks.bedrock && worldObj.getTileEntity(xCoord-1, yCoord, zCoord) != this)worldObj.setBlock(xCoord-1, yCoord, zCoord, ModRegistry.GreyGoo);
            if (worldObj.getBlock(xCoord+1, yCoord, zCoord) != Blocks.bedrock && worldObj.getTileEntity(xCoord+1, yCoord, zCoord) != this)worldObj.setBlock(xCoord+1, yCoord, zCoord, ModRegistry.GreyGoo);
            if (worldObj.getBlock(xCoord, yCoord, zCoord-1) != Blocks.bedrock && worldObj.getTileEntity(xCoord, yCoord, zCoord-1) != this)worldObj.setBlock(xCoord, yCoord, zCoord-1, ModRegistry.GreyGoo);
            if (worldObj.getBlock(xCoord, yCoord, zCoord+1) != Blocks.bedrock && worldObj.getTileEntity(xCoord, yCoord, zCoord+1) != this)worldObj.setBlock(xCoord, yCoord, zCoord+1, ModRegistry.GreyGoo);
            if (worldObj.getBlock(xCoord, yCoord+1, zCoord) != Blocks.bedrock && worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != this)worldObj.setBlock(xCoord, yCoord+1, zCoord, ModRegistry.GreyGoo);
            if (worldObj.getBlock(xCoord, yCoord-1, zCoord) != Blocks.bedrock && worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != this)worldObj.setBlock(xCoord, yCoord-1, zCoord, ModRegistry.GreyGoo);
        }
    }
}