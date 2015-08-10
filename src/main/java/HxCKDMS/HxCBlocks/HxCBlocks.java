package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Proxy.IProxy;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.Category;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, dependencies = References.DEPENDENCIES)
public class HxCBlocks {
    @Mod.Instance(References.MOD_ID)
    public static HxCBlocks HxCBlocks;

    @SidedProxy(clientSide = References.CLIENT_PROXY_LOCATION, serverSide = References.SERVER_PROXY_LOCATION)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        HxCConfig hxcconfig = new HxCConfig();
        registerNewConfigSys(hxcconfig);
        ModRegistry.preInit();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModRegistry.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){}

    public void registerNewConfigSys(HxCConfig config) {
        config.registerCategory(new Category("General", "General Stuff"));
        config.handleConfig(Configurations.class, new File(HxCCore.HxCConfigDir, "HxCBlocks.cfg"));
    }
}
