package HxCKDMS.HxCBlocks.Blocks;

import HxCKDMS.HxCBlocks.Reference.References;
import HxCKDMS.HxCBlocks.Registry.CreativeTabHxCBlocks;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.io.File;
import java.util.Random;

public class BlockSoulExtractor extends Block {
    Random rand = new Random();
	public BlockSoulExtractor(Material material) {
        super(material);
        setCreativeTab(CreativeTabHxCBlocks.tabHxCBlocks);
        setBlockName("SoulExtractor");
        setStepSound(soundTypeMetal);
        setBlockTextureName(References.MOD_ID + ":SoulExtractor");
        setHardness(1.0F);
        setResistance(1600.0F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.getHeldItem() != null || !player.isSneaking()) {return false;}
        if (!world.isRemote) {
            String UUID = player.getUniqueID().toString();
            File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
            float soul = NBTFileIO.getFloat(CustomPlayerData, "Soul");
            float num = rand.nextFloat();
            if (num <= soul) {
                ItemStack fragment = new ItemStack(ModRegistry.SoulFragment);
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
        return false;
    }
}
