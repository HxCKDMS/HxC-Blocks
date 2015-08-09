package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileSpawnerAccelerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawnerAccelerator extends BlockContainer {

    public BlockSpawnerAccelerator(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("Accelerator");
		setStepSound(soundTypeMetal);
        setTextureName(References.MOD_ID + ":Accelerator");
		setHardness(1.0F);
		setResistance(1600.0F);
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
        if (item instanceof ItemMonsterPlacer) {
            TileSpawnerAccelerator HxCTile = (TileSpawnerAccelerator) tileEntity;
            ItemMonsterPlacer egg = (ItemMonsterPlacer) item;
            int d = egg.getDamage(stack);
            HxCTile.Mob = egg.getItemStackDisplayName(new ItemStack(egg, 1, d)).replaceFirst("Spawn ", "").trim().replaceAll(" ", "");
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSpawnerAccelerator();
    }
}
