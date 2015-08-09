package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Configs.Configurations;
import HxCKDMS.HxCBlocks.Items.ItemBinder;
import HxCKDMS.HxCBlocks.Items.ItemVacuumCore;
import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVacuum extends BlockContainer {

	public BlockVacuum(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("Vacuum");
        setTextureName(References.MOD_ID + ":Vacuum");
        setStepSound(soundTypeMetal);
        setHardness(1.0F);
        setResistance(1600.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileVacuum();
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
        ItemStack stack = player.getHeldItem();
        Item item = stack.getItem();
        TileVacuum HxCTile = (TileVacuum)tileEntity;

        if (item instanceof ItemVacuumCore && HxCTile.inventory[12] == null || (HxCTile.inventory[12] != null && HxCTile.inventory[12].stackSize <= Configurations.MaxBlockRange)) {
            if (HxCTile.inventory[12] == null) HxCTile.inventory[12] = new ItemStack(ModRegistry.VacuumCore, 1);
            else HxCTile.inventory[12] = new ItemStack(ModRegistry.VacuumCore,  HxCTile.inventory[12].stackSize + 1);
            if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
        }
        if (item instanceof ItemBinder && HxCTile.inventory[11] == null) {
            HxCTile.inventory[11] = stack;
            if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
        }
        return true;
    }
}
