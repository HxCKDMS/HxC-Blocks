package HxCKDMS.HxCBlocks.Events;

import HxCKDMS.HxCBlocks.Blocks.*;
import HxCKDMS.HxCBlocks.Items.*;
import HxCKDMS.HxCBlocks.Registry.ModRegistry;
import HxCKDMS.HxCBlocks.TileEntities.*;
import HxCKDMS.HxCCore.Handlers.NBTFileIO;
import HxCKDMS.HxCCore.HxCCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.io.File;
import java.util.EventListener;
import java.util.Random;

@SuppressWarnings("unused")
public class EventBlockInteract implements EventListener {
    Random rand = new Random();
    int randInt = rand.nextInt(50);
    float randfloat = (randInt/10);
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.world.getBlock(event.x, event.y, event.z);
        TileEntity tile = event.world.getTileEntity(event.x, event.y, event.z);
        World world = event.world;
        EntityPlayer player = event.entityPlayer;
        Item item = null;
        ItemStack stack = null;
        String UUID = player.getUniqueID().toString();
//        if (block instanceof BlockVacuum || block instanceof BlockBarrier || block instanceof BlockSoulExtractor || block instanceof BlockSlaughterBlock || block instanceof BlockSpawnerAccelerator || block instanceof BlockXPAbsorber || block instanceof IInventory) {
            if (event.entityPlayer.getHeldItem() != null) {
                item = event.entityPlayer.getHeldItem().getItem();
                stack = event.entityPlayer.getHeldItem();
            }
            /**                                                             *
             *        All data possibly needed gathered above               *
             *                                                             **/
            if (block instanceof BlockSoulExtractor && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                File CustomPlayerData = new File(HxCCore.HxCCoreDir, "HxC-" + UUID + ".dat");
                float soul = NBTFileIO.getFloat(CustomPlayerData, "Soul");
                if (randfloat <= soul) {
                    ItemStack fragment = new ItemStack(ModRegistry.SoulFragment);
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setString("Player", player.getDisplayName());
                    fragment.setTagCompound(tag);
                    float damagedSoul = soul - randfloat;
                    NBTFileIO.setFloat(CustomPlayerData, "Soul", damagedSoul);
                    float hp = player.getHealth();
                    player.attackEntityFrom(new DamageSource("SoulExtraction"), hp * 1000);
                    if (!world.isRemote)
                        world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, fragment));
                }
            } else if (tile instanceof TileXPAbsorber && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                TileXPAbsorber HxCTile = (TileXPAbsorber) tile;
                if (item instanceof ItemSoulBinder) {
                    HxCTile.BoundPlayer = stack.getTagCompound().getString("Player");
                    HxCTile.AllowUpdate = true;
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.decrStackSize(player.inventory.currentItem, 1);
                } else if (item instanceof ItemSoulFragment && HxCTile.modifier <= 31) {
                    HxCTile.modifier = (HxCTile.modifier + 1);
                    if (!world.isRemote)
                        player.addChatMessage(new ChatComponentText("\u00A73Range was set to " + HxCTile.modifier));
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.decrStackSize(player.inventory.currentItem, 1);
                }
                //TODO: Add Configuring Item and gui to block if the item was used on block.
            } else if (tile instanceof TileSlaughterBlock && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                TileSlaughterBlock HxCTile = (TileSlaughterBlock) tile;
                if (item instanceof ItemSlaughterCore && HxCTile.modifier <= 31) {
                    HxCTile.modifier = (HxCTile.modifier + 1);
                    if (!world.isRemote)
                        player.addChatMessage(new ChatComponentText("\u00A73Range was set to " + HxCTile.modifier));
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.decrStackSize(player.inventory.currentItem, 1);
                }
            } else if (tile instanceof TileVacuum && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                TileVacuum HxCTile = (TileVacuum) tile;
                if (item instanceof ItemVacuumCore && HxCTile.modifier <= 31) {
                    HxCTile.modifier = (HxCTile.modifier + 1);
                    if (!world.isRemote)
                        player.addChatMessage(new ChatComponentText("\u00A73Range was set to " + HxCTile.modifier));
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.decrStackSize(player.inventory.currentItem, 1);
                } else if (item instanceof ItemBinder) {
                    NBTTagCompound dat = stack.getTagCompound();
                    int[] pb = dat.getIntArray("PB");
                    int mode = dat.getInteger("Mode");
                    if (mode == 1) {
                        HxCTile.OtherPos = pb;
                        dat.setInteger("Mode", 0);
                        stack.setTagCompound(dat);
                    }
                }
            } else if (tile instanceof TileBarrier && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && item instanceof ItemSoulFragment) {
                TileBarrier HxCTile = (TileBarrier) tile;
                HxCTile.modifier = (HxCTile.modifier + 1);
                if (!world.isRemote)
                    player.addChatMessage(new ChatComponentText("\u00A73Range was set to " + HxCTile.modifier));
                if (!player.capabilities.isCreativeMode) player.inventory.decrStackSize(player.inventory.currentItem, 1);
            } else if (tile instanceof TileSpawnerAccelerator && item instanceof ItemMonsterPlacer) {
                TileSpawnerAccelerator HxCTile = (TileSpawnerAccelerator) tile;
                ItemMonsterPlacer egg = (ItemMonsterPlacer)item;
                int d = egg.getDamage(stack);
                switch (d){
                    case 50: HxCTile.Mob = "Creeper";
                        break;
                    case 51: HxCTile.Mob = "Skeleton";
                        break;
                    case 52: HxCTile.Mob = "Spider";
                        break;
                    case 54: HxCTile.Mob = "Zombie";
                        break;
                    case 55: HxCTile.Mob = "Slime";
                        break;
                    case 56: HxCTile.Mob = "Ghast";
                        break;
                    case 57: HxCTile.Mob = "PigZombie";
                        break;
                    case 58: HxCTile.Mob = "Enderman";
                        break;
                    case 59: HxCTile.Mob = "CaveSpider";
                        break;
                    case 60: HxCTile.Mob = "Silverfish";
                        break;
                    case 61: HxCTile.Mob = "Blaze";
                        break;
                    case 62: HxCTile.Mob = "LavaSlime";
                        break;
                    case 63: HxCTile.Mob = "EnderDragon";
                        break;
                    case 64: HxCTile.Mob = "WitherBoss";
                        break;
                    case 65: HxCTile.Mob = "Bat";
                        break;
                    case 66: HxCTile.Mob = "Witch";
                        break;
                    case 91: HxCTile.Mob = "Sheep";
                        break;
                    case 92: HxCTile.Mob = "Cow";
                        break;
                    case 93: HxCTile.Mob = "Chicken";
                        break;
                    case 94: HxCTile.Mob = "Squid";
                        break;
                    case 95: HxCTile.Mob = "Wolf";
                        break;
                    case 96: HxCTile.Mob = "Mooshroom";
                        break;
                    case 97: HxCTile.Mob = "SnowGolem";
                        break;
                    case 98: HxCTile.Mob = "Ozelot";
                        break;
                    case 99: HxCTile.Mob = "VillagerGolem";
                        break;
                    case 100: HxCTile.Mob = "Horse";
                        break;
                    case 120: HxCTile.Mob = "Villager";
                        break;
                    default: HxCTile.Mob = "Pig";
                        break;
                }

            }else if (tile instanceof IInventory && event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && item instanceof ItemBinder) {
                NBTTagCompound dat = stack.getTagCompound();
                int[] pb = dat.getIntArray("PB");
                int mode = dat.getInteger("Mode");
                if (mode == 0) {
                    dat.setIntArray("PB", new int[]{event.x, event.y, event.z});
                    dat.setInteger("Mode", 1);
                    stack.setTagCompound(dat);
                }
            }
        }
//    }
}
