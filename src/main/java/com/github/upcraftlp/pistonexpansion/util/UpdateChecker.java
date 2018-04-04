package com.github.upcraftlp.pistonexpansion.util;

import com.github.upcraftlp.pistonexpansion.PistonExpansion;
import com.github.upcraftlp.pistonexpansion.config.PistonExpansionConfig;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class UpdateChecker {

    public static boolean hasUpdate() {
        if (PistonExpansionConfig.enableUpdateChecker) {
            ForgeVersion.CheckResult result = getResult();
            ForgeVersion.Status status = result.status;
            if (status != ForgeVersion.Status.PENDING && status != ForgeVersion.Status.FAILED) {
                return status == ForgeVersion.Status.OUTDATED || (PistonExpansionConfig.announceBetaUpdates && status == ForgeVersion.Status.BETA_OUTDATED);
            }
            PistonExpansion.getLogger().warn("Error getting update status for {}, found status {}!", PistonExpansion.MODID, status.toString());
        }
        return false;
    }

    public static ForgeVersion.CheckResult getResult() {
        return ForgeVersion.getResult(FMLCommonHandler.instance().findContainerFor(PistonExpansion.MODID));
    }

    public static void notifyServer() {
        if (hasUpdate()) {
            ForgeVersion.CheckResult result = getResult();
            PistonExpansion.getLogger().warn("There's an update available for {}, download version {} here: {}", PistonExpansion.MODNAME, result.target, result.url);
        }
    }
}
