package com.benonardo.skintextures.mixin;

import com.benonardo.skintextures.SkinTexturesUtil;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Priority higher = replace textures after everything with default priority
@Mixin(value = AbstractClientPlayerEntity.class, priority = 2000)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @ModifyReturnValue(method = "getSkinTextures", at = @At("TAIL"))
    protected SkinTextures useResourcePackSkinTexturesIfPresent(SkinTextures original) {
        var skin = SkinTexturesUtil.getResourcePackSkin(this.getGameProfile());
        var cape = SkinTexturesUtil.getResourcePackCape(this.getGameProfile());
        var elytra = SkinTexturesUtil.getResourcePackElytra(this.getGameProfile());
        if (skin == null && cape == null && elytra == null)
            return original;

        return new SkinTextures(
                skin == null ? original.texture() : skin,
                null,
                cape == null ? original.capeTexture() : cape,
                elytra == null ? original.elytraTexture() : elytra,
                original.model(),
                true
        );
    }

}
