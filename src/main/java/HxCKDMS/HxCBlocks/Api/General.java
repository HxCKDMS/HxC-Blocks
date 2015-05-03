package HxCKDMS.HxCBlocks.Api;

import HxCKDMS.HxCBlocks.Items.ItemHxCWrench;
import net.minecraft.block.Block;

@SuppressWarnings("unused")
public class General {
    public static void addToHxCWrenchBlackList(Block block){
        ItemHxCWrench.HxCWrenchBL.add(block);
    }
}
