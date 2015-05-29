package HxCKDMS.HxCBlocks;

import HxCKDMS.HxCBlocks.Configs.Config;
import HxCKDMS.HxCBlocks.Proxy.IProxy;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, dependencies = References.DEPENDENCIES)
public class HxCBlocks {
    @Mod.Instance(References.MOD_ID)
    public static HxCBlocks HxCBlocks;

    @SidedProxy(clientSide = References.CLIENT_PROXY_LOCATION, serverSide = References.SERVER_PROXY_LOCATION)
    public static IProxy proxy;

    public static Config Config;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
        ModRegistry.preInit();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModRegistry.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){}
}
