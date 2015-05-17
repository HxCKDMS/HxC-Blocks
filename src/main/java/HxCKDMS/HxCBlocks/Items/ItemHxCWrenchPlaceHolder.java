package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ItemHxCWrenchPlaceHolder extends Item {
    public static final String suffix = " - Fake Block";

    public ItemHxCWrenchPlaceHolder() {
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("HxCWrenchPlaceHolder");
        setMaxStackSize(1);
        setMaxDamage(0);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String name;
        if(stack.hasTagCompound()) {
            name = GameRegistry.findBlock(stack.stackTagCompound.getString("BlockOwner"), stack.stackTagCompound.getString("BlockUN")).getLocalizedName().replaceAll("tile.", "").replaceAll(".name", "");
        }else
            name = "HxC Wrench";

        return StringUtils.capitalize(name + suffix);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float deltaX, float deltaY, float deltaZ) {
        if(!world.isRemote){
            try{
                if(stack.hasTagCompound()){
                    Block BlockToSpawn = GameRegistry.findBlock(stack.stackTagCompound.getString("BlockOwner"), stack.stackTagCompound.getString("BlockUN"));

                    int[] placeCoords = getCoordsForPlacement(x, y, z, side);

                    if(placeCoords == null) return false;

                    x = placeCoords[0];
                    y = placeCoords[1];
                    z = placeCoords[2];

                    if(world.canMineBlock(player, x, y, z) && y >= 0) {
                        if(!new ItemStack(BlockToSpawn, 1, stack.stackTagCompound.getInteger("BlockMeta")).tryPlaceItemIntoWorld(player, world, x, y, z, side, deltaX, deltaY, deltaZ))
                            return false;

                        world.setBlockMetadataWithNotify(x, y, z, stack.stackTagCompound.getInteger("BlockMeta"), 0);

                        NBTTagCompound tileCmp = stack.stackTagCompound.getCompoundTag("BlockNBT");

                        if(tileCmp != null && !tileCmp.hasNoTags()){
                            TileEntity tileEntity = TileEntity.createAndLoadEntity(tileCmp);

                            tileEntity.xCoord = x;
                            tileEntity.yCoord = y;
                            tileEntity.zCoord = z;

                            world.setTileEntity(x, y, z, tileEntity);
                        }

                        if(player.inventory.getCurrentItem() == stack)
                            player.inventory.decrStackSize(player.inventory.currentItem, 1);

                        return true;
                    }

                } else {
                    player.addChatComponentMessage(new ChatComponentText("\u00A74You most likely spawned this item in if this is not the case \u00A74please contact any HxC author."));
                    if(player.inventory.getCurrentItem() == stack)
                        player.inventory.decrStackSize(player.inventory.currentItem, 1);
                }
            }catch (Exception ignored){
                if(player.inventory.getCurrentItem() == stack)
                    player.inventory.decrStackSize(player.inventory.currentItem, 1);

                return true;
            }
        }else{
            return true;
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean flag) {
        if(stack.hasTagCompound()){
            tooltips.add("BlockMeta: " + stack.stackTagCompound.getInteger("BlockMeta"));
            tooltips.add("BlockUN: " + stack.stackTagCompound.getString("BlockUN"));
            tooltips.add("BlockOwner: " + stack.stackTagCompound.getString("BlockOwner"));
        }else{
            tooltips.add("\u00A74Where did you get this? \u00A7this broken :/");
        }
    }

    private static int[] getCoordsForPlacement(int x, int y, int z, int side) {
        if(side == 0)
            return new int[]{x, y - 1, z};
        else if(side == 1)
            return new int[]{x, y + 1, z};
        else if(side == 2)
            return new int[]{x, y, z - 1};
        else if(side == 3)
            return new int[]{x, y, z + 1};
        else if(side == 4)
            return new int[]{x - 1, y, z};
        else if(side == 5)
            return new int[]{x + 1, y, z};
        else
            return null;

    }
}
