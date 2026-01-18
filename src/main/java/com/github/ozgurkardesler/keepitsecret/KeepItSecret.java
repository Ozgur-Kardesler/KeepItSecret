package com.github.ozgurkardesler.keepitsecret;

import com.github.ozgurkardesler.keepitsecret.other.Reference;
import com.github.ozgurkardesler.keepitsecret.rules.KeepItSecretGameRules;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.CommandEvent;

@Mod(Reference.MOD_ID)
public class KeepItSecret {
    static{
        KeepItSecretGameRules.init();
    }
    public KeepItSecret(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }
}