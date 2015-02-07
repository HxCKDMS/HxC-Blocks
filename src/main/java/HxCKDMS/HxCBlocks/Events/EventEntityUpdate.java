package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.io.File;

@SuppressWarnings("unused")
public class EventEntityUpdate {
    int SoulTimer = 12000;
    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event){
        if (event.entityLiving instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
            String UUID = player.getUniqueID().toString();
            File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
            NBTTagCompound nbt = NBTFileIO.getData(CustomPlayerData);
            float soul = nbt.getFloat("Soul");
            if (soul < 1.0f) {
                SoulTimer--;
                if (SoulTimer <= 0){
                    NBTFileIO.setFloat(CustomPlayerData, "Soul", (soul + 0.1f));
                    SoulTimer = 12000;
                }
                if (SoulTimer == 5000) {
                    player.addPotionEffect(new PotionEffect(Potion.wither.getId(), 10, 1, false));
                }
            }
        }
    }
}
