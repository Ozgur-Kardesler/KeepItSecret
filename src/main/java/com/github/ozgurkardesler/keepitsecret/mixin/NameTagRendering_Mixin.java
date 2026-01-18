package com.github.ozgurkardesler.keepitsecret.mixin;

import com.github.ozgurkardesler.keepitsecret.Config;
import com.github.ozgurkardesler.keepitsecret.net.ClientRuleCache;
import com.github.ozgurkardesler.keepitsecret.other.Reference;
import com.github.ozgurkardesler.keepitsecret.rules.KeepItSecretGameRules;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;


@Mixin(EntityRenderer.class)
public class NameTagRendering_Mixin<T extends Entity, S extends EntityRenderState> {
    // you can use this var for instanceof checks in override.
    T keepitsecret$entity;

    @Inject(
            method = "shouldRender",
            at = @At("HEAD")
    )
    private void keepitsecret$captureEntity(
            T livingEntity, Frustum camera, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> cir
    ) {
        this.keepitsecret$entity = livingEntity;
    }

    @ModifyArg(
            method = "submitNameTag",
            at = @At(
            value = "INVOKE",
                   target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitNameTag(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/phys/Vec3;ILnet/minecraft/network/chat/Component;ZIDLnet/minecraft/client/renderer/state/CameraRenderState;)V"
            ),
           index = 4,
           order = 0
   )

   private boolean keepitsecret$overrideDiscrete_ST(boolean par5) {
        if(keepitsecret$entity instanceof Mob){
            boolean hide = ClientRuleCache.HIDE_MOB_NAMES;
            return !(hide && Config.HIDE_MOB_NAMES.getAsBoolean());
        }
        return true;
    }

}
