package com.github.ozgurkardesler.keepitsecret.mixin;

import com.github.ozgurkardesler.keepitsecret.Config;
import com.github.ozgurkardesler.keepitsecret.net.ClientRuleCache;
import com.github.ozgurkardesler.keepitsecret.other.Reference;
import com.github.ozgurkardesler.keepitsecret.rules.KeepItSecretGameRules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(AvatarRenderer.class)
public class PlayerNameTagRendering_Mixin {
    @ModifyArg(
            method = "submitNameTag(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitNameTag(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/phys/Vec3;ILnet/minecraft/network/chat/Component;ZIDLnet/minecraft/client/renderer/state/CameraRenderState;)V"
            ),
            index = 4,
            order = 1
    )
    private boolean keepitsecret$overrideDiscrete_ST(boolean par5) {
        boolean hide = ClientRuleCache.HIDE_PLAYER_NAMES;
        return !(hide && Config.HIDE_PLAYER_NAMES.getAsBoolean());
    }
}
