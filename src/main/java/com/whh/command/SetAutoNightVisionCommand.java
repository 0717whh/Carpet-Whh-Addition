package com.whh.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.whh.CarpetWhhAddition;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SetAutoNightVisionCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("carpet")
                        .then(CommandManager.literal("setDefault")
                        .then(CommandManager.literal("autoNightVision")
                                .then(CommandManager.argument("value", StringArgumentType.word())
                                .executes(context -> {
                                            String value = StringArgumentType.getString(context, "value");
                                            boolean newValue = Boolean.parseBoolean(value);

                                            CarpetWhhAddition.changeAutoNightVisionRule(newValue);
                                            return 1;
                                        })
                                )
                        ))
        );
    }
}
