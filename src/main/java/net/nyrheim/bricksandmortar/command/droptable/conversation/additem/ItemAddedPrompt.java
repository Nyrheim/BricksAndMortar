package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.DropTableItem;
import net.nyrheim.penandpaper.item.ItemType;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.ChatColor.GREEN;

public final class ItemAddedPrompt extends MessagePrompt {

    private final BricksAndMortar plugin;

    public ItemAddedPrompt(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    protected @Nullable Prompt getNextPrompt(@NotNull ConversationContext context) {
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        DropTable dropTable = (DropTable) context.getSessionData("dropTable");
        dropTable.addItem(new DropTableItem(
                (ItemType) context.getSessionData("itemType"),
                (int) context.getSessionData("amount"),
                (RPKItemQuality) context.getSessionData("quality"),
                (int) context.getSessionData("chance"),
                (ItemType) context.getSessionData("toolType")
        ));
        nodeService.updateDropTable(dropTable);
        return END_OF_CONVERSATION;
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return GREEN + "Item added.";
    }
}
