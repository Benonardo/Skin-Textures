package com.benonardo.skintextures.mixin;

import com.benonardo.skintextures.SkinTexturesUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Priority higher = inject at the head of everything else
@Mixin(value = AbstractClientPlayerEntity.class, priority = 2000)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getSkinTexture", at = @At("HEAD"), cancellable = true)
    protected void useResourcePackSkinIfPresent(CallbackInfoReturnable<Identifier> cir) {
        var id = SkinTexturesUtil.getResourcePackSkin(this.getGameProfile());
        if (id != null) {
            cir.setReturnValue(id);
        }
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    protected void useResourcePackCapeIfPresent(CallbackInfoReturnable<Identifier> cir) {
        var id = SkinTexturesUtil.getResourcePackCape(this.getGameProfile());
        if (id != null) {
            cir.setReturnValue(id);
        }
    }

    @Inject(method = "getElytraTexture", at = @At("HEAD"), cancellable = true)
    protected void useResourcePackElytraIfPresent(CallbackInfoReturnable<Identifier> cir) {
        var id = SkinTexturesUtil.getResourcePackElytra(this.getGameProfile());
        if (id != null) {
            cir.setReturnValue(id);
        }
    }

}
