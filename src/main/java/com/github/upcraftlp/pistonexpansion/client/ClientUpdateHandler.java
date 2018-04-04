package com.github.upcraftlp.pistonexpansion.client;

import com.github.upcraftlp.pistonexpansion.PistonExpansion;
import com.github.upcraftlp.pistonexpansion.util.UpdateChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PistonExpansion.MODID, value = {Side.CLIENT})
public class ClientUpdateHandler {

    @SubscribeEvent
    public static void showUpdateNotification(TickEvent.ClientTickEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null) {
            if (UpdateChecker.hasUpdate()) {
                ForgeVersion.CheckResult result = UpdateChecker.getResult();
                ITextComponent update = new TextComponentTranslation("message.aquatic.update", PistonExpansion.MODNAME, Loader.MC_VERSION);

                ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, result.url);
                HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(result.url).setStyle(new Style().setColor(TextFormatting.AQUA).setItalic(true)));
                ITextComponent keyword = new TextComponentTranslation("message.aquatic.update.download.keyword").setStyle(new Style().setColor(TextFormatting.BLUE).setClickEvent(clickEvent).setHoverEvent(hoverEvent));
                ITextComponent download = new TextComponentTranslation("message.aquatic.update.download", result.target, keyword, PistonExpansion.VERSION);

                player.sendMessage(update);
                player.sendMessage(download);
            }
            MinecraftForge.EVENT_BUS.unregister(ClientUpdateHandler.class);
        }
    }

}
