package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemWitherCore extends Item{
    public ItemWitherCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("WitherCore");
		setMaxStackSize(64);
	}
}
