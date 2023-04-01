package com.benonardo.skintextures;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class SkinTexturesUtil {

    private SkinTexturesUtil() {}

    public static String getLowerCaseHash(GameProfile profile) {
        return MinecraftClient.getInstance().getSkinProvider().getTextures(profile).get(MinecraftProfileTexture.Type.SKIN).getHash();
    }

    @Nullable
    public static Identifier getResourcePackSkin(GameProfile profile) {
        try {
            var uuidId = new Identifier("skintextures", "textures/entity/player/" + profile.getId().toString().toLowerCase() + ".png");
            if (MinecraftClient.getInstance().getResourceManager().getResource(uuidId).isPresent()) {
                return uuidId;
            } else {
                var nameId = new Identifier("skintextures", "textures/entity/player/" + profile.getName().toLowerCase() + ".png");
                if (MinecraftClient.getInstance().getResourceManager().getResource(nameId).isPresent()) {
                    return nameId;
                } else {
                    var hashId = new Identifier("skintextures", "textures/entity/player/" + getLowerCaseHash(profile) + ".png");
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
