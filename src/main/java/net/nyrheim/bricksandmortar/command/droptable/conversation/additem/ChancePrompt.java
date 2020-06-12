package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;

public final class ChancePrompt extends NumericPrompt {

    private final BricksAndMortar plugin;

    public ChancePrompt(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return input.intValue() > 0;
    }

    @Override
    protected @NotNull Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull Number input) {
        context.setSessionData("chance", input.intValue());
        return new ToolTypePrompt(plugin);
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Chance: (type \"cancel\" to cancel)";
    }
}
