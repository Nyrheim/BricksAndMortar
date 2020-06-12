package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jooq.exception.DataAccessException;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class DropTableDeleteCommand implements CommandExecutor {
    private final BricksAndMortar plugin;

    public DropTableDeleteCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.delete")) {
            sender.sendMessage(RED + "You do not have permission to delete drop tables.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " delete [drop table]");
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTable dropTable = nodeService.getDropTable(args[0]);
        try {
            nodeService.deleteDropTable(dropTable);
            sender.sendMessage(GREEN + "Drop table deleted.");
        } catch (DataAccessException exception) { //TODO Actually check if nodes are still using the drop table. This method sucks but I'm in a rush.
            sender.sendMessage(RED + "Could not delete drop table. Is a node still using it?");
        }
        return true;
    }
}
