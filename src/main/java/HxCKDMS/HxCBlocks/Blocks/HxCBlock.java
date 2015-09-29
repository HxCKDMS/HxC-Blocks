package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.HxCBlocks;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCBlocks.lib.Reference;
import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static HxCKDMS.HxCBlocks.lib.Reference.BLOCKS;

public class HxCBlock extends Block {
    public HxCBlock() {
        super(Material.rock);
        setHardness(1.0F);
        setResistance(1600.0F);
        setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return meta != BLOCKS.length-1;
    }

//    @Override
//    public int getRenderType() {
//        return -1;
//    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (metadata == BLOCKS.length-1)
            extractSoul(world, player);
        if (tileEntity == null || player.isSneaking() || !hasGui(metadata)) {
            return false;
        }
        player.openGui(HxCBlocks.HxCBlocks, tileEntity.blockMetadata, world, x, y, z);
        return true;
    }

    private void extractSoul(World world, EntityPlayer player) {
        if (!world.isRemote) {
            String UUID = player.getUniqueID().toString();
            File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
            float soul = NBTFileIO.getFloat(CustomPlayerData, "Soul");
            float num = world.rand.nextFloat();
            if (num <= soul) {
                ItemStack fragment = new ItemStack(Items.baked_potato);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("Player", player.getDisplayName());
                fragment.setTagCompound(tag);
                float damagedSoul = soul - num;
                NBTFileIO.setFloat(CustomPlayerData, "Soul", damagedSoul);
                float hp = player.getHealth();
                player.attackEntityFrom(new DamageSource("SoulExtraction"), hp * 1000);
                if (!world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, fragment));
                player.addChatMessage(new ChatComponentText("Your soul aches."));
            }
        }
    }

    private boolean hasGui(int meta) {
        return meta < 6;
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        ItemStack stack = new ItemStack(this, 1, metadata);
        return icons.get(stack.getUnlocalizedName());
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        HxCTile tile = new HxCTile();
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("name", BLOCKS[metadata]);
        tile.writeToNBT(tag);
        return tile;
    }

    @SideOnly(Side.CLIENT)
    private HashMap<String, IIcon> icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        icons = new HashMap<>();
        for (String name : BLOCKS)
            icons.put("block" + name, iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + name));
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for(int i = 0; i < BLOCKS.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
