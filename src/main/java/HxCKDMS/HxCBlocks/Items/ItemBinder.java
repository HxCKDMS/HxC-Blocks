package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemBinder extends Item{
    public NBTTagCompound data;

    public ItemBinder(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("Binder");
        setTextureName(References.MOD_ID + ":Binder");
        setMaxStackSize(1);
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
