package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileSpawnerAccelerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawnerAccelerator extends BlockContainer {

    public BlockSpawnerAccelerator(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setUnlocalizedName("Accelerator");
		setStepSound(soundTypeMetal);
		setHardness(1.0F);
		setResistance(1600.0F);
	}

    @Override
    public boolean canConnectRedstone(IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSpawnerAccelerator();
    }
}
