package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemSlaughterCore extends Item{
    public ItemSlaughterCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("SlaughterCore");
		setTextureName(References.MOD_ID + ":SlaughterCore");
		setMaxStackSize(64);
	}
}
