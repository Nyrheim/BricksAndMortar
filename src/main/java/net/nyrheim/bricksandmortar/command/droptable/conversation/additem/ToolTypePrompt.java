package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.penandpaper.item.ItemType;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.ChatColor.WHITE;

public final class ToolTypePrompt extends ValidatingPrompt {

    private final BricksAndMortar plugin;

    public ToolTypePrompt(BricksAndMortar plugin) {
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
    protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        context.setSessionData(
                "toolType",
                ItemType.valueOf(input.toUpperCase()
                        .replace(' ', '_')
                        .replace('-', '_')
                        .replace("'", "")
                )
        );

        return new ItemAddedPrompt(plugin);
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return WHITE + "Tool type: (type \"cancel\" to cancel)";
    }

}
