package com.github.ozgurkardesler.keepitsecret.mixin;

import com.github.ozgurkardesler.keepitsecret.KeepItSecret;
import com.github.ozgurkardesler.keepitsecret.net.SyncNameRulesPayload;
import com.github.ozgurkardesler.keepitsecret.rules.KeepItSecretGameRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class GameRule_Mixin {
    @Shadow
    public abstract GameRules getGameRules();

    @Shadow
    public abstract PlayerList getPlayerList();

    @Inject(method = "onGameRuleChanged",
    at = @At("HEAD")
    )
    void keepitsecret$gamerule(String gameRule, GameRules.Value<?> value, CallbackInfo ci){
        if(gameRule.equals(KeepItSecretGameRules.MOBS.getId()) ||  gameRule.equals(KeepItSecretGameRules.PLAYERS.getId())){
            boolean hide = getGameRules()
                    .getBoolean(KeepItSecretGameRules.MOBS);
            boolean hide2 = getGameRules()
                    .getBoolean(KeepItSecretGameRules.PLAYERS);

            for (ServerPlayer player : getPlayerList().getPlayers()) {
                player.connection.send(
                        new SyncNameRulesPayload(hide, hide2)
                );
            }
        }
    }
}
