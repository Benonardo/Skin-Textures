package com.benonardo.skintextures;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class SkinTexturesUtil {

    private SkinTexturesUtil() {}

    @Nullable
    public static Identifier getResourcePackSkin(GameProfile profile) {
        try {
            var nameId = new Identifier("skintextures", "textures/entity/player/" + profile.getName().toLowerCase() + ".png");
            var uuidId = new Identifier("skintextures", "textures/entity/player/" + profile.getId().toString().toLowerCase() + ".png");
            if (MinecraftClient.getInstance().getResourceManager().getResource(nameId).isPresent()) {
                return nameId;
            } else if (MinecraftClient.getInstance().getResourceManager().getResource(uuidId).isPresent()) {
                return uuidId;
            }
        } catch (Exception ignored) {

        }
        return null;
    }

}
