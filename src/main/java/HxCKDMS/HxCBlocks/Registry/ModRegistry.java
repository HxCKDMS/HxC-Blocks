package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCBlocks.Blocks.BlockSlaughterBlock;
import HxCKDMS.HxCBlocks.Blocks.BlockSoulExtractor;
import HxCKDMS.HxCBlocks.Blocks.BlockXPAbsorber;
import HxCKDMS.HxCBlocks.Events.EventBlockInteract;
import HxCKDMS.HxCBlocks.Events.EventEntityUpdate;
import HxCKDMS.HxCBlocks.Items.ItemSoulBinder;
import HxCKDMS.HxCBlocks.Items.ItemSoulFragment;
import HxCKDMS.HxCBlocks.TileEntities.TileSlaughterBlock;
import HxCKDMS.HxCBlocks.TileEntities.TileXPAbsorber;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModRegistry {
    //blocks
    public static BlockSlaughterBlock SlaughterBlock = new BlockSlaughterBlock(Material.iron);
    public static BlockXPAbsorber XPAbsorber = new BlockXPAbsorber(Material.iron);
    public static BlockSoulExtractor SoulExtractor = new BlockSoulExtractor(Material.iron);
    //tab
    public static CreativeTabs HxCBlocks = CreativeTabHxCBlocks.tabHxCBlocks;
    //items
    public static ItemSoulFragment SoulFragment = new ItemSoulFragment();
    public static ItemSoulBinder SoulBinder = new ItemSoulBinder();

    public static void preInit(){
        registerBlocks();
        registerItems();
        registerTileEntities();
        registerCraftingRecipes();
    }

    public static void init(){
        MinecraftForge.EVENT_BUS.register(new EventBlockInteract());
        MinecraftForge.EVENT_BUS.register(new EventEntityUpdate());
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(SlaughterBlock, "SlaughterBlock");
        GameRegistry.registerBlock(XPAbsorber, "XPAbsorber");
        GameRegistry.registerBlock(SoulExtractor, "SoulExtractor");
    }

    private static void registerItems() {
        GameRegistry.registerItem(SoulFragment, "SoulFragment");
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileSlaughterBlock.class, "TileSlaughterBlock");
        GameRegistry.registerTileEntity(TileXPAbsorber.class, "TileSlaughterBlock");
    }

    private static void registerCraftingRecipes() {
        GameRegistry.addRecipe(new ItemStack(SoulBinder), " i ", "ifi", " i ", 'i', Items.iron_ingot, 'f', SoulFragment);
        GameRegistry.addRecipe(new ItemStack(SoulFragment), SoulFragment);
        GameRegistry.addRecipe(new ItemStack(SoulExtractor), "o o", "ese", "o o", 'e', Items.ender_eye, 's', Items.nether_star, 'o', Blocks.obsidian);
    }
}
