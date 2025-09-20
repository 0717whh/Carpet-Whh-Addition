package com.whh.feature;

import carpet.CarpetServer;

import com.whh.CarpetWhhSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.*;
import net.minecraft.server.network.ServerPlayerEntity;


import java.util.List;

public class AutoNightVision {

    public static void checkAndApplyNightVision() {
        if (CarpetWhhSettings.autoNightVision) {
            applyNightVisionToAllPlayers();
        } else {
            removeNightVisionFromAllPlayers();
        }
    }
    public static void applyNightVisionToAllPlayers() {
        if (CarpetServer.minecraft_server != null) {
            List<ServerPlayerEntity> players = CarpetServer.minecraft_server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player : players) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 255, false, false, true));
            }
        }
    }

    public static void removeNightVisionFromAllPlayers() {
        if (CarpetServer.minecraft_server != null) {
            List<ServerPlayerEntity> players = CarpetServer.minecraft_server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player : players) {
                player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            }
        }
    }

    public static void onAutoNightVisionChanged() {
        if (CarpetWhhSettings.autoNightVision) {
            AutoNightVision.applyNightVisionToAllPlayers();  // 启用夜视效果
        } else {
            AutoNightVision.removeNightVisionFromAllPlayers();  // 移除夜视效果
        }
    }

}
