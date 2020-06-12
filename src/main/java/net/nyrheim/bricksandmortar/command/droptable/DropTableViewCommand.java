package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.DropTableView;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;

public final class DropTableViewCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public DropTableViewCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.view")) {
            sender.sendMessage(RED + "You do not have permission to view drop tables.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " view [drop table]");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTable dropTable = nodeService.getDropTable(args[0]);
        if (dropTable == null) {
            sender.sendMessage(RED + "No drop table by that name found");
            return true;
        }
        new DropTableView(dropTable).send(sender);
        return true;
    }

}
