package HxCKDMS.HxCBlocks.Configs;

import HxCKDMS.HxCCore.api.Configuration.Config;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class Configurations {
    @Config.Boolean
    public static boolean GreyGoo, LeBomb, SafetyChecks = true;

    @Config.Integer
    public static int MaxBlockRange = 32, MaxLeBombRange = 64, LeBombDelay = 160;

    @Config.Double(description = "0.001 minimum max 1, Decreasing this increases acuracy of sphere creation and lag")
    public static double LeBombAccuracy = 0.05;

    @Config.List(description = "Valid weapons to be using in SlaughterBlock this is only weapons that aren't already able to be inserted.")
    public static List<String> Validweapons = Arrays.asList("draconicDistructionStaff");
}
