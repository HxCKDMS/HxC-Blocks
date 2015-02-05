package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVacuum extends BlockContainer {

	public BlockVacuum(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setBlockName("Vacuum");
        setBlockTextureName(References.MOD_ID + ":Vacuum");
        setStepSound(soundTypeMetal);
        setHardness(1.0F);
        setResistance(1600.0F);
        setLightLevel(1);
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
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        TileVacuum block = (TileVacuum)world.getTileEntity(x, y, z);
        block.modifier = 1;
    }
}
