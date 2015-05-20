package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemSoulBinder extends Item{
	public NBTTagCompound data;

    public ItemSoulBinder(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SoulBinder");
		setTextureName(References.MOD_ID + ":SoulBinder");
		setMaxStackSize(1);
	}

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean boo) {
        super.addInformation(stack, player, tooltips, boo);
        try {
            String p = stack.getTagCompound().getString("Player");
            tooltips.add("\u00A7bThis bound to " + p + "'s soul.");
        } catch (Exception ignored){}
    }
}
