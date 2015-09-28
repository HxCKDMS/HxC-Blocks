package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.lib.Reference;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemVacuumCore extends Item{
    public ItemVacuumCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("VacuumCore");
		setTextureName(Reference.MOD_ID + ":VacuumCore");
		setMaxStackSize(64);
	}
}
