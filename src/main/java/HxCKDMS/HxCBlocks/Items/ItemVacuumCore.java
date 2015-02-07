package HxCKDMS.HxCBlocks.Items;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.item.Item;

public class ItemVacuumCore extends Item{
    public ItemVacuumCore(){
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("VacuumCore");
		setMaxStackSize(64);
	}
}
