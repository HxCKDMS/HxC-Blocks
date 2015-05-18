package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockGreyGoo extends Block {
    public BlockGreyGoo(Material material) {
		super(material);
		setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
		setBlockName("GreyGoo");
		setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":GreyGoo");
		setHardness(1.0F);
		setResistance(1600.0F);
        setLightLevel(1);
	}

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (world.getBlock(x-1, y, z).getBlockHardness(world, x-1, y, z) > 0 && world.getBlock(x-1, y, z) != this)world.setBlock(x-1, y, z, this);
        if (world.getBlock(x+1, y, z).getBlockHardness(world, x+1, y, z) > 0 && world.getBlock(x+1, y, z) != this)world.setBlock(x+1, y, z, this);
        if (world.getBlock(x, y, z-1).getBlockHardness(world, x, y, z-1) > 0 && world.getBlock(x, y, z-1) != this)world.setBlock(x, y, z-1, this);
        if (world.getBlock(x, y, z+1).getBlockHardness(world, x, y, z+1) > 0 && world.getBlock(x, y, z+1) != this)world.setBlock(x, y, z+1, this);
        if (world.getBlock(x, y+1, z).getBlockHardness(world, x, y+1, z) > 0 && world.getBlock(x, y+1, z) != this)world.setBlock(x, y+1, z, this);
        if (world.getBlock(x, y-1, z).getBlockHardness(world, x, y-1, z) > 0 && world.getBlock(x, y-1, z) != this)world.setBlock(x, y-1, z, this);
    }
}
