package net.nyrheim.bricksandmortar.command.node;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

public final class NodeListCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public NodeListCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.node.list")) {
            sender.sendMessage(RED + "You do not have permission to list nodes");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        sender.sendMessage(WHITE + "Nodes:");
        nodeService.getNodes().forEach(node -> sender.sendMessage(WHITE + " \u2022 " + node.getName()));
        return true;
    }
}
