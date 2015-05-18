package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Items.ItemBinder;
import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.EventListener;

@SuppressWarnings("unused")
public class EventBlockInteract implements EventListener {
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        TileEntity tile = event.world.getTileEntity(event.x, event.y, event.z);
        EntityPlayer player = event.entityPlayer;
        if (tile instanceof IInventory && !(tile instanceof TileVacuum) && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && player.getHeldItem().getItem() instanceof ItemBinder) {
            NBTTagCompound dat = player.getHeldItem().getTagCompound();
            dat.setInteger("x", event.x);
            dat.setInteger("y", event.y);
            dat.setInteger("z", event.z);
            player.getHeldItem().setTagCompound(dat);
        }
    }
}
