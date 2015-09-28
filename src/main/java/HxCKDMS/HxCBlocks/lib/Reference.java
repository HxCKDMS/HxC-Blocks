package HxCKDMS.HxCBlocks.lib;

import java.util.Arrays;
import java.util.List;

public class Reference {
    public static final String MOD_ID = "HxCBlocks";
    public static final String MOD_NAME = "HxC-Blocks";
    public static final String VERSION = "1.7.0";
    public static final String SERVER_PROXY_LOCATION = "HxCKDMS.HxCBlocks.Proxy.ServerProxy";
    public static final String CLIENT_PROXY_LOCATION = "HxCKDMS.HxCBlocks.Proxy.ClientProxy";
    public static final String DEPENDENCIES = "required-after:HxCCore@[1.8.3,)";

    public static final String[] BLOCKS = new String[]{"Barrier", "PotionBrewer", "SlaughterBlock", "SpawnerAccelerator", "Vacuum", "XPAbsorber","GreyGoo", "LeBomb", "SoulExtractor"};
    public static final List<String> INGREDIENTS = Arrays.asList("nether_wart", "glowstone_dust", "redstone_dust", "sugar", "mushroom");
}
