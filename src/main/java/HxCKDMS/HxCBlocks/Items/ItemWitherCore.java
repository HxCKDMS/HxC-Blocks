package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemWitherCore extends Item{
    public ItemWitherCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("WitherCore");
		setTextureName(References.MOD_ID + ":WitherCore");
		setMaxStackSize(64);
	}
}
