package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCBlocks.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabHxCBlocks {
    public static CreativeTabs tabHxCBlocks = new CreativeTabs(Reference.MOD_ID){
//        public Item getTabIconItem() {
//            return ModRegistry.SoulFragment;
//        }
        public Item getTabIconItem() {
            return Items.baked_potato;
        }
    };
}