package HxCKDMS.HxCBlocks.Configs;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static boolean GreyGoo;
    public static boolean LeBomb;
    public static boolean SafetyChecks;
    public static int MaxRange;
    public static int MaxLeBombRange;
    public static int LeBombDelay;
    public static double LeBombAccuracy;
    public Config(Configuration config)
    {
        config.load();

        GreyGoo = config.getBoolean("EnableGreyGoo", "Features", false, "enable to allow using grey goo. disable to remove it");
        LeBomb = config.getBoolean("EnableLeBomb", "Features", false, "I wouldn't enable if I were you.");
        SafetyChecks = config.getBoolean("EnableSafetyChecks", "Features", true, "enable safety checks for placing grey goo");

        MaxRange = config.getInt("MaxRange", "Features", 32, 1, 64, "max range of blocks");
        MaxLeBombRange = config.getInt("MaxBombRange", "Features", 256, 4, 8192, "max range of BOOM");
        LeBombDelay = config.getInt("LeBombDelay", "Features", 160, 4, 16348, "LeBombDelay");
        LeBombAccuracy = config.getFloat("LeBombAccuracy", "Features", 0.05f, 0.01f, 1, "Lower = Better Sphere");
        if(config.hasChanged())
        {
            config.save();
        }
    }
}
