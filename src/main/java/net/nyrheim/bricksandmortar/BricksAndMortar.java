package net.nyrheim.bricksandmortar;

import com.rpkit.core.bukkit.event.provider.RPKBukkitServiceProviderReadyEvent;
import com.rpkit.core.bukkit.plugin.RPKBukkitPlugin;
import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.command.droptable.DropTableCommand;
import net.nyrheim.bricksandmortar.command.node.NodeCommand;
import net.nyrheim.bricksandmortar.command.profession.ProfessionCommand;
import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import net.nyrheim.bricksandmortar.node.NodePlayerInteractListener;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.recipe.BricksRecipeService;
import net.nyrheim.bricksandmortar.recipe.RecipeGUIInventoryClickListener;
import net.nyrheim.bricksandmortar.recipe.WorkstationPlayerInteractListener;
import net.nyrheim.bricksandmortar.service.Services;
import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class BricksAndMortar extends RPKBukkitPlugin implements Listener {

    private PenAndPaper penAndPaper;
    private Database database;
    private Services services;

    @Override
    public void onEnable() {
        penAndPaper = (PenAndPaper) getServer().getPluginManager().getPlugin("PenAndPaper");
        if (penAndPaper == null) {
            setEnabled(false);
            return;
        }

        saveDefaultConfig();

        database = new Database(
                this,
                getConfig().getString("database.url"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        services = new Services(this);
        services.register(BricksProfessionService.class, new BricksProfessionService(this));
        services.register(BricksRecipeService.class, new BricksRecipeService(this));
        services.register(BricksNodeService.class, new BricksNodeService(database));
    }

    private boolean loadedRecipes = false;

    @Override
    public void onPostEnable() {
        if (!loadedRecipes) {
            attemptRecipeInitialization();
        }
    }

    @EventHandler
    public void onServiceProviderReady(RPKBukkitServiceProviderReadyEvent event) {
        if (event.getServiceProvider() instanceof RPKItemQualityProvider) {
            if (!loadedRecipes) {
                attemptRecipeInitialization();
            }
        }
    }

    private void attemptRecipeInitialization() {
        try {
            core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
            getServices().get(BricksRecipeService.class).loadRecipes();
            loadedRecipes = true;
        } catch (UnregisteredServiceException ignored) {}
    }

    @Override
    public void registerListeners() {
        registerListeners(
                new RecipeGUIInventoryClickListener(),
                new WorkstationPlayerInteractListener(this),
                new NodePlayerInteractListener(this),
                this
        );
    }

    @Override
    public void registerCommands() {
        getCommand("profession").setExecutor(new ProfessionCommand(this));
        getCommand("node").setExecutor(new NodeCommand(this));
        getCommand("droptable").setExecutor(new DropTableCommand(this));
    }

    public PenAndPaper getPenAndPaper() {
        return penAndPaper;
    }

    public Database getDatabase() {
        return database;
    }

    public Services getServices() {
        return services;
    }
}
