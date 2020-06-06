package net.nyrheim.bricksandmortar.recipe;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.util.logging.Level.SEVERE;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.*;
import static net.nyrheim.penandpaper.item.weapon.WeaponType.*;
import static org.bukkit.Material.ANVIL;
import static org.bukkit.Material.FLETCHING_TABLE;

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
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(DAGGER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HANDAXE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(AXEHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(JAVELIN, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LIGHT_HAMMER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(MACE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SICKLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SPEAR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(BATTLEAXE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(AXEHEAD, 3);
                    put(POMMEL, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(FLAIL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 2);
                    put(POMMEL, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(GLAIVE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 1);
                    put(AXEHEAD, 1);
                    put(POMMEL, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(GREATAXE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(AXEHEAD, 5);
                    put(POMMEL, 1);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(GREATSWORD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 6);
                    put(POMMEL, 1);
                    put(GUARD, 1);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HALBERD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 1);
                    put(AXEHEAD, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LANCE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                    put(SPEARHEAD, 5);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LONGSWORD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 2);
                    put(POMMEL, 1);
                    put(GUARD, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(MAUL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(HAMMER_HEAD, 7);
                    put(POMMEL, 1);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(MORNINGSTAR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 2);
                    put(POMMEL, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(PIKE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                    put(SPEARHEAD, 5);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(RAPIER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                    put(GUARD, 1);
                    put(POMMEL, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SCIMITAR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                    put(POMMEL, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SHORTSWORD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                    put(POMMEL, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(TRIDENT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 1);
                    put(POMMEL, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(WAR_PICK, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(SPEARHEAD, 1);
                    put(AXEHEAD, 1);
                    put(POMMEL, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(WARHAMMER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 2);
                    put(POMMEL, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HAND_CROSSBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(CBOW_MECHANISM, 1);
                    put(STRING, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LIGHT_CROSSBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(CBOW_MECHANISM, 2);
                    put(STRING, 1);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HEAVY_CROSSBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(CBOW_MECHANISM, 3);
                    put(STRING, 1);
                }},
                60,
                60
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(GUARD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(BLADE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SPEARHEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(AXEHEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HAMMER_HEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(CBOW_MECHANISM, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(POMMEL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(ARROW_HEAD, 4),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(NAILS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(CLUB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                3,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(GREATCLUB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(LUMBER, 3);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(QUARTERSTAFF, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(DART, 4),
                new HashMap<ItemType, Integer>() {{
                    put(ARROW_BODY, 1);
                    put(SPEARHEAD, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SHORTBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 1);
                    put(LUMBER, 6);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SLING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LEATHER_STRAP, 1);
                    put(STRING, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(WHIP, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LEATHER_STRAP, 1);
                    put(STRING, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(BLOWGUN, 1),
                new HashMap<ItemType, Integer>() {{

                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                3,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LONGBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 2);
                    put(LUMBER, 8);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                5,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LONGERBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LONGBOW, 2);
                    put(STRING, 2);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HANDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(LEATHER_STRAP, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(ARROW_BODY, 4),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(BEAM, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 10);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(STORAGE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 4);
                    put(LEATHER_STRAP, 1);
                    put(GLUE, 1);
                    put(NAILS, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(DOOR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 6);
                    put(NAILS, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SMALL_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 4);
                    put(NAILS, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(MEDIUM_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 8);
                    put(NAILS, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LARGE_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 10);
                    put(NAILS, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(TRINKET, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(GLUE, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(BLOCK_AND_TACKLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(NAILS, 5);
                    put(GLUE, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(FISHING_ROD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 6);
                    put(GLUE, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SMITHS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(HAMMER_HEAD, 4);
                    put(BLADE, 3);
                    put(IRON_INGOT, 5);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(CARPENTERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(AXEHEAD, 4);
                    put(BLADE, 3);
                    put(IRON_INGOT, 5);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(ARCHITECTS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(LUMBER, 12);
                    put(IRON_INGOT, 6);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(LEATHERWORKERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(BLADE, 7);
                    put(IRON_INGOT, 5);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(MINERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(HAMMER_HEAD, 4);
                    put(BLADE, 3);
                    put(IRON_INGOT, 5);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(WOODCUTTERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(AXEHEAD, 12);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(FARMERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(BLADE, 8);
                    put(IRON_INGOT, 4);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(ALCHEMISTS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(GLASS, 12);
                    put(IRON_INGOT, 8);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HERBALISTS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(BLADE, 8);
                    put(IRON_INGOT, 4);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(HUNTERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SHORTBOW, 1);
                    put(IRON_INGOT, 3);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(FISHERMANS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FISHING_ROD, 1);
                    put(NET, 2);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(SCAFFOLDING_KIT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 12);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                Collections.emptyList(),
                new PenItemStack(DIVING_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{

                }},
                50,
                50
        ));
        plugin.getLogger().info("Recipes loaded (" + recipes.size() + ")");
    }

    public List<BricksRecipe> getRecipes() {
        return recipes;
    }

}
