package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ItemHxCWrench extends Item {
    public static ArrayList<Block> HxCWrenchBL = new ArrayList<>();

    public ItemHxCWrench() {
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("HxCWrench");
        setTextureName(Reference.MOD_ID + ":HxCWrench");
        setMaxStackSize(1);
        setMaxDurability(0);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float bx, float by, float bz) {
        if(!world.isRemote){
            if(world.canMineBlock(player, x, y, z)) {
                Block BlockToGrab = world.getBlock(x, y, z);
                TileEntity TileEntityToGrab = world.getTileEntity(x, y, z);

                if(BlockToGrab.getBlockHardness(world, x, y, z) == -1.0F) return false;
                if(HxCWrenchBL.contains(BlockToGrab)) return false;

                String BlockOwner = GameRegistry.findUniqueIdentifierFor(BlockToGrab).modId;
                String BlockUN = GameRegistry.findUniqueIdentifierFor(BlockToGrab).name;

                int BlockMeta = world.getBlockMetadata(x, y, z);
//
//                ItemStack DroppedItem = new ItemStack(ModRegistry.HxCWrenchPlaceHolder);

                ItemStack DroppedItem = new ItemStack(Items.baked_potato);

                DroppedItem.stackTagCompound = new NBTTagCompound();
                DroppedItem.stackTagCompound.setString("BlockOwner", BlockOwner);
                DroppedItem.stackTagCompound.setString("BlockUN", BlockUN);
                DroppedItem.stackTagCompound.setInteger("BlockMeta", BlockMeta);

                if(TileEntityToGrab != null){
                    NBTTagCompound nbtToSave = new NBTTagCompound();

                    TileEntityToGrab.writeToNBT(nbtToSave);

                    DroppedItem.stackTagCompound.setTag("BlockNBT", nbtToSave);

                    world.removeTileEntity(x, y, z);
                }

                world.setBlock(x, y, z, Blocks.air, 0, 3);

                world.spawnEntityInWorld(new EntityItem(world, x, y, z, DroppedItem));
                return true;
            }
        }else{
            return true;
        }
        return false;
    }
}
