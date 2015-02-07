package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSoulExtractor extends Block {
	public BlockSoulExtractor(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setUnlocalizedName("SoulExtractor");
        setStepSound(soundTypeMetal);
        setHardness(1.0F);
        setResistance(1600.0F);
        setLightLevel(1);
    }
}
