package net.nyrheim.bricksandmortar.command.droptable;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND;
import static net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT;
import static org.bukkit.ChatColor.RED;

public final class DropTableListCommand implements CommandExecutor {
    private final BricksAndMortar plugin;

    public DropTableListCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.list")) {
            sender.sendMessage(RED + "You do not have permission to list drop tables.");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        nodeService.getDropTables().forEach(dropTable -> {
            TextComponent dropTableButton = new TextComponent(dropTable.getName());
            dropTableButton.setHoverEvent(new HoverEvent(
                    SHOW_TEXT,
                    new ComponentBuilder("Click here to view drop table " + dropTable.getName()).create()
            ));
            dropTableButton.setClickEvent(new ClickEvent(
                    RUN_COMMAND,
                    "/droptable view " + dropTable.getName()
            ));
            sender.spigot().sendMessage(new ComponentBuilder(dropTableButton).create());
        });
        return true;
    }
}
