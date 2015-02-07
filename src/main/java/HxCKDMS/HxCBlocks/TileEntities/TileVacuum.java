package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumItems;
import HxCKDMS.HxCCore.Utils.IOHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

@SuppressWarnings("all")
public class TileVacuum extends TileEntity implements ISidedInventory, IUpdatePlayerListBox {
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

    public void update(){
        if(worldObj != null && !worldObj.isRemote && !powered) event.vacuum(pos, worldObj);
        exportItem(64);
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
    }

    protected boolean exportItem(int maxItems){
        EnumFacing[] dirs = EnumFacing.values();
        TileEntity tile = null;
        try { tile = worldObj.getTileEntity(new BlockPos(OtherPos[0], OtherPos[1], OtherPos[2])); } catch (Exception ignore) {
            for (EnumFacing dir : dirs) {
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
                ItemStack remainder = IOHelper.insert(tile, exportedStack, EnumFacing.UP, false);
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
    public int getInventoryStackLimit(){
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer no) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer no) {

    }

    @Override
    public void closeInventory(EntityPlayer no) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return accessibleSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
        return true;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
        return true;
    }

    public boolean isPowered() {
        return isPoweringTo(worldObj, pos.getX(), pos.getY() + 1, pos.getZ(), EnumFacing.UP) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY() - 1, pos.getZ(), EnumFacing.DOWN) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() + 1, EnumFacing.SOUTH) ||
                isPoweringTo(worldObj, pos.getX(), pos.getY(), pos.getZ() - 1, EnumFacing.NORTH) ||
                isPoweringTo(worldObj, pos.getX() + 1, pos.getY(), pos.getZ(), EnumFacing.EAST) ||
                isPoweringTo(worldObj, pos.getX() - 1, pos.getY(), pos.getZ(), EnumFacing.WEST);
    }

    protected boolean powered = false;
    public static boolean isPoweringTo(World world, int x, int y, int z, EnumFacing side) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock().isProvidingWeakPower(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)), side) > 0;
    }
}