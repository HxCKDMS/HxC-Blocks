package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileSpawnerAccelerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawnerAccelerator extends BlockContainer {

    public BlockSpawnerAccelerator(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("Accelerator");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":Accelerator");
		setHardness(1.0F);
		setResistance(1600.0F);
	}

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSpawnerAccelerator();
    }
}
