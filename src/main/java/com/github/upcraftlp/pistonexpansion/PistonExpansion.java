package com.github.upcraftlp.pistonexpansion;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        name = PistonExpansion.MODNAME,
        version = PistonExpansion.VERSION,
        modid = PistonExpansion.MODID,
        certificateFingerprint = PistonExpansion.FINGERPRINT_KEY,
        dependencies = PistonExpansion.DEPENDENCIES,
        acceptedMinecraftVersions = PistonExpansion.MINECRAFT_VERSION,
        updateJSON = PistonExpansion.UPDATE_JSON
)
public class PistonExpansion {

    public static final String MODNAME = "Piston Expansion";
    public static final String MODID = "pistonexpansion";
    public static final String VERSION = "@VERSION@";
    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";
    public static final String MINECRAFT_VERSION = Loader.MC_VERSION;
    public static final String DEPENDENCIES = ""; // none for now
    public static final String UPDATE_JSON = "@UPDATEJSON@";

    private static Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

}
