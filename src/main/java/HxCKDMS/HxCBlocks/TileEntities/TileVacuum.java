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
import net.minecraftforge.common.util.ForgeDirection;

@SuppressWarnings("all")
public class TileVacuum extends TileEntity implements ISidedInventory {
    public int modifier;
    public boolean AllowUpdate;

    private ItemStack[] inventory = new ItemStack[getInvSize()];
    EventVacuumItems event = new EventVacuumItems();

    protected int getInvSize(){
        return 5;
    }

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("Mod", modifier);

        NBTTagList tagList = new NBTTagList();
        for(int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if(inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte)currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        par1.setTag("Inventory", tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
        this.AllowUpdate = par1.getBoolean("Enabled");
        NBTTagList tagList = par1.getTagList("Inventory", 5);
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
        if(worldObj != null && !worldObj.isRemote && AllowUpdate) event.vacuum(new int[]{xCoord, yCoord, zCoord}, worldObj);
    }

    protected boolean exportItem(int maxItems){
        ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
        TileEntity tile = null;
        for (ForgeDirection dir : dirs) {
            TileEntity neighbor = IOHelper.getNeighbor(this, dir);
            if (neighbor instanceof TileEntityChest) {
                tile = neighbor;
                break;
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
                if(stack.stackSize <= 0) {
                    setInventorySlotContents(i, null);
                }
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
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack){
        inventory[slot] = itemStack;
        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
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
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    private static final int[] accessibleSlots = {0, 1, 2, 3, 4};

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return accessibleSlots;
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }
}