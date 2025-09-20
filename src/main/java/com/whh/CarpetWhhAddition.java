package com.whh;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
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


    }
    public String version() {
        return "WhhCarpetAddition 0.1.0";
    }

    public void onGameStarted() {
        //Carpet解析规则类
        CarpetServer.settingsManager.parseSettingsClass(CarpetWhhSettings.class);
    }


    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
                                 CommandRegistryAccess registryAccess) {
        dispatcher.register(
                CommandManager.literal("whhtest")
                        .executes(this::runWhhTest)
        );
    }



    private int runWhhTest(CommandContext<ServerCommandSource> ctx) {
        if (!CarpetWhhSettings.enableWhhTestCommand) {
            ctx.getSource().sendError(Text.literal(
                    "Command disabled by Carpet rule: enableWhhTestCommand"));
            return 0;
        }
        ctx.getSource().sendFeedback(() -> Text.literal("Hello whh"), false);
        return 1;
    }
    @Override
    public Map<String, String> canHasTranslations(String lang) {
        String path = String.format("/assets/%s/carpet/lang/%s.yml", MOD_ID, lang);
        try (InputStream in = CarpetWhhAddition.class.getResourceAsStream(path)) {
            if (in == null) return Collections.emptyMap();
            Map<String, Object> raw = YAML.load(in);
            return flattenYaml(raw);
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    private static Map<String, String> flattenYaml(Map<String, Object> raw) {
        java.util.Map<String, String> flat = new java.util.HashMap<>();
        flatten("", raw, flat);
        return flat;
    }

    @SuppressWarnings("unchecked")
    private static void flatten(String prefix, Map<String, Object> src, Map<String, String> out) {
        for (Map.Entry<String, Object> e : src.entrySet()) {
            String key = prefix.isEmpty() ? e.getKey() : prefix + "." + e.getKey();
            Object v = e.getValue();
            if (v instanceof Map<?, ?> m) {
                flatten(key, (Map<String, Object>) m, out);
            } else if (v != null) {
                out.put(key, String.valueOf(v));
            }
        }
    }


}