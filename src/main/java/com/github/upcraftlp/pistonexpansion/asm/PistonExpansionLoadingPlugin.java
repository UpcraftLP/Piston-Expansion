package com.github.upcraftlp.pistonexpansion.asm;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(Loader.MC_VERSION)
@IFMLLoadingPlugin.TransformerExclusions({"com.github.upcraftlp.pistonexpansion.asm"})
@IFMLLoadingPlugin.SortingIndex(9001)
public class PistonExpansionLoadingPlugin implements IFMLLoadingPlugin {

    private static Logger log = LogManager.getLogger("pistonexpansion-core");
    private static boolean runtimeDeobfuscation;

    public static boolean isDeobfuscatedEnvironment() {
        return !runtimeDeobfuscation;
    }

    public static Logger getLog() {
        return log;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"com.github.upcraftlp.pistonexpansion.asm.PistonExpansionTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        runtimeDeobfuscation = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
