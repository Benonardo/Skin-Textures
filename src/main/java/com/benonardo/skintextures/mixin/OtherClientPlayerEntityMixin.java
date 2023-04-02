package com.benonardo.skintextures.mixin;

import com.benonardo.skintextures.SkinTexturesUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

// Priority higher = inject at the head of everything else
@Mixin(value = OtherClientPlayerEntity.class, priority = 2000)
public class OtherClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public OtherClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!player.isSneaking()) return ActionResult.PASS;
        MinecraftClient.getInstance().keyboard.setClipboard(SkinTexturesUtil.getLowerCaseHash(this.getGameProfile()));
        player.sendMessage(Text.literal("Copied hash to clipboard"), true);
        return ActionResult.SUCCESS;
    }
}
