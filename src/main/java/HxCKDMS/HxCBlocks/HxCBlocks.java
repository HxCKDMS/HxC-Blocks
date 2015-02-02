package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCCore.Utils.LogHelper;
import HxCKDMS.HxCBlocks.Proxy.ClientProxy;
import HxCKDMS.HxCBlocks.Proxy.ServerProxy;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class HxCBlocks {
    @Mod.Instance(References.MOD_ID)
    public static HxCBlocks HxCBlocks;

    @SidedProxy(serverSide = References.SERVER_PROXY_LOCATION, clientSide = References.CLIENT_PROXY_LOCATION)
    public static ClientProxy cProxy;
    public static ServerProxy sProxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModRegistry.preInit();
        cProxy.preInit();

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
