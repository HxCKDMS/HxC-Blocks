package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileGreyGoo extends TileEntity {
    boolean hasSpread = false;
    int delay = 30;
    @Override
    public boolean canUpdate() {
        return !hasSpread;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setBoolean("Spread", hasSpread);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        hasSpread = tag.getBoolean("Spread");
    }

    @Override
    public void updateEntity() {
        if (delay == 0) {
            if (canReplace(xCoord - 1, yCoord, zCoord))
                worldObj.setBlock(xCoord - 1, yCoord, zCoord, ModRegistry.GreyGoo);
            if (canReplace(xCoord + 1, yCoord, zCoord))
                worldObj.setBlock(xCoord + 1, yCoord, zCoord, ModRegistry.GreyGoo);
            if (canReplace(xCoord, yCoord, zCoord - 1))
                worldObj.setBlock(xCoord, yCoord, zCoord - 1, ModRegistry.GreyGoo);
            if (canReplace(xCoord, yCoord, zCoord + 1))
                worldObj.setBlock(xCoord, yCoord, zCoord + 1, ModRegistry.GreyGoo);
            if (canReplace(xCoord, yCoord + 1, zCoord))
                worldObj.setBlock(xCoord, yCoord + 1, zCoord, ModRegistry.GreyGoo);
            if (canReplace(xCoord, yCoord - 1, zCoord))
                worldObj.setBlock(xCoord, yCoord - 1, zCoord, ModRegistry.GreyGoo);
            hasSpread = true;
        } else delay--;
    }
    boolean canReplace(int x, int y, int z) {
        return (worldObj.getBlock(x, y, z).getBlockHardness(worldObj, x, y, z) > 0 && !(worldObj.getBlock(x, y, z) instanceof BlockContainer) && !(worldObj.getBlock(x, y, z) instanceof BlockAir));
    }
}
