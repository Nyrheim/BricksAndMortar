package net.nyrheim.bricksandmortar.command.droptable;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.command.droptable.conversation.additem.ItemTypePrompt;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import net.nyrheim.bricksandmortar.node.DropTableView;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class DropTableAddItemCommand implements CommandExecutor {

    private final BricksAndMortar plugin;
    private final ConversationFactory conversationFactory;

    public DropTableAddItemCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
        this.conversationFactory = new ConversationFactory(plugin)
                .withModality(true)
                .withFirstPrompt(new ItemTypePrompt(plugin))
                .withEscapeSequence("cancel")
                .addConversationAbandonedListener(event -> {
                    if (!event.gracefulExit()) {
                        if (event.getContext().getForWhom() instanceof CommandSender) {
                            CommandSender whomst = (CommandSender) event.getContext().getForWhom();
                            whomst.sendMessage(RED + "Operation cancelled.");
                        }
                    }
                });
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.droptable.additem")) {
            sender.sendMessage(RED + "You do not have permission to add drop table items");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /droptable additem [drop table]");
            return true;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTable dropTable= nodeService.getDropTable(Arrays.stream(args).reduce((a, b) -> a + " " + b).orElse(""));
        if (dropTable == null) {
            sender.sendMessage(RED + "There is no drop table by that name.");
            return true;
        }
        if (!(sender instanceof Conversable)) {
            sender.sendMessage(RED + "You cannot be conversed with (??)");
            return true;
        }
        Conversable conversable = (Conversable) sender;
        Conversation conversation = conversationFactory.buildConversation(conversable);
        conversation.getContext().setSessionData("dropTable", dropTable);
        conversation.addConversationAbandonedListener(event -> {
            if (event.gracefulExit()) {
                if (event.getContext().getForWhom() instanceof CommandSender) {
                    CommandSender whomst = (CommandSender) event.getContext().getForWhom();
                    new DropTableView(dropTable).send(whomst);
                }
            }
        });
        conversation.begin();
        return true;
    }
}
