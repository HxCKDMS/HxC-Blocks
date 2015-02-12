package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
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
	}

    @Override
    public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileSlaughterBlock HxCTile = (TileSlaughterBlock)world.getTileEntity(x,y,z);
//        int[] Targets = HxCTile.Targets;
        int Modifier = HxCTile.modifier;
        if (Modifier > 1) {
//            ItemStack DataTile = new ItemStack(this, 1);
//            DataTile.setTagCompound(new NBTTagCompound());
//            NBTTagCompound data = DataTile.getTagCompound();
//            data.setIntArray("Targets", Targets);
//            data.setInteger("Modifier", Modifier);
//            DataTile.setStackDisplayName("Slaughter Block(Modified)");
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this)));
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModRegistry.SlaughterCore, Modifier-1)));
        } else {
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this)));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileSlaughterBlock();
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        /*if (stack == (new ItemStack(this)) && entity instanceof EntityPlayer && stack.getDisplayName().contains("Modified")) {
            NBTTagCompound data = stack.getTagCompound();
            int[] Targets = data.getIntArray("Targets");
            int Modifier = data.getInteger("Modifier");
            TileSlaughterBlock HxCTile = (TileSlaughterBlock)world.getTileEntity(x, y, z);
            HxCTile.Targets = Targets; HxCTile.modifier = Modifier;
            world.markBlockForUpdate(x, y, z);
        } else*/ if (stack == (new ItemStack(this))) {
            TileSlaughterBlock HxCTile = (TileSlaughterBlock)world.getTileEntity(x, y, z);
            HxCTile.modifier = 1;
        }
    }
}
