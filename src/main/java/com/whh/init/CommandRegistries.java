package com.whh.init;

import com.mojang.brigadier.CommandDispatcher;
import com.whh.command.WhhTestCommand;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistries {

    private CommandRegistries() {}

    public static void registerAll(CommandDispatcher<ServerCommandSource> dispatcher,
                                   CommandRegistryAccess registryAccess) {
        WhhTestCommand.register(dispatcher);
        // 以后在这里追加：OtherCommand.register(dispatcher);
    }

}
