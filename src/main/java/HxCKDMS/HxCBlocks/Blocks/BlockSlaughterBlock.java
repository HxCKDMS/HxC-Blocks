package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Configs.Config;
import HxCKDMS.HxCBlocks.Items.ItemSlaughterCore;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class BlockSlaughterBlock extends BlockContainer {

	public BlockSlaughterBlock(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("SlaughterBlock");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":SlaughterBlock");
		setHardness(1.0F);
		setResistance(1600.0F);
	}

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSlaughterBlock();
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)tileEntity;
        ItemStack stack;
        Item item;
        if (player.getHeldItem() != null) {
            stack = player.getHeldItem();
            item = stack.getItem();
            if (item instanceof ItemSlaughterCore) {
                if (HxCTile.inventory[1] != null && HxCTile.inventory[1].stackSize <= Config.MaxRange)
                    HxCTile.inventory[1].stackSize++;
                else if (HxCTile.inventory[1] == null)
                    HxCTile.inventory[1] = new ItemStack(item);
            }
            List<String> weaplist = Arrays.asList(Config.Validweapons);
            if (item instanceof ItemSword || weaplist.contains(item.getUnlocalizedName())) {
                if (HxCTile.inventory[0] != null && !world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, x, y + 1, z, HxCTile.inventory[0]));

                HxCTile.inventory[0] = stack;
            }
            if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
        }
//        player.openGui(HxCBlocks.HxCBlocks, 0, world, x, y, z);
        return true;
    }
}
