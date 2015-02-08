package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.Configs.Config;
import HxCKDMS.HxCCore.Utils.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, dependencies = References.DEPENDENCIES)
public class HxCBlocks {
    @Mod.Instance(References.MOD_ID)
    public static HxCBlocks HxCBlocks;

    public static Config Config;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
        ModRegistry.preInit();
        LogHelper.info("Pre initialization has been completed.", References.MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModRegistry.init();
        LogHelper.info("Initialization has been completed.", References.MOD_NAME);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("Post initialization has been completed.", References.MOD_NAME);
    }
}
