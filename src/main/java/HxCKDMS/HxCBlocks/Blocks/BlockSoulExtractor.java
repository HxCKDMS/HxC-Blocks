package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSoulExtractor extends Block {
	public BlockSoulExtractor(Material material) {
        super(material);
        setCreativeTab(ModRegistry.HxCBlocks);
        setBlockName("SoulExtractor");
        setStepSound(soundTypeMetal);
        setBlockTextureName("SoulExtractor");
        setHardness(1.0F);
        setResistance(1600.0F);
        setLightLevel(1);
        setLightOpacity(0);
    }

}
