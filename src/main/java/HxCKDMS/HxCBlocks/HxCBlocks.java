package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCBlocks.Proxy.CommonProxy;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCCore.Utils.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class HxCBlocks {
    @Mod.Instance(References.MOD_ID)
    public static HxCBlocks HxCBlocks;

    @SidedProxy(serverSide = References.SERVER_PROXY_LOCATION, clientSide = References.CLIENT_PROXY_LOCATION)
    public static CommonProxy proxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModRegistry.preInit();

        LogHelper.info("Pre initialization has been completed.", References.MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModRegistry.init();
        proxy.init();
        LogHelper.info("Initialization has been completed.", References.MOD_NAME);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("Post initialization has been completed.", References.MOD_NAME);
    }
}
