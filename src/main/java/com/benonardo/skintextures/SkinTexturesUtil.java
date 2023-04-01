package com.benonardo.skintextures;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class SkinTexturesUtil {

    private SkinTexturesUtil() {}

    @Nullable
    public static Identifier getResourcePackSkin(GameProfile profile) {
        var nameId = profile.getName() == null ? null : new Identifier("skintextures", "textures/entity/player/" + profile.getName().toLowerCase() + ".png");
        var uuidId = profile.getId() == null ? null : new Identifier("skintextures", "textures/entity/player/" + profile.getId().toString().toLowerCase() + ".png");
        if (nameId != null && MinecraftClient.getInstance().getResourceManager().getResource(nameId).isPresent()) {
            return nameId;
        } else if (uuidId != null && MinecraftClient.getInstance().getResourceManager().getResource(uuidId).isPresent()) {
            return uuidId;
        }
        return null;
    }

}
