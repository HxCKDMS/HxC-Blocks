package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCBlocks.Blocks.*;
import HxCKDMS.HxCBlocks.Items.*;
import HxCKDMS.HxCBlocks.TileEntities.*;
import HxCKDMS.HxCBlocks.Events.EventBlockInteract;
import HxCKDMS.HxCBlocks.Events.EventEntityUpdate;
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
    public static BlockVacuum Vacuum = new BlockVacuum(Material.iron);
    public static BlockBarrier Barrier = new BlockBarrier(Material.iron);
    public static BlockSpawnerAccelerator Accelerator = new BlockSpawnerAccelerator(Material.iron);
    //tab
    public static CreativeTabs HxCBlocks = CreativeTabHxCBlocks.tabHxCBlocks;
    //items
    public static ItemSoulFragment SoulFragment = new ItemSoulFragment();
    public static ItemSoulBinder SoulBinder = new ItemSoulBinder();
    public static ItemVacuumCore VacuumCore = new ItemVacuumCore();
    public static ItemSlaughterCore SlaughterCore = new ItemSlaughterCore();
    public static ItemBinder Binder = new ItemBinder();
    public static ItemWitherCore WitherCore = new ItemWitherCore();

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
        GameRegistry.registerBlock(Vacuum, "Vacuum");
        GameRegistry.registerBlock(Barrier, "Barrier");
        GameRegistry.registerBlock(Accelerator, "Accelerator");
    }

    private static void registerItems() {
        GameRegistry.registerItem(SoulFragment, "SoulFragment");
        GameRegistry.registerItem(SoulBinder, "SoulBinder");
        GameRegistry.registerItem(VacuumCore, "VacuumCore");
        GameRegistry.registerItem(SlaughterCore, "SlaughterCore");
        GameRegistry.registerItem(WitherCore, "WitherCore");
        GameRegistry.registerItem(Binder, "Binder");
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileSlaughterBlock.class, "TileSlaughterBlock");
        GameRegistry.registerTileEntity(TileXPAbsorber.class, "TileXPAbsorber");
        GameRegistry.registerTileEntity(TileVacuum.class, "TileVacuum");
        GameRegistry.registerTileEntity(TileBarrier.class, "TileBarrier");
        GameRegistry.registerTileEntity(TileSpawnerAccelerator.class, "TileSpawnerAccelerator");
    }

    private static void registerCraftingRecipes() {
        GameRegistry.addRecipe(new ItemStack(SoulBinder), " i ", "ifi", " i ", 'i', Items.iron_ingot, 'f', SoulFragment);
        GameRegistry.addRecipe(new ItemStack(WitherCore), "isi", "sws", "isi", 'i', Items.iron_ingot, 'w', Items.nether_star, 's', new ItemStack(Items.skull, 1, 1));
        GameRegistry.addRecipe(new ItemStack(SlaughterCore), "idi", "dsd", "idi", 'i', Items.iron_ingot, 'd', Items.diamond_sword, 's', Items.nether_star);
        GameRegistry.addRecipe(new ItemStack(VacuumCore), "ipi", "php", "ipi", 'i', Items.iron_ingot, 'h', Blocks.hopper, 'p', Items.ender_pearl);
        GameRegistry.addRecipe(new ItemStack(Binder), " i ", "isi", " i ", 'i', Items.iron_ingot, 's', Items.sign);
        GameRegistry.addShapelessRecipe(new ItemStack(SoulFragment), SoulFragment);
        GameRegistry.addShapelessRecipe(new ItemStack(SoulBinder), SoulBinder);
        GameRegistry.addShapelessRecipe(new ItemStack(Binder), Binder);
        GameRegistry.addRecipe(new ItemStack(SoulExtractor), "o o", "ewe", "o o", 'e', Items.ender_eye, 'w', WitherCore, 'o', Blocks.obsidian);
        GameRegistry.addRecipe(new ItemStack(XPAbsorber), "oho", "ede", "obo", 'e', Items.ender_eye, 'd', Items.diamond, 'o', Blocks.obsidian, 'b', Items.bucket, 'h', Blocks.hopper);
        GameRegistry.addRecipe(new ItemStack(SlaughterBlock), "o o", "sps", "o o", 's', Items.diamond_sword, 'p', Blocks.piston, 'o', Blocks.obsidian);
        GameRegistry.addRecipe(new ItemStack(Vacuum), "oho", "eve", "oho", 'h', Blocks.hopper, 'v', VacuumCore, 'o', Blocks.obsidian, 'e', Items.ender_eye);
        GameRegistry.addRecipe(new ItemStack(Barrier), "oeo", "bbb", "opo", 'b', Blocks.iron_bars, 'o', Blocks.obsidian, 'e', Items.ender_eye, 'p', Items.ender_pearl);
    }
}
