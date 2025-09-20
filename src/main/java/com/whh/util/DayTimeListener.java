package com.whh.util;

import com.whh.CarpetWhhSettings;
import net.minecraft.server.world.ServerWorld;

public class DayTimeListener {

    private static int tickCounter = 0;
    private static final int CHECK_INTERVAL = 2000;  // 每秒20ticks


    public static void tick(ServerWorld world) {
        tickCounter++;

        if (tickCounter >= CHECK_INTERVAL) {
            tickCounter = 0;

            long time = world.getTimeOfDay();

            if (time >= 11616 && time < 23000) {
                CarpetWhhSettings.onAutoNightVisionChanged();
            }
        }
    }
}
