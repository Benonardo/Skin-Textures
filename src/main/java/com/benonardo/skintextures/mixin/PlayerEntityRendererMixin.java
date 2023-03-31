package com.benonardo.skintextures.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    private void addResourcepackSkin(AbstractClientPlayerEntity abstractClientPlayerEntity, CallbackInfoReturnable<Identifier> cir) {
        var nameId = new Identifier("skintextures", "textures/entity/player/" + abstractClientPlayerEntity.getGameProfile().getName().toLowerCase() + ".png");
        var uuidId = new Identifier("skintextures", "textures/entity/player/" + abstractClientPlayerEntity.getGameProfile().getId().toString().toLowerCase() + ".png");
        if (MinecraftClient.getInstance().getResourceManager().getResource(nameId).isPresent()) {
            cir.setReturnValue(nameId);
        } else if (MinecraftClient.getInstance().getResourceManager().getResource(uuidId).isPresent()) {
            cir.setReturnValue(uuidId);
        }
    }

}
