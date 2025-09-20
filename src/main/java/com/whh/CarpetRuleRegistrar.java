package com.whh;

import carpet.CarpetServer;
import com.whh.feature.AutoNightVision;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class CarpetRuleRegistrar {
    public static void register() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetWhhSettings.class);
        System.out.println("Rules from CarpetWhhSettings have been registered.");

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {

            if (entity instanceof ServerPlayerEntity) {
                AutoNightVision.checkAndApplyNightVision();
            }
        });
    }
}
