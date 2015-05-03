package HxCKDMS.HxCBlocks.Proxy;

import HxCKDMS.HxCBlocks.Client.Rendering.ItemRenderer.ItemRendererHxCWrenchPlaceHolder;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    private void initRendering(){
        MinecraftForgeClient.registerItemRenderer(ModRegistry.HxCWrenchPlaceHolder, new ItemRendererHxCWrenchPlaceHolder());
    }

    @Override
    public void preInit() {
        initRendering();
    }
}
