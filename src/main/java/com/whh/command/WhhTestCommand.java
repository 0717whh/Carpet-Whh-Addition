package com.whh.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.whh.CarpetWhhSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class WhhTestCommand {
    private WhhTestCommand() {}

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("whhtest")
                        .executes(WhhTestCommand::run)
        );
    }

    private static int run(CommandContext<ServerCommandSource> ctx) {
        if (!CarpetWhhSettings.enableWhhTestCommand) {
            ctx.getSource().sendError(Text.literal(
                    "Command disabled by Carpet rule: enableWhhTestCommand"));
            return 0;
        }
        ctx.getSource().sendFeedback(() -> Text.literal("Hello whh"), false);
        return 1;
    }
}
