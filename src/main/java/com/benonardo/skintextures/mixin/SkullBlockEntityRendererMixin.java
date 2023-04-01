package com.benonardo.skintextures.mixin;

import com.benonardo.skintextures.SkinTexturesUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkullBlockEntityRenderer.class)
public class SkullBlockEntityRendererMixin {

    @Inject(method = "getRenderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getInstance()Lnet/minecraft/client/MinecraftClient;"), cancellable = true)
    private static void useResourcePackSkinIfPresent(SkullBlock.SkullType type, GameProfile profile, CallbackInfoReturnable<RenderLayer> cir) {
        var id = SkinTexturesUtil.getResourcePackSkin(profile);
        if (id != null) {
            cir.setReturnValue(RenderLayer.getEntityTranslucent(id));
        }
    }

}
