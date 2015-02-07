package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemSlaughterCore extends Item{
    public ItemSlaughterCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SlaughterCore");
		setMaxStackSize(64);
	}
}
