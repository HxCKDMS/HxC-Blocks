package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventVacuumXP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TileXPAbsorber extends TileEntity implements IUpdatePlayerListBox {
    public int XP;
    public int modifier;
    public boolean AllowUpdate;
    public String BoundPlayer;
    EventVacuumXP event = new EventVacuumXP();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setBoolean("Enabled", AllowUpdate);
        par1.setInteger("Mod", modifier);
        par1.setInteger("XP", XP);
        if (BoundPlayer != null) par1.setString("BoundPlayer", BoundPlayer);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
        this.XP = par1.getInteger("XP");
        this.AllowUpdate = par1.getBoolean("Enabled");
        this.BoundPlayer = par1.getString("BoundPlayer");
    }

    public void update() {
        if(worldObj != null && !worldObj.isRemote && AllowUpdate && !powered) event.vacuum(pos, worldObj);
        boolean nowPowered = isPowered();
        if (powered != nowPowered) {
            powered = nowPowered;
        }
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