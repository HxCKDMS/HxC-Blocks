package HxCKDMS.HxCBlocks.Proxy;

public class ClientProxy implements IProxy {
    private void initRendering(){
//        MinecraftForgeClient.registerItemRenderer(ModRegistry.HxCWrenchPlaceHolder, new ItemRendererHxCWrenchPlaceHolder());

    }

    @Override
    public void preInit() {
//        initRendering();
//        ClientRegistry.bindTileEntitySpecialRenderer(HxCTile.class, new HxCTileRender());
    }
    public void init() {}
    public void postInit() {}
    //Will only Override when they actually do something because I like clean code
}
