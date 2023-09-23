package com.benonardo.skintextures;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class SkinTexturesUtil {

    private SkinTexturesUtil() {}

    public static String getLowerCaseHash(GameProfile profile) {
        return Integer.toHexString(MinecraftClient.getInstance().getSkinProvider().getSkinTextures(profile).texture().hashCode());
    }

    @Nullable
    public static Identifier getResourcePackSkin(GameProfile profile) {
        return getResourcePackPlayerTexture("/", profile);
    }

    @Nullable
    public static Identifier getResourcePackCape(GameProfile profile) {
        return getResourcePackPlayerTexture("/cape/", profile);
    }

    @Nullable
    public static Identifier getResourcePackElytra(GameProfile profile) {
        return getResourcePackPlayerTexture("/elytra/", profile);
    }

    @Nullable
    private static Identifier getResourcePackPlayerTexture(String path, GameProfile profile) {
        try {
            var uuidId = new Identifier("skintextures", "textures/entity/player" + path + profile.getId().toString().toLowerCase() + ".png");
            if (MinecraftClient.getInstance().getResourceManager().getResource(uuidId).isPresent()) {
                return uuidId;
            } else {
                var nameId = new Identifier("skintextures", "textures/entity/player" + path + profile.getName().toLowerCase() + ".png");
                if (MinecraftClient.getInstance().getResourceManager().getResource(nameId).isPresent()) {
                    return nameId;
                } else {
                    var hashId = new Identifier("skintextures", "textures/entity/player" + path + getLowerCaseHash(profile) + ".png");
                    if (MinecraftClient.getInstance().getResourceManager().getResource(hashId).isPresent()) {
                        return hashId;
                    }
                }
            }
        } catch (Exception ignored) {

        }
        return null;
    }

}
