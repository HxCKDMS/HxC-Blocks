package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumItems;
import HxCKDMS.HxCCore.Utils.IOHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@SuppressWarnings("all")
public class TileVacuum extends TileEntity implements ISidedInventory {
    public int modifier;
    public int[] OtherPos = null;
    //Hostile, Neutral, Passive, Boss, Pets
    public int[] Targets = new int[]{0,0,0,0,0};

    private ItemStack[] inventory = new ItemStack[getInvSize()];
    EventVacuumItems event = new EventVacuumItems();

    protected int getInvSize() { return 50; }

    private static final int[] accessibleSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
    31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("Mod", modifier);
        if (OtherPos != null) tag.setIntArray("BoundBlockPos", OtherPos);
        tag.setIntArray("Targets", Targets);

        NBTTagList List = new NBTTagList();
        for(int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
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
        modifier = tag.getInteger("Mod");
        OtherPos = tag.getIntArray("BoundBlockPos");
        Targets = tag.getIntArray("Targets");

        NBTTagList tagList = tag.getTagList("Inventory", getInvSize());
        inventory = new ItemStack[inventory.length];
        for(int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if(slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    public void updateEntity(){
        if(worldObj != null && !worldObj.isRemote && !powered) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
        exportItem(64);
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
    }

    protected boolean exportItem(int maxItems){
        ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
        TileEntity tile = null;
        try { tile = worldObj.getTileEntity(OtherPos[0], OtherPos[1], OtherPos[2]); } catch (Exception ignore) {
            for (ForgeDirection dir : dirs) {
                TileEntity neighbor = IOHelper.getNeighbor(this, dir);
                if (neighbor instanceof TileEntityChest) {
                    tile = neighbor;
                    break;
                }
            }
        }

        for(int i = 4; i >= 0; i--) {
            ItemStack stack = inventory[i];
            if(stack != null && tile != null) {
                ItemStack exportedStack = stack.copy();
                if(exportedStack.stackSize > maxItems) exportedStack.stackSize = maxItems;
                int count = exportedStack.stackSize;
                ItemStack remainder = IOHelper.insert(tile, exportedStack, ForgeDirection.UP, false);
                int exportedItems = count - (remainder == null ? 0 : remainder.stackSize);
                stack.stackSize -= exportedItems;
                if(stack.stackSize <= 0) setInventorySlotContents(i, null);
                maxItems -= exportedItems;
                if(maxItems <= 0) return true;
            }
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        inventory[slot] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer no) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return accessibleSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return true;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return true;
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