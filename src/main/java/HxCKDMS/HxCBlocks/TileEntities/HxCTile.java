package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Events.Boom;
import HxCKDMS.HxCBlocks.Events.EventSlaughter;
import HxCKDMS.HxCBlocks.Events.EventVacuumItems;
import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import HxCKDMS.HxCCore.api.Utils.AABBUtils;
import HxCKDMS.HxCCore.api.Utils.IOHelper;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

import static HxCKDMS.HxCBlocks.lib.Reference.*;

public class HxCTile extends TileEntity implements ISidedInventory {
//    "Barrier", "PotionBrewer", "SlaughterBlock", "SpawnerAccelerator", "Vacuum", "XPAbsorber","GreyGoo", "LeBomb", "SoulExtractor"
    public String Mob = "";
    public int XP = 0;
    public boolean started, initialized;

    @Override
    public int getBlockMetadata() {
        return super.getBlockMetadata();
    }

    public List<String> EFFECTS = new ArrayList<>();

    private static final int[] accessibleSlots = {0};
    protected int[] invSizes = new int[]{1, 3, 2, 0, 16, 2, 0, 0, 0};
    public ItemStack[] inventory;

    public void poke() {
        System.out.println("Poked!");
        started = true;
    }

    public void initialize() {
        inventory = new ItemStack[invSizes[getBlockMetadata()]];
        initialized = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (initialized) {
            XP = tag.getInteger("XP");

            NBTTagList tagList = tag.getTagList("Inventory", invSizes[getBlockMetadata()]);
            inventory = new ItemStack[inventory.length];
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                byte slot = tagCompound.getByte("Slot");
                if (slot >= 0 && slot <= inventory.length) {
                    inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (initialized) {
            if (XP != 0) tag.setInteger("XP", XP);

            NBTTagList List = new NBTTagList();
            for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
                if (inventory[currentIndex] != null) {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    tagCompound.setByte("Slot", (byte) currentIndex);
                    inventory[currentIndex].writeToNBT(tagCompound);
                    List.appendTag(tagCompound);
                    tag.setTag("Inventory", List);
                }
            }
        }
    }

    public void updateEntity() {
        if (worldObj != null && !worldObj.isRemote && initialized) {
            switch (getBlockMetadata()) {
                case 0 :
                    if (!powered) barrier(worldObj, xCoord, yCoord, zCoord, inventory[0]);
                    break;
                case 1 :
                    if (!powered) brewPot();
                    break;
                case 2 :
                    if(!powered && inventory[0] != null) slaughter(worldObj, xCoord, yCoord, zCoord);
                    break;
                case 3 :
                    if (powered) {
                        accelerate(worldObj, xCoord, yCoord, zCoord, Mob);
                        Mob = "";
                    }
                    break;
                case 4 :
                    if(!powered) vacuum(worldObj, xCoord, yCoord, zCoord);
                    break;
                case 5 :
                    if(!powered) vacuumXP(worldObj, xCoord, yCoord, zCoord);
                    break;
                case 6 :
                    if (powered) goo(worldObj, xCoord, yCoord, zCoord);
                    break;
                case 7 :
                    if (powered) leBomb(worldObj, xCoord, yCoord, zCoord);
                    break;
                default:
                    LogHelper.warn(BLOCKS[getBlockMetadata()] + " doesn't have a predefined action for updating!", MOD_NAME);
                    break;
            }
            boolean nowPowered = isPowered();
            if (powered != nowPowered) {
                powered = nowPowered;
            }
        }
        initialize();
    }

    public boolean exportItem(int maxItems){
        ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
        TileEntity tile = null;
        try {
            int x = inventory[11].getTagCompound().getInteger("x");
            int y = inventory[11].getTagCompound().getInteger("y");
            int z = inventory[11].getTagCompound().getInteger("z");
            tile = worldObj.getTileEntity(x, y, z);
        } catch (Exception ignore) {
            for (ForgeDirection dir : dirs) {
                TileEntity neighbor = IOHelper.getNeighbor(this, dir);
                if (neighbor instanceof TileEntityChest) {
                    tile = neighbor;
                    break;
                }
            }
        }

        for (int i = 4; i >= 0; i--) {
            ItemStack stack = inventory[i];
            if (stack != null && tile != null) {
                ItemStack exportedStack = stack.copy();
                if (exportedStack.stackSize > maxItems) exportedStack.stackSize = maxItems;
                int count = exportedStack.stackSize;
                ItemStack remainder = IOHelper.insert(tile, exportedStack, ForgeDirection.UP, false);
                int exportedItems = count - (remainder == null ? 0 : remainder.stackSize);
                stack.stackSize -= exportedItems;
                if (stack.stackSize <= 0) setInventorySlotContents(i, null);
                maxItems -= exportedItems;
                if (maxItems <= 0) return true;
            }
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return invSizes[getBlockMetadata()];
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
    public boolean isCustomInventoryName() {
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
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(int p_94128_1_) {
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

    @SuppressWarnings("unchecked")
    private static void barrier(World world, int x, int y, int z, ItemStack stack) {
        int height;
        if (stack != null) height = stack.stackSize;
        else height = 0;
        List<Entity> list  = world.getEntitiesWithinAABB(Entity.class, AABBUtils.getAreaBoundingBox(x, y, z, 0).expand(0, height+2, 0));
        for (Entity entity : list) {
            entity.motionX = -entity.motionX;
            entity.motionZ = -entity.motionZ;
        }
    }

    private static void slaughter(World world, int x, int y, int z) {
        EventSlaughter event = new EventSlaughter();
        event.Slaughter(x, y, z, world);
    }

    private static void vacuum(World world, int x, int y, int z) {
        EventVacuumItems event = new EventVacuumItems();
        event.vacuum(x, y, z, world);
    }

    private static void vacuumXP(World world, int x, int y, int z) {
        EventVacuumXP event = new EventVacuumXP();
        event.vacuum(x, y, z, world);
    }

    private static void accelerate(World world, int x, int y, int z, String Mob) {
        TileEntity tile  = world.getTileEntity(x, y + 1, z);
        if (tile instanceof TileEntityMobSpawner) {
            TileEntityMobSpawner spawner = (TileEntityMobSpawner)tile;
            spawner.func_145881_a().spawnDelay = 0;
            if (!Mob.isEmpty()){spawner.func_145881_a().setEntityName(Mob);}
            world.markBlockForUpdate(x, y+1, z);
        }
    }

    static int delay = Configurations.SpecialDelay;
    public void goo(World world, int x, int y, int z) {
        if (delay == 0 && !world.isRemote) {
            if (canReplace(world, x - 1, y, z))
                world.setTileEntity(x - 1, y, z, this);
            if (canReplace(world, x + 1, y, z))
                world.setTileEntity(x + 1, y, z, this);
            if (canReplace(world, x, y, z - 1))
                world.setTileEntity(x, y, z - 1, this);
            if (canReplace(world, x, y, z + 1))
                world.setTileEntity(x, y, z + 1, this);
            if (canReplace(world, x, y + 1, z))
                world.setTileEntity(x, y + 1, z, this);
            if (canReplace(world, x, y - 1, z))
                world.setTileEntity(x, y - 1, z, this);
            invalidate();
        } else delay--;
    }

    public static void leBomb(World world, int x, int y, int z) {
        if (delay == 0) {
            world.setBlockToAir(x, y, z);
            new Boom(world, x, y, z, world.rand.nextInt(Configurations.MaxLeBombRange));
            world.playSoundToNearExcept(null, "mob.wither.spawn", 1, 0.5f);
        } else delay--;
    }

    public void brewPot() {
        if (started && delay == 0) {
            LogHelper.info("Brewing potion with statuses : ", EFFECTS.toString());
        } else if (started) delay--;

        if (inventory[0] != null) {
            System.out.println(inventory[0].getUnlocalizedName());
            if (INGREDIENTS.contains(inventory[0].getUnlocalizedName())) {
                EFFECTS.add(inventory[0].getUnlocalizedName());
                inventory[0] = null;
            }
        }
    }

    static boolean canReplace(World world, int x, int y, int z) {
        return (world.getBlock(x, y, z).getBlockHardness(world, x, y, z) > 0 && !(world.getBlock(x, y, z) instanceof BlockContainer) && !(world.getBlock(x, y, z) instanceof BlockAir));
    }
}
