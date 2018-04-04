package com.github.upcraftlp.pistonexpansion.config;

import com.github.upcraftlp.pistonexpansion.PistonExpansion;
import net.minecraftforge.common.config.Config;

@Config(modid = PistonExpansion.MODID, name = "craftdevmods/piston-expansion")
@Config.LangKey("config.pistonexpansion.title")
public class PistonExpansionConfig {

    @Config.RequiresMcRestart
    @Config.Name("Enable Update Checker")
    @Config.Comment({"whether to announce mod updates", "Default: true"})
    public static boolean enableUpdateChecker = true;

    @Config.RequiresMcRestart
    @Config.Name("Announce Beta Updates")
    @Config.Comment({"enable to also show beta updates", "Default: false"})
    public static boolean announceBetaUpdates = false;

    @Config.RangeInt(min = 0, max = 10000)
    @Config.Name("Default Piston Push Limit")
    @Config.Comment({"default value of the pistonPushLimit game rule", "Default: 12"})
    public static int defaultPistonPushLimit = 12;

    @Config.RangeInt(min = 0, max = 10000)
    @Config.Name("Maximum Piston Push Limit")
    @Config.Comment({"maximum number of blocks that can be pushed at once, setting the game rule higher will have no effect.", "Default: 1000"})
    public static int maxPistonPushLimit = 1000;

    @Config.Name("Sticky Pistons Ignore Game Rule")
    @Config.Comment({"if true, sticky pistons will ignore the pistonPushLimit gamerule. disabling this will lead to unexpected/buggy behaviour. You have been warned.", "Default: true"})
    public static boolean ignoreSticky = true;
}
