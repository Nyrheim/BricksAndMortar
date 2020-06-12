package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;

import static java.util.logging.Level.WARNING;

public final class AmountPrompt extends NumericPrompt {

    private final BricksAndMortar plugin;

    public AmountPrompt(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return input.intValue() > 0;
    }

    @Override
    protected @NotNull Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull Number input) {
        context.setSessionData("amount", input.intValue());
        try {
            plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(WARNING, "Failed to find item quality provider", exception);
            return new ChancePrompt(plugin);
        }
        return new QualityPrompt(plugin);
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Amount: (type \"cancel\" to cancel)";
    }
}
