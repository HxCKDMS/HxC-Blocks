package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Blocks.BlockSoulExtractor;
import HxCKDMS.HxCBlocks.Items.ItemSoulBinder;
import HxCKDMS.HxCBlocks.Items.ItemSoulFragment;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.io.File;
import java.util.EventListener;
import java.util.Random;

@SuppressWarnings("unused")
public class EventBlockInteract implements EventListener {
    Random rand = new Random();
    int randInt = rand.nextInt(50);
    float randfloat = (randInt*.01f);
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event){
        Block block = event.world.getBlock(event.x, event.y, event.z);
        TileEntity tile = event.world.getTileEntity(event.x, event.y, event.z);
        World world = event.world;
        EntityPlayer player = event.entityPlayer;
        String UUID = player.getUniqueID().toString();
        File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
        NBTTagCompound playerData = NBTFileIO.getData(CustomPlayerData);
        Item item = null; ItemStack stack = null;
        if (event.entityPlayer.getHeldItem() != null){
            item = event.entityPlayer.getHeldItem().getItem();
            stack = event.entityPlayer.getHeldItem();
        }
        /**                                                             *
         *        All data possibly needed gathered above               *
         *                                                             **/
        if(block instanceof BlockSoulExtractor){
            float soul = playerData.getFloat("Soul");
            if (randfloat <= soul){
                ItemStack fragment = new ItemStack(ModRegistry.SoulFragment);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("Player", player.getDisplayName());
                fragment.setTagCompound(tag);
                world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, fragment));
                playerData.setFloat("Soul", (soul - randfloat));
                float hp = player.getHealth();
                player.attackEntityFrom(new DamageSource("SoulExtraction"), hp*1000);
            }
        } else if (tile instanceof TileXPAbsorber) {
            if (item instanceof ItemSoulBinder) {
                String p = stack.getTagCompound().getString("Player");
                TileXPAbsorber xpAbsorber = (TileXPAbsorber)tile;
                xpAbsorber.BoundPlayer = p;
                world.markBlockForUpdate(event.x, event.y, event.z);
            } else if (item instanceof ItemSoulFragment) {
                TileXPAbsorber xpAbsorber = (TileXPAbsorber)tile;
                xpAbsorber.modifier = (xpAbsorber.modifier+3);
                player.addChatMessage(new ChatComponentText("\u00A73Range was set to " + xpAbsorber.modifier));
                player.inventory.decrStackSize(player.inventory.currentItem, 1);
                world.markBlockForUpdate(event.x, event.y, event.z);
            }
            //TODO: Add Configuring Item and gui to block if the item was used on block.
        }
    }
}
