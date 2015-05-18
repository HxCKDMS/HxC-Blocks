package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileXPAbsorber extends TileEntity{
    public ItemStack[] inventory = new ItemStack[getInvSize()];
    public int XP;
    public int Range;
    EventVacuumXP event = new EventVacuumXP();

    protected int getInvSize() { return 1; }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("XP", XP);
        tag.setInteger("Range", Range);
        NBTTagList List = new NBTTagList();
        for(int currentIndex = 0; currentIndex < getInvSize(); ++currentIndex) {
            if(inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte)currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                List.appendTag(tagCompound);
            }
        }
        tag.setTag("Inventory", List);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        XP = tag.getInteger("XP");
        Range = tag.getInteger("Range");
        NBTTagList tagList = tag.getTagList("Inventory", getInvSize());
        inventory = new ItemStack[getInvSize()];
        for(int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if(slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    public void updateEntity() {
        if(worldObj != null && !worldObj.isRemote && !powered && inventory[0] != null && inventory[0].getTagCompound().getString("BoundPlayer") != null) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
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