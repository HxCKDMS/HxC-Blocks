package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Items.ItemSoulBinder;
import HxCKDMS.HxCBlocks.Items.ItemSoulFragment;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockXPAbsorber extends BlockContainer {

	public BlockXPAbsorber(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("XPAbsorber");
        setTextureName(References.MOD_ID + ":XPAbsorber");
        setStepSound(soundTypeMetal);
        setHardness(1.0F);
        setResistance(1600.0F);
        setLightLevel(1);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileXPAbsorber();
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking() || player.getHeldItem() == null) {
            return false;
        }
        TileXPAbsorber HxCTile = (TileXPAbsorber) tileEntity;
        ItemStack stack = player.getHeldItem();
        Item item = stack.getItem();
        if (item instanceof ItemSoulBinder) {
            HxCTile.Item = stack;
            if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
        } else if (item instanceof ItemSoulFragment && HxCTile.Range < Configurations.MaxBlockRange) {
            HxCTile.Range++;
            if (!player.capabilities.isCreativeMode)player.inventory.decrStackSize(player.inventory.currentItem, 1);
        }
        return true;
    }
}
