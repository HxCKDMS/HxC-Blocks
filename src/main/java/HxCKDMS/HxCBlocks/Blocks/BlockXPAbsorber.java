package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
    public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileXPAbsorber HxCTile = (TileXPAbsorber)world.getTileEntity(x,y,z);
        if (HxCTile.AllowUpdate) {
            String BoundPlayer = HxCTile.BoundPlayer;
            int Modifier = HxCTile.modifier;
            int XP = HxCTile.XP;
//            ItemStack DataTile = new ItemStack(this, 1);
//            DataTile.setTagCompound(new NBTTagCompound());
//            NBTTagCompound data = DataTile.getTagCompound();
//            data.setInteger("XP", XP);
//            data.setString("BoundPlayer", BoundPlayer);
//            data.setInteger("Modifier", Modifier);
//            DataTile.setStackDisplayName("XP Absorber(Bound)");
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack((this))));
            if (Modifier > 1)world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModRegistry.SoulFragment, Modifier-1)));
            if (XP > 0)world.spawnEntityInWorld(new EntityXPOrb(world, x, y, z, XP));
            ItemStack SoulBinder = new ItemStack(ModRegistry.SoulBinder);
            SoulBinder.getTagCompound().setString("BoundPlayer", BoundPlayer);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, SoulBinder));
        } else {
            world.setBlockToAir(x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this)));
        }
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        /*if (stack == (new ItemStack(this)) && entity instanceof EntityPlayer && stack.getDisplayName().contains("Bound")) {
            NBTTagCompound data = stack.getTagCompound();
            String BoundPlayer = data.getString("BoundPlayer");
            int Modifier = data.getInteger("Modifier");
            int XP = data.getInteger("XP");
            TileXPAbsorber HxCTile = (TileXPAbsorber)world.getTileEntity(x, y, z);
            HxCTile.BoundPlayer = BoundPlayer; HxCTile.modifier = Modifier; HxCTile.AllowUpdate = true; HxCTile.XP = XP;
            world.markBlockForUpdate(x, y, z);
        } else */if (stack == (new ItemStack(this))) {
            TileXPAbsorber HxCTile = (TileXPAbsorber)world.getTileEntity(x, y, z);
            HxCTile.modifier = 1; HxCTile.AllowUpdate = false;
        }
    }
}
