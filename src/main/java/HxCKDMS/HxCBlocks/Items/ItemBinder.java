package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.entity.player.EntityPlayer;
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
        setTextureName(References.MOD_ID + ":Binder");
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    //TODO: make item automatically function when pulled out of the creative menu or spawned in.
    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        data = getNBT(stack);
        int[] pb = new int[]{0,0,0};
        data.setInteger("Mode", 0);
        data.setIntArray("PB", pb);
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
            int[] coords = stack.getTagCompound().getIntArray("PB");
            int mode = stack.getTagCompound().getInteger("Mode");
            tooltips.add("X:" + coords[0]);
            tooltips.add("Y:" + coords[1]);
            tooltips.add("Z:" + coords[2]);
            if (mode == 1) tooltips.add("Mode: Write");
            if (mode == 0) tooltips.add("Mode: Read");
        } catch (Exception ignored){
            tooltips.add("\u00A74ERROR! Please put in crafting grid.");
        }
    }
}
