package com.github.ozgurkardesler.keepitsecret.net;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
        modid = "keepitsecret"
)
public final class NetworkRegister {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar =
                event.registrar("keepitsecret");

        registrar.playToClient(
                SyncNameRulesPayload.TYPE,
                SyncNameRulesPayload.CODEC,
                new IPayloadHandler<SyncNameRulesPayload>() {
                    @Override
                    public void handle(SyncNameRulesPayload syncNameRulesPayload, IPayloadContext iPayloadContext) {
                        ClientPayloadHandler.handle(syncNameRulesPayload, (ClientPayloadContext) iPayloadContext);
                    }
                }
        );
    }
}

