package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.io.File;

@SuppressWarnings("unused")
public class EventEntityUpdate {
    @SubscribeEvent
    @SideOnly(Side.SERVER)
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
                NBTFileIO.setInteger(CustomPlayerData, "SoulTimer", SoulTimer);
                if (SoulTimer <= 0){
                    NBTFileIO.setFloat(CustomPlayerData, "Soul", (soul+0.1f));
                    NBTFileIO.setInteger(CustomPlayerData, "SoulTimer", 12000);
                }
            }
        }
    }
}
