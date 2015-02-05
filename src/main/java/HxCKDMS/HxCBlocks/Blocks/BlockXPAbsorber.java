package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockXPAbsorber extends BlockContainer {

	public BlockXPAbsorber(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setBlockName("XPAbsorber");
        setBlockTextureName(References.MOD_ID + ":XPAbsorber");
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
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        TileXPAbsorber block = (TileXPAbsorber)world.getTileEntity(x, y, z);
        block.AllowUpdate = false; block.modifier = 1; block.BoundPlayer = "none";
    }
}
