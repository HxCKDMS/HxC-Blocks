package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileGreyGoo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemGooStopper extends Item{


    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        int px = Math.round((float)player.posX); int x; int mx;
        int pz = Math.round((float)player.posZ); int z; int mz;
        if (px > 0) {
            x = px - 500; mx = px + 500;
        } else {
            x = px + 500;  mx = px - 500;
        }
        if (pz > 0) {
            z = pz - 500; mz = pz + 500;
        } else {
            z = pz + 500; mz = pz - 500;
        }
        for (int i = x; i < 1000; i++){
            for (int j = 0; j < 256; j++){
                for (int k = z; k < 1000; k++){
                    TileEntity tile = world.getTileEntity(i,j,k);
                    if (tile instanceof TileGreyGoo){
                        TileGreyGoo goo = (TileGreyGoo)tile;
                        goo.goo = true;
                    }
                }
            }
        }
        player.inventory.decrStackSize(player.inventory.currentItem, 1);
        return super.onItemRightClick(stack, world, player);
    }

    public ItemGooStopper(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("GooStopper");
        setTextureName(References.MOD_ID + ":GooStopper");
        setMaxStackSize(1);
    }
}
