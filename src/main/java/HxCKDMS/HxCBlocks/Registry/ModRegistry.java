package HxCKDMS.HxCBlocks.Registry;

import HxCKDMS.HxCBlocks.Blocks.HxCBlock;
import HxCKDMS.HxCBlocks.Events.EventBlockPlace;
import HxCKDMS.HxCBlocks.Events.EventEntityUpdate;
import HxCKDMS.HxCBlocks.Items.ItemBlockHxC;
import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class ModRegistry {
    //blocks
//    public static BlockSlaughterBlock SlaughterBlock = new BlockSlaughterBlock(Material.iron);
//    public static BlockXPAbsorber XPAbsorber = new BlockXPAbsorber(Material.iron);
//    public static BlockSoulExtractor SoulExtractor = new BlockSoulExtractor(Material.iron);
//    public static BlockVacuum Vacuum = new BlockVacuum(Material.iron);
//    public static BlockBarrier Barrier = new BlockBarrier(Material.iron);
//    public static BlockSpawnerAccelerator Accelerator = new BlockSpawnerAccelerator(Material.iron);
//    public static BlockGreyGoo GreyGoo = new BlockGreyGoo(Material.iron);
//    public static BlockLeBomb LeBomb = new BlockLeBomb(Material.iron);
    //tab
    public static CreativeTabs HxCBlocks = CreativeTabHxCBlocks.tabHxCBlocks;
    public static HxCBlock hxCBlock = new HxCBlock();
    //items
//    public static ItemSoulFragment SoulFragment = new ItemSoulFragment();
//    public static ItemSoulBinder SoulBinder = new ItemSoulBinder();
//    public static ItemVacuumCore VacuumCore = new ItemVacuumCore();
//    public static ItemSlaughterCore SlaughterCore = new ItemSlaughterCore();
//    public static ItemBinder Binder = new ItemBinder();
//    public static ItemWitherCore WitherCore = new ItemWitherCore();
//    public static ItemHxCWrench HxCWrench = new ItemHxCWrench();
//    public static ItemHxCWrenchPlaceHolder HxCWrenchPlaceHolder = new ItemHxCWrenchPlaceHolder();
//    public static ItemMetBlue IMetBlue = new ItemMetBlue();

    public static void preInit(){
        registerBlocks();
        registerItems();
        registerTileEntities();
        registerCraftingRecipes();
    }

    public static void init(){
        MinecraftForge.EVENT_BUS.register(new EventEntityUpdate());
        MinecraftForge.EVENT_BUS.register(new EventBlockPlace());
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(hxCBlock, ItemBlockHxC.class, "blockHxC");
    }

    private static void registerItems() {
//        GameRegistry.registerItem(SoulFragment, "SoulFragment");
//        GameRegistry.registerItem(SoulBinder, "SoulBinder");
//        GameRegistry.registerItem(VacuumCore, "VacuumCore");
//        GameRegistry.registerItem(SlaughterCore, "SlaughterCore");
//        GameRegistry.registerItem(WitherCore, "WitherCore");
//        GameRegistry.registerItem(Binder, "Binder");
//        GameRegistry.registerItem(HxCWrench, "HxCWrench");
//        GameRegistry.registerItem(HxCWrenchPlaceHolder, "HxCWrenchPlaceHolder");
//        GameRegistry.registerItem(IMetBlue, "MetBlue");
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(HxCTile.class, "tileHxC");
    }

    private static void registerCraftingRecipes() {
//        GameRegistry.addRecipe(new ItemStack(SoulBinder), " i ", "ifi", " i ", 'i', Items.iron_ingot, 'f', SoulFragment);
//        GameRegistry.addRecipe(new ItemStack(WitherCore), "isi", "sws", "isi", 'i', Items.iron_ingot, 'w', Items.nether_star, 's', new ItemStack(Items.skull, 1, 1));
//        GameRegistry.addRecipe(new ItemStack(SlaughterCore), "idi", "dsd", "idi", 'i', Items.iron_ingot, 'd', Items.diamond_sword, 's', Items.nether_star);
//        GameRegistry.addRecipe(new ItemStack(VacuumCore), "ipi", "php", "ipi", 'i', Items.iron_ingot, 'h', Blocks.hopper, 'p', Items.ender_pearl);
//        GameRegistry.addRecipe(new ItemStack(Binder), " i ", "isi", " i ", 'i', Items.iron_ingot, 's', Items.sign);
//        GameRegistry.addRecipe(new ItemStack(HxCWrench), "did", " s ", " i ", 'i', Items.iron_ingot, 'd', Items.diamond, 's', Items.nether_star);
//        GameRegistry.addRecipe(new ItemStack(SoulExtractor), "o o", "ewe", "o o", 'e', Items.ender_eye, 'w', WitherCore, 'o', Blocks.obsidian);
//        GameRegistry.addRecipe(new ItemStack(XPAbsorber), "oho", "ede", "obo", 'e', Items.ender_eye, 'd', Items.diamond, 'o', Blocks.obsidian, 'b', Items.bucket, 'h', Blocks.hopper);
//        GameRegistry.addRecipe(new ItemStack(SlaughterBlock), "o o", "sps", "o o", 's', Items.diamond_sword, 'p', Blocks.piston, 'o', Blocks.obsidian);
//        GameRegistry.addRecipe(new ItemStack(Vacuum), "oho", "eve", "oho", 'h', Blocks.hopper, 'v', VacuumCore, 'o', Blocks.obsidian, 'e', Items.ender_eye);
//        GameRegistry.addRecipe(new ItemStack(Barrier), "oeo", "bbb", "opo", 'b', Blocks.iron_bars, 'o', Blocks.obsidian, 'e', Items.ender_eye, 'p', Items.ender_pearl);
//        GameRegistry.addRecipe(new ItemStack(IMetBlue), "fkl", 'f', Items.sugar, 'k', new ItemStack(Items.dye, 1, 4), 'l', new ItemStack(Items.potionitem,1,0)); // TODO: MAKE SHAPELESS
    }
}
