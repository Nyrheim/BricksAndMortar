package net.nyrheim.bricksandmortar.recipe;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType;

import java.util.*;

import static java.util.logging.Level.SEVERE;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.HAMMER;
import static net.nyrheim.penandpaper.item.weapon.WeaponType.DAGGER;
import static org.bukkit.Material.ANVIL;

public final class BricksRecipeService {

    private final BricksAndMortar plugin;
    private final List<BricksRecipe> recipes = new ArrayList<>();

    public BricksRecipeService(BricksAndMortar plugin) {
        this.plugin = plugin;
        plugin.getServer().clearRecipes();
    }

    public void loadRecipes() {
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(SEVERE, "No item quality provider found", exception);
            return;
        }
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        plugin.getLogger().info("Loading recipes...");
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                HAMMER,
                Collections.singletonList(itemQualityProvider.getItemQuality("adequate")),
                DAGGER,
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.WAND, 1);
                    put(AdventuringGearType.CHALK, 1);
                }}
        ));
        plugin.getLogger().info("Recipes loaded (" + recipes.size() + ")");
    }

    public List<BricksRecipe> getRecipes() {
        return recipes;
    }

}
