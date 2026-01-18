package com.github.ozgurkardesler.keepitsecret.net;

import net.neoforged.neoforge.client.network.handling.ClientPayloadContext;

public final class ClientPayloadHandler {

    public static void handle(
            SyncNameRulesPayload payload,
            ClientPayloadContext ctx
    ) {
        ClientRuleCache.HIDE_MOB_NAMES = payload.hideMobs();
        ClientRuleCache.HIDE_PLAYER_NAMES = payload.hidePlayers();
    }
}

