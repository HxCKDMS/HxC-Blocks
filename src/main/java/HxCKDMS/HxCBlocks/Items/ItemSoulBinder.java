package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
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
		setTextureName(References.MOD_ID + ":SoulBinder");
		setMaxStackSize(1);
	}

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        data = getNBT(stack);
        data.setString("Player", player.getDisplayName());
        stack.setTagCompound(data);
    }

    static NBTTagCompound getNBT(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
        }
        return stack.stackTagCompound;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean boo) {
        super.addInformation(stack, player, tooltips, boo);
        try {
            String p = stack.getTagCompound().getString("Player");
            tooltips.add("\u00A7bThis bound to " + p + "'s soul.");
        } catch (Exception e){
            tooltips.add("\u00A74ERROR! Please put in crafting grid.");
        }
    }
}
