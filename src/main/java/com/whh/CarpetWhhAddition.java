package com.whh;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import com.whh.i18n.YamlTranslations;
import com.whh.util.DayTimeListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;


public class CarpetWhhAddition implements ModInitializer, CarpetExtension {
	public static final String MOD_ID = "carpetwhhaddition";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final Yaml YAML = new Yaml();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
       // CarpetServer.settingsManager.parseSettingsClass(CarpetWhhSettings.class);
		LOGGER.info("Hello Fabric world!");

        CarpetServer.manageExtension(this);

        LOGGER.info("[{}] initialized", MOD_ID);

        ServerTickEvents.START_WORLD_TICK.register(world -> {
            if (world instanceof ServerWorld) {
                DayTimeListener.tick(world);  // 检查时间并在早上应用夜视效果
            }
        });

    }
    public String version() {
        return "WhhCarpetAddition 0.1.0";
    }
    public void  onGameStarted() {
        CarpetRuleRegistrar.register();
    }

    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
                                 CommandRegistryAccess registryAccess) {
        CommandRegistries.registerAll(dispatcher, registryAccess);
    }





    public Map<String, String> canHasTranslations(String lang) {
        return YamlTranslations.load(lang, MOD_ID);
    }
    public static void changeAutoNightVisionRule(boolean newValue) {
        CarpetWhhSettings.autoNightVision = newValue;  // 更新规则值
        CarpetWhhSettings.onAutoNightVisionChanged();  // 触发规则变化后的操作
    }
}