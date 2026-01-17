package com.github.ozgurkardesler.keepitsecret.mixin;

import com.github.ozgurkardesler.keepitsecret.Config;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {

    private boolean keepitsecret$depthTestWasEnabled = false;
    private int keepitsecret$previousDepthFunc = GL11.GL_LESS;

    @Inject(method = "renderNameTag", at = @At("HEAD"))
    private void keepitsecret$enableDepthForNameTag(
            T entity,
            Component text,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            CallbackInfo ci
    ) {
        boolean shouldEnableDepth = entity instanceof Player
                ? Config.HIDE_PLAYER_NAMES.get()
                : Config.HIDE_MOB_NAMES.get();

        if (shouldEnableDepth) {
            keepitsecret$depthTestWasEnabled = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
            keepitsecret$previousDepthFunc = GL11.glGetInteger(GL11.GL_DEPTH_FUNC);

            // enablşng depth testing
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            // LEQUAL
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            // enable writing to the depth buffer
            GL11.glDepthMask(true);
        }
    }

    @Inject(method = "renderNameTag", at = @At("RETURN"))
    private void keepitsecret$restoreDepthAfterNameTag(
            T entity,
            Component text,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            CallbackInfo ci
    ) {
        boolean shouldEnableDepth = entity instanceof Player
                ? Config.HIDE_PLAYER_NAMES.get()
                : Config.HIDE_MOB_NAMES.get();

        if (shouldEnableDepth) {
            if (keepitsecret$depthTestWasEnabled) {
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            } else {
                GL11.glDisable(GL11.GL_DEPTH_TEST);
            }

            // restore the original depth function
            GL11.glDepthFunc(keepitsecret$previousDepthFunc);
        }
    }
}