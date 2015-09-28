package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockHxC extends ItemBlock {
    public ItemBlockHxC(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxDurability(0);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "block" + Reference.BLOCKS[itemStack.getCurrentDurability()];
    }

    @Override
    public int getMetadata(int metadata){
        return metadata;
    }
}