package com.benonardo.skintextures.mixin;

import com.benonardo.skintextures.SkinTexturesUtil;
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
    private void useResourcePackSkinIfPresent(AbstractClientPlayerEntity abstractClientPlayerEntity, CallbackInfoReturnable<Identifier> cir) {
        var id = SkinTexturesUtil.getResourcePackSkin(abstractClientPlayerEntity.getGameProfile());
        if (id != null) {
            cir.setReturnValue(id);
        }
    }

}
