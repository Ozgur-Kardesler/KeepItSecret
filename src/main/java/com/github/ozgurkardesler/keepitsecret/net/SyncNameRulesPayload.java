package com.github.ozgurkardesler.keepitsecret.net;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SyncNameRulesPayload(
        boolean hideMobs,
        boolean hidePlayers
) implements CustomPacketPayload {

    public static final Type<SyncNameRulesPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("keepitsecret", "sync_name_rules"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncNameRulesPayload> CODEC =
            StreamCodec.of(
                    (buf, msg) -> {
                        buf.writeBoolean(msg.hideMobs);
                        buf.writeBoolean(msg.hidePlayers);
                    },
                    buf -> new SyncNameRulesPayload(
                            buf.readBoolean(),
                            buf.readBoolean()
                    )
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}



