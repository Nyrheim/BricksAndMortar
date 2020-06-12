package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.DropTableView;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class DropTableCreateCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public DropTableCreateCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.create")) {
            sender.sendMessage(RED + "You do not have permission to create drop tables.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " create [name]");
            return true;
        }
        String name = args[0];
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTable dropTable = new DropTable(name);
        nodeService.addDropTable(dropTable);
        sender.sendMessage(GREEN + "Drop table \"" + name + "\" created.");
        new DropTableView(dropTable).send(sender);
        return true;
    }
}
