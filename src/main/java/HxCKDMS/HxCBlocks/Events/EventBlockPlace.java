package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Blocks.HxCBlock;
import HxCKDMS.HxCBlocks.Configs.Configurations;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
@SuppressWarnings("all")
public class EventBlockPlace {
    @SubscribeEvent
    public void PlaceEvent(BlockEvent.PlaceEvent event) {
        if (event.block instanceof HxCBlock && (event.block.getDamageValue(event.world, event.x, event.y,event.z) == 6 || event.block.getDamageValue(event.world, event.x, event.y,event.z) == 7) && Configurations.SafetyChecks){
            if (!event.player.onGround && event.player.isSneaking() && event.player.capabilities.isCreativeMode) {
                String puuid = event.player.getGameProfile().getId().toString();
                String pname = event.player.getDisplayName();
                if (!pname.contains("[")) {
                    event.setCanceled(false);
                    List<EntityPlayerMP> players = event.world.playerEntities;
                    for (EntityPlayerMP player : players) {
                        player.addChatMessage(new ChatComponentText("\u00A74Goodbye cruel world."));
                    }
                }
            } else {
                event.setCanceled(true);
            }
        }
    }
}
