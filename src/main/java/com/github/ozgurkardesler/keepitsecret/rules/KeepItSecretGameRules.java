package com.github.ozgurkardesler.keepitsecret.rules;


import net.minecraft.world.level.GameRules;


public class KeepItSecretGameRules {


    public static final GameRules.Key<GameRules.BooleanValue> PLAYERS =
            GameRules.register(
                    "keepItSecretPlayers",
                    GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(true)
            );


    public static final GameRules.Key<GameRules.BooleanValue> MOBS =
            GameRules.register(
                    "keepItSecretMobs",
                    GameRules.Category.MOBS,
                    GameRules.BooleanValue.create(true)
            );

    public static void init(){
    }
}