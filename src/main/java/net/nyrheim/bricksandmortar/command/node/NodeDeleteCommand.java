package net.nyrheim.bricksandmortar.command.node;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.Node;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class NodeDeleteCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public NodeDeleteCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.node.delete")) {
            sender.sendMessage(RED + "You do not have permission to delete nodes.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " [node]");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        Node node = nodeService.getNode(args[0]);
        if (node == null) {
            sender.sendMessage(RED + "There is no node by that name.");
            return true;
        }
        nodeService.deleteNode(node);
        sender.sendMessage(GREEN + "Node deleted.");
        return true;
    }
}
