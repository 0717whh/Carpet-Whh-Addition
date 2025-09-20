package com.whh.init;

import carpet.CarpetServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleRegistries {
    private static final Logger LOGGER = LoggerFactory.getLogger("carpetwhhaddition-rules");

    private RuleRegistries() {}

    /** 在这里列出你的所有 Settings 类 */
    public static void registerAll() {
        register(
                com.whh.settings.CarpetWhhSettings.class
                // , com.whh.carpet.settings.OtherSettings.class
                // , com.whh.carpet.settings.MoreSettings.class
        );
    }


    public static void register(Class<?>... settingsClasses) {
        for (Class<?> cls : settingsClasses) {
            try {
                CarpetServer.settingsManager.parseSettingsClass(cls);
                LOGGER.info("Registered Carpet settings: {}", cls.getName());
            } catch (Throwable t) {
                LOGGER.error("Failed to register Carpet settings: {}", cls.getName(), t);
            }
        }
    }
}
