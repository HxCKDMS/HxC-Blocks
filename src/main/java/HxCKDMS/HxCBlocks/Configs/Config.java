package HxCKDMS.HxCBlocks.Configs;

import net.minecraftforge.common.config.Configuration;

public class Config
{
    public static boolean GreyGoo;
    public static boolean SafetyChecks;
    public static int MaxRange;
    public Config(Configuration config)
    {
        config.load();

        GreyGoo = config.getBoolean("EnableGreyGoo", "Features", false, "enable to allow using grey goo. disable to remove it");
        SafetyChecks = config.getBoolean("EnableSafetyChecks", "Features", true, "enable safety checks for placing grey goo");

        MaxRange = config.getInt("MaxRange", "Features", 32, 1, 64, "max range of blocks");
        if(config.hasChanged())
        {
            config.save();
        }
    }
}
