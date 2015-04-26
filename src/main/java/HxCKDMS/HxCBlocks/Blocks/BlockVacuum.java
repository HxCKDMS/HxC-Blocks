package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.TileVacuum;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
    public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileVacuum HxCTile = (TileVacuum)world.getTileEntity(x,y,z);
        int[] OtherPos = HxCTile.OtherPos;
        int Modifier = HxCTile.modifier;
        if (Modifier > 1) {
            ItemStack DataTile = new ItemStack(this, 1);
            DataTile.setTagCompound(new NBTTagCompound());
            NBTTagCompound data = DataTile.getTagCompound();
            data.setIntArray("OtherPos", OtherPos);
            data.setInteger("Modifier", Modifier);
            DataTile.setStackDisplayName("Item Vacuum(Bound)");
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, DataTile));
//            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModRegistry.VacuumCore, Modifier-1)));
        } else {
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this)));
        }
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (stack == (new ItemStack(this)) && entity instanceof EntityPlayer && stack.getDisplayName().contains("Bound")) {
            NBTTagCompound data = stack.getTagCompound();
            int[] OtherPos = data.getIntArray("OtherPos");
            int Modifier = data.getInteger("Modifier");
            TileVacuum HxCTile = (TileVacuum)world.getTileEntity(x, y, z);
            HxCTile.OtherPos = OtherPos; HxCTile.modifier = Modifier;
        } else if (stack == (new ItemStack(this))) {
            TileVacuum HxCTile = (TileVacuum)world.getTileEntity(x, y, z);
            HxCTile.modifier = 1;
        }
    }
}
