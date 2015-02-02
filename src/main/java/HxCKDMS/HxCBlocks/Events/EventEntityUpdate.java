package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.io.File;

@SuppressWarnings("unused")
public class EventEntityUpdate {
    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event){
        if (event.entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            String UUID = player.getUniqueID().toString();
            File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
            float soul = NBTFileIO.getFloat(CustomPlayerData, "Soul");
            int SoulTimer = NBTFileIO.getInteger(CustomPlayerData, "SoulTimer");
            if (soul != 1f) {
                SoulTimer--;
                NBTFileIO.setInteger(CustomPlayerData, "SoulTimer", SoulTimer);
                if (SoulTimer == 0){
                    NBTFileIO.setFloat(CustomPlayerData, "Soul", soul+.01f);
                    NBTFileIO.setInteger(CustomPlayerData, "SoulTimer", 12000);
                }
            }
        }
    }
}
