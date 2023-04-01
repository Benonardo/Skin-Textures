package com.benonardo.skintextures.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.enjarai.showmeyourskin.client.cursed.DummyClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(DummyClientPlayerEntity.class)
public abstract class DummyClientPlayerEntityMixin extends AbstractClientPlayerEntityMixin {

    public DummyClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    @Inject(method = "getSkinTexture", at = @At("HEAD"), cancellable = true)
    protected void useResourcePackSkinIfPresent(CallbackInfoReturnable<Identifier> cir) {
        super.useResourcePackSkinIfPresent(cir);
    }

}
