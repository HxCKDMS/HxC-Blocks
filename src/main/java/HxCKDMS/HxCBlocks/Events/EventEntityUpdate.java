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
            NBTTagCompound nbt = NBTFileIO.getData(CustomPlayerData);
            float soul = nbt.getFloat("Soul");
            int SoulTimer = nbt.getInteger("SoulTimer");
            if (soul != 1f) {
                SoulTimer--;
                nbt.setInteger("SoulTimer", SoulTimer);
                if (SoulTimer <= 0){
                    nbt.setFloat("Soul", soul+.01f);
                    nbt.setInteger("SoulTimer", 12000);
                }
            }
        }
    }
}
