package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSlaughterBlock extends BlockContainer {

	public BlockSlaughterBlock(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("SlaughterBlock");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":SlaughterBlock");
		setHardness(1.0F);
		setResistance(1600.0F);
        setLightLevel(1);
        setLightOpacity(0);
	}

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSlaughterBlock();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        TileSlaughterBlock block = (TileSlaughterBlock)world.getTileEntity(x, y, z);
        block.x = x; block.y = y; block.z = z; block.AllowUpdate = false; block.BoundPlayer = world.getClosestPlayer(x,y,z,3).getDisplayName(); block.modifier = 1;
        world.markBlockForUpdate(x, y, z);
    }
}
