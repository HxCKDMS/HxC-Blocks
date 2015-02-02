package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSoulExtractor extends Block {
	public BlockSoulExtractor(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setBlockName("SoulExtractor");
        setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":SoulExtractor");
        setHardness(1.0F);
        setResistance(1600.0F);
        setLightLevel(1);
        setLightOpacity(0);
    }

}
