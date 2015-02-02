package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCBlocks.Reference.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHxCBlocks {
    public static CreativeTabs tabHxCBlocks = new CreativeTabs(References.MOD_ID){
        public Item getTabIconItem() {
            return ModRegistry.SoulFragment;
        }
    };
}