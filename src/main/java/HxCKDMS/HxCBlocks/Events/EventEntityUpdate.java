package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
            NBTTagCompound playerData = NBTFileIO.getData(CustomPlayerData);
            float soul = playerData.getFloat("Soul");
            int SoulTimer = playerData.getInteger("SoulTimer");
            if (soul != 1f) {
                SoulTimer--;
                playerData.setInteger("SoulTimer", SoulTimer);
                if (SoulTimer == 0){
                    playerData.setFloat("Soul", soul+.01f);
                    playerData.setInteger("SoulTimer", 12000);
                }
            }
        }
    }
}
