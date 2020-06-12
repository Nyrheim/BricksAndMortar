package net.nyrheim.bricksandmortar.command.node;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class NodeCommand implements CommandExecutor {

    private final BricksAndMortar plugin;
    private final NodeCreateCommand nodeCreateCommand;
    private final NodeDeleteCommand nodeDeleteCommand;

    public NodeCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
        nodeCreateCommand = new NodeCreateCommand(plugin);
        nodeDeleteCommand = new NodeDeleteCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " [create|delete]");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "create":
                return nodeCreateCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "delete":
                return nodeDeleteCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            default:
                sender.sendMessage(RED + "Usage: /" + label + " [create|delete]");
                break;
        }
        return true;
    }
}
