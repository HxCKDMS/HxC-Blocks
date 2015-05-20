package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileXPAbsorber extends TileEntity{
    public ItemStack Item;
    public int XP;
    public int Range;
    EventVacuumXP event = new EventVacuumXP();

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (XP != 0) tag.setInteger("XP", XP);
        if (Range != 0) tag.setInteger("Range", Range);
        NBTTagList List = new NBTTagList();
        if(Item != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setByte("Item", (byte)0);
            Item.writeToNBT(tagCompound);
            List.appendTag(tagCompound);
            tag.setTag("Item", List);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        XP = tag.getInteger("XP");
        Range = tag.getInteger("Range");
        NBTTagList tagList = tag.getTagList("Item", 0);
        NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
        Item = ItemStack.loadItemStackFromNBT(tagCompound);
    }

    public void updateEntity() {
        if(worldObj != null && !worldObj.isRemote && !powered && Item != null && !Item.getTagCompound().getString("Player").isEmpty()) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
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