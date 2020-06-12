package net.nyrheim.bricksandmortar.node;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

import static net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND;
import static net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT;

public final class DropTableView {

    private final DropTable dropTable;

    public DropTableView(DropTable dropTable) {
        this.dropTable = dropTable;
    }

    public void send(CommandSender target) {
        dropTable.getItems().forEach(item -> {
            ComponentBuilder rowBuilder = new ComponentBuilder("Item type: " + item.getItemType().getName() +
                    ", amount: " + item.getAmount() +
                    ", quality: " + (item.getQuality() != null ? item.getQuality().getName() : "none") +
                    ", chance: " + item.getChance() +
                    ", tool type: " + item.getToolType().getName() + " ");
            TextComponent deleteButton = new TextComponent("(x)");
            deleteButton.setHoverEvent(
                    new HoverEvent(
                            SHOW_TEXT,
                            new ComponentBuilder("Click here to delete this item from the drop table").create()
                    )
            );
            deleteButton.setClickEvent(
                    new ClickEvent(
                            RUN_COMMAND,
                            "/droptable removeitem " + item.getId().getValue()
                    )
            );
            rowBuilder.append(deleteButton).color(ChatColor.RED);
            target.spigot().sendMessage(
                    rowBuilder.create()
            );
        });
        TextComponent addButton = new TextComponent("Add item");
        addButton.setHoverEvent(
                new HoverEvent(
                        SHOW_TEXT,
                        new ComponentBuilder("Click here to add a new item to the drop table").create()
                )
        );
        addButton.setClickEvent(
                new ClickEvent(
                        RUN_COMMAND,
                        "/droptable additem " + dropTable.getName()
                )
        );
        ComponentBuilder addButtonRowBuilder = new ComponentBuilder(addButton).color(ChatColor.GREEN);
        target.spigot().sendMessage(addButtonRowBuilder.create());
    }

}
