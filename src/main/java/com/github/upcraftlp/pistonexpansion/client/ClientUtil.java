package com.github.upcraftlp.pistonexpansion.client;

import com.github.upcraftlp.pistonexpansion.PistonExpansion;
import com.google.common.base.Preconditions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.image.BufferedImage;
import java.net.URI;

@SideOnly(Side.CLIENT)
public class ClientUtil {

    @SuppressWarnings("unchecked")
    public static void openLink(String url) {
        try {
            URI uri = new URI(url);
            Class oclass = Class.forName("java.awt.Desktop");
            boolean flag = (Boolean) oclass.getMethod("isDesktopSupported").invoke(null);
            if (!flag) throw new UnsupportedOperationException("no desktop supported!");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, uri);
        } catch (Throwable throwable1) {
            Throwable throwable = throwable1.getCause();
            PistonExpansion.getLogger().error("Couldn\'t open link: {}", new Object[]{throwable == null ? (StringUtils.isNullOrEmpty(url) ? "<UNKNOWN>" : url) : throwable.getMessage()});
        }
    }

    public static ResourceLocation loadTexture(ResourceLocation location, int[] data) {
        Preconditions.checkArgument(data.length == 2, "Data pointer must have an array size of 2");
        ResourceLocation dest;
        try {
            BufferedImage img = TextureUtil.readBufferedImage(ClientUtil.class.getResourceAsStream("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath()));
            data[0] = img.getWidth();
            data[1] = img.getHeight();
            dest = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(location.getResourceDomain(), new DynamicTexture(img));
        } catch (Exception e) {
            dest = TextureMap.LOCATION_MISSING_TEXTURE;
            PistonExpansion.getLogger().warn("Image not found:" + location);
        }
        return dest;
    }

    public static KeyBinding getKeyBinding(String name, int keyCode) {
        return new KeyBinding("key.aquatic." + name, keyCode, "key.category.aquatic");
    }
}
