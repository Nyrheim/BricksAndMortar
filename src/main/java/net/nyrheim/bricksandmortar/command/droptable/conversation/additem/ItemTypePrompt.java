package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.penandpaper.item.ItemType;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.WHITE;

public final class ItemTypePrompt extends ValidatingPrompt {

    private final BricksAndMortar plugin;

    public ItemTypePrompt(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        try {
            ItemType.valueOf(input.toUpperCase()
                    .replace(' ', '_')
                    .replace('-', '_')
                    .replace("'", "")
            );
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    @Override
    protected @NotNull Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        context.setSessionData(
                "itemType",
                ItemType.valueOf(input.toUpperCase()
                        .replace(' ', '_')
                        .replace('-', '_')
                        .replace("'", "")
                )
        );
        return new AmountPrompt(plugin);
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return WHITE + "Item type: (type \"cancel\" to cancel)";
    }
}
