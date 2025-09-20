package com.whh.util;

import com.whh.CarpetWhhSettings;
import net.minecraft.server.world.ServerWorld;

public class DayTimeListener {
    // 每次游戏时间变更时检查并应用规则
    private static int tickCounter = 0;
    private static final int CHECK_INTERVAL = 2000;  // 200 ticks 等于大约 10 秒（游戏内每秒 20 ticks）

    // 每次游戏时间变更时检查并应用规则
    public static void tick(ServerWorld world) {
        tickCounter++;

        // 每隔 CHECK_INTERVAL ticks 检查一次是否是白天
        if (tickCounter >= CHECK_INTERVAL) {
            tickCounter = 0;  // 重置计数器

            // 获取当前游戏时间（Minecraft 中一天有 24000 个刻）
            long time = world.getTimeOfDay();

            if (time >= 11616 && time < 23000) {
                CarpetWhhSettings.onAutoNightVisionChanged();  // 触发规则变化后的操作
            }
        }
    }
}
