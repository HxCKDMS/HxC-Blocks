package HxCKDMS.HxCBlocks.Proxy;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy{
    @Override
    public void init() {
        Minecraft mc = Minecraft.getMinecraft();
        ItemModelMesher mesher = mc.getRenderItem().getItemModelMesher();
        mesher.register(ModRegistry.Binder, 0, new ModelResourceLocation(References.MOD_ID + ":Binder", "inventory"));
        mesher.register(ModRegistry.SlaughterCore, 0, new ModelResourceLocation(References.MOD_ID + ":SlaughterCore", "inventory"));
        mesher.register(ModRegistry.WitherCore, 0, new ModelResourceLocation(References.MOD_ID + ":WitherCore", "inventory"));
        mesher.register(ModRegistry.SoulBinder, 0, new ModelResourceLocation(References.MOD_ID + ":SoulBinder", "inventory"));
        mesher.register(ModRegistry.SoulFragment, 0, new ModelResourceLocation(References.MOD_ID + ":SoulFragment", "inventory"));
        mesher.register(ModRegistry.VacuumCore, 0, new ModelResourceLocation(References.MOD_ID + ":VacuumCore", "inventory"));

        mesher.register(Item.getItemFromBlock(ModRegistry.Vacuum), 0, new ModelResourceLocation(References.MOD_ID + ":Vacuum", "inventory"));
        mesher.register(Item.getItemFromBlock(ModRegistry.SlaughterBlock), 0, new ModelResourceLocation(References.MOD_ID + ":SlaughterBlock", "inventory"));
        mesher.register(Item.getItemFromBlock(ModRegistry.Accelerator), 0, new ModelResourceLocation(References.MOD_ID + ":Accelerator", "inventory"));
        mesher.register(Item.getItemFromBlock(ModRegistry.Barrier), 0, new ModelResourceLocation(References.MOD_ID + ":Barrier", "inventory"));
        mesher.register(Item.getItemFromBlock(ModRegistry.SoulExtractor), 0, new ModelResourceLocation(References.MOD_ID + ":SoulExtractor", "inventory"));
        mesher.register(Item.getItemFromBlock(ModRegistry.XPAbsorber), 0, new ModelResourceLocation(References.MOD_ID + ":XPAbsorber", "inventory"));

        super.init();
    }
}
