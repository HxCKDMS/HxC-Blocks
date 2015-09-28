package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCBlocks.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemBinder extends Item{
    public NBTTagCompound data;

    public ItemBinder(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("Binder");
        setTextureName(Reference.MOD_ID + ":Binder");
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float bx, float by, float bz) {
        if (world.getTileEntity(x, y, z) instanceof IInventory && !(world.getTileEntity(x, y, z) instanceof HxCTile)) {
            NBTTagCompound dat = new NBTTagCompound();
            dat.setInteger("x", x);
            dat.setInteger("y", y);
            dat.setInteger("z", z);
            stack.setTagCompound(dat);
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean boo) {
        super.addInformation(stack, player, tooltips, boo);
        NBTTagCompound compound = stack.getTagCompound();
        try {
            tooltips.add("X:" + compound.getInteger("x"));
            tooltips.add("Y:" + compound.getInteger("y"));
            tooltips.add("Z:" + compound.getInteger("z"));
        } catch (Exception ignored){
            tooltips.add("\u00A73Item is unbound.");
        }
    }
}
