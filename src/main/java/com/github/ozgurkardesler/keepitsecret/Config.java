package com.github.ozgurkardesler.keepitsecret;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue HIDE_PLAYER_NAMES;
    public static final ModConfigSpec.BooleanValue HIDE_MOB_NAMES;

    static {
        BUILDER.push("Keep It Secret Config");

        HIDE_PLAYER_NAMES = BUILDER
                .comment("When enabled, player nametags will be hidden behind blocks")
                .define("hidePlayerNames", true);

        HIDE_MOB_NAMES = BUILDER
                .comment("When enabled, mob nametags will be hidden behind blocks")
                .define("hideMobNames", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}