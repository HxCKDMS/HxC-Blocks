package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileLeBomb;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLeBomb extends BlockContainer {
    public BlockLeBomb(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("LeBomb");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":LeBomb");
		setHardness(1.0F);
		setResistance(1600.0F);
        setLightLevel(1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileLeBomb();
	}
}
