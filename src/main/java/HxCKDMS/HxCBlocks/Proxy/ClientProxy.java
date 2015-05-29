package HxCKDMS.HxCBlocks.Proxy;

import HxCKDMS.HxCBlocks.Client.Rendering.ItemRenderer.ItemRendererHxCWrenchPlaceHolder;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy implements IProxy {
    private void initRendering(){
        MinecraftForgeClient.registerItemRenderer(ModRegistry.HxCWrenchPlaceHolder, new ItemRendererHxCWrenchPlaceHolder());
    }

    @Override
    public void preInit() {
        initRendering();
    }
    public void init() {}
    public void postInit() {}
    //Will only Override when they actually do something because I like clean code
}
