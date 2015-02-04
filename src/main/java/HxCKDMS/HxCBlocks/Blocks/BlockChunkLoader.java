package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileChunkLoader;
import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChunkLoader extends BlockContainer {

	public BlockChunkLoader(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("ChunkLoader");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":ChunkLoader");
		setHardness(1.0F);
		setResistance(1600.0F);
        setLightLevel(1);
        setLightOpacity(0);
	}

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileChunkLoader();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        TileSlaughterBlock block = (TileSlaughterBlock)world.getTileEntity(x, y, z);
        block.BoundPlayer = world.getClosestPlayer(x,y,z,3).getDisplayName();// block.modifier = 1; block.AllowUpdate = true;
    }
}
