package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.lib.Reference;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemSlaughterCore extends Item{
    public ItemSlaughterCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SlaughterCore");
		setTextureName(Reference.MOD_ID + ":SlaughterCore");
		setMaxStackSize(64);
	}
}
