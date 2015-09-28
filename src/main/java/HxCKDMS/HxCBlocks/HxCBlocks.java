package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCBlocks.Client.Rendering.GUI.GUIHandler;
import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Proxy.IProxy;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.network.HxCTileSync;
import HxCKDMS.HxCCore.HxCCore;
import HxCKDMS.HxCCore.api.Configuration.Category;
import HxCKDMS.HxCCore.api.Configuration.HxCConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

import java.io.File;

import static HxCKDMS.HxCBlocks.lib.Reference.*;

@SuppressWarnings("unused")
@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class HxCBlocks {
    @Mod.Instance(MOD_ID)
    public static HxCBlocks HxCBlocks;

    public static SimpleNetworkWrapper networkWrapper = new SimpleNetworkWrapper(MOD_ID.toLowerCase());

    @SidedProxy(clientSide = CLIENT_PROXY_LOCATION, serverSide = SERVER_PROXY_LOCATION)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        HxCConfig hxcconfig = new HxCConfig();
        registerNewConfigSys(hxcconfig);
        ModRegistry.preInit();
        proxy.preInit();
        networkWrapper.registerMessage(HxCTileSync.handler.class, HxCTileSync.class, 1, Side.SERVER);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
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
