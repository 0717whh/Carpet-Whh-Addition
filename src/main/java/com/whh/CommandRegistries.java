package com.whh;

import com.mojang.brigadier.CommandDispatcher;
import com.whh.command.SetAutoNightVisionCommand;
import com.whh.command.WhhTestCommand;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistries {

    private CommandRegistries() {}

    public static void registerAll(CommandDispatcher<ServerCommandSource> dispatcher,
                                   CommandRegistryAccess registryAccess) {
        WhhTestCommand.register(dispatcher);
        //SetAutoNightVisionCommand.register(dispatcher);
    }

}
