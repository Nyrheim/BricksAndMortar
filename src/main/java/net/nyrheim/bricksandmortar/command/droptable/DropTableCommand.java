package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class DropTableCommand implements CommandExecutor {

    private final BricksAndMortar plugin;
    private final DropTableCreateCommand dropTableCreateCommand;
    private final DropTableDeleteCommand dropTableDeleteCommand;
    private final DropTableViewCommand dropTableViewCommand;
    private final DropTableAddItemCommand dropTableAddItemCommand;
    private final DropTableRemoveItemCommand dropTableRemoveItemCommand;
    private final DropTableListCommand dropTableListCommand;

    public DropTableCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
        this.dropTableCreateCommand = new DropTableCreateCommand(plugin);
        this.dropTableDeleteCommand = new DropTableDeleteCommand(plugin);
        this.dropTableViewCommand = new DropTableViewCommand(plugin);
        this.dropTableAddItemCommand = new DropTableAddItemCommand(plugin);
        this.dropTableRemoveItemCommand = new DropTableRemoveItemCommand(plugin);
        this.dropTableListCommand = new DropTableListCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " [create|delete|view|additem|removeitem|list]");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "create":
                return dropTableCreateCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "delete":
                return dropTableDeleteCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "view":
                return dropTableViewCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "additem":
                return dropTableAddItemCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "removeitem":
                return dropTableRemoveItemCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            case "list":
                return dropTableListCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            default:
                sender.sendMessage(RED + "Usage: /" + label + " [create|delete|view|additem|removeitem|list]");
                break;
        }
        return true;
    }
}
