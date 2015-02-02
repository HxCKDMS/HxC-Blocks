package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCCore.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHxCBlocks {
    public static CreativeTabs tabHxCBlocks = new CreativeTabs(Reference.MOD_ID){
        public Item getTabIconItem() {
            return ModRegistry.SoulFragment;
        }
    };
}