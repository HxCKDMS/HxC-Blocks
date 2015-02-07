package HxCKDMS.HxCBlocks.TileEntities;

import HxCKDMS.HxCBlocks.Events.EventSlaughter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TileSlaughterBlock extends TileEntity implements IUpdatePlayerListBox {
    public int modifier;

    EventSlaughter event = new EventSlaughter();

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("Mod", modifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.modifier = par1.getInteger("Mod");
    }

    public void update(){
        if(worldObj != null && !worldObj.isRemote && !powered) event.Slaughter(pos, worldObj);
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