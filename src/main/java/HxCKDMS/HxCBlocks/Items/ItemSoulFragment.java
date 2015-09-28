package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.lib.Reference;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemSoulFragment extends Item{
	public NBTTagCompound data;

    public ItemSoulFragment(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SoulFragment");
		setTextureName(Reference.MOD_ID + ":SoulFragment");
		setMaxStackSize(4);
	}

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean boo) {
        super.addInformation(stack, player, tooltips, boo);
        try {
            String p = stack.getTagCompound().getString("Player");
            tooltips.add("\u00A7bThis is a fragment of " + p + "'s soul.");
        } catch (Exception ignored){}
    }
}
