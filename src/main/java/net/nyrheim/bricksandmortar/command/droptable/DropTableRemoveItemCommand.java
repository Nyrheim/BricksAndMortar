package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class DropTableRemoveItemCommand implements CommandExecutor {
    private final BricksAndMortar plugin;

    public DropTableRemoveItemCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.removeitem")) {
            sender.sendMessage(RED + "You do not have permission to remove drop table items.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " removeitem [id]");
            return true;
        }
        int dropTableItemId;
        try {
            dropTableItemId = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Item ID must be an integer.");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTableItem dropTableItem = nodeService.getDropTableItem(new DropTableItemId(dropTableItemId));
        DropTable dropTable = nodeService.getDropTable(dropTableItem);
        if (dropTable == null) {
            sender.sendMessage(RED + "Drop table not found. I don't know how you're seeing this tbh.");
            return true;
        }
        dropTable.removeItem(dropTableItem);
        nodeService.updateDropTable(dropTable);
        sender.sendMessage(GREEN + "Item removed.");
        new DropTableView(dropTable).send(sender);
        return true;
    }
}
