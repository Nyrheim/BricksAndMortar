package net.nyrheim.bricksandmortar.command.droptable.conversation.additem;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;

import static java.util.logging.Level.WARNING;

public final class QualityPrompt extends ValidatingPrompt {

    private final BricksAndMortar plugin;

    public QualityPrompt(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(WARNING, "Failed to find item quality provider", exception);
            return false;
        }
        return itemQualityProvider.getItemQuality(input) != null || input.equalsIgnoreCase("none");
    }

    @Override
    protected @NotNull Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(WARNING, "Failed to find item quality provider", exception);
            context.setSessionData("quality", null);
            return new ChancePrompt(plugin);
        }
        context.setSessionData("quality", itemQualityProvider.getItemQuality(input));
        return new ChancePrompt(plugin);
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Quality: (type \"none\" for no quality, \"cancel\" to cancel)";
    }
}
