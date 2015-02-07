package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemSoulBinder extends Item{
	public NBTTagCompound data;

    public ItemSoulBinder(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SoulBinder");
		setMaxStackSize(1);
	}

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        data = getNBT(stack);
        data.setString("Player", player.getName());
        stack.setTagCompound(data);
    }

    static NBTTagCompound getNBT(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean boo) {
        super.addInformation(stack, player, tooltips, boo);
        try {
            String p = stack.getTagCompound().getString("Player");
            tooltips.add("\u00A7bThis bound to " + p + "'s soul.");
        } catch (Exception ignored){
            tooltips.add("\u00A74ERROR! Please put in crafting grid.");
        }
    }
}
