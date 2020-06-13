package net.nyrheim.bricksandmortar.recipe;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.logging.Level.SEVERE;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.HIDE;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.*;
import static net.nyrheim.penandpaper.item.armor.ArmorType.*;
import static net.nyrheim.penandpaper.item.weapon.WeaponType.*;

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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(DAGGER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(HANDAXE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(AXEHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(JAVELIN, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(LIGHT_HAMMER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(MACE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(HAMMER_HEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(SICKLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(BLADE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(SPEAR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 2);
                    put(SPEARHEAD, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(LANCE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                    put(SPEARHEAD, 5);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                5,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(PIKE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                    put(SPEARHEAD, 5);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                3,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CHAIN_SHIRT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(CHAIN_RINGS, 24);
                    put(WOOL, 5);
                }},
                60,
                60
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(BREASTPLATE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ARMOR_PLATE, 22);
                    put(LEATHER_STRAP, 2);
                    put(WOOL, 5);
                }},
                65,
                65
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(HALF_PLATE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ARMOR_PLATE, 30);
                    put(LEATHER_STRAP, 5);
                    put(WOOL, 10);
                }},
                70,
                70
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(IRON_BARDING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HALF_PLATE, 1);
                    put(BARDING, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CHAIN_MAIL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(CHAIN_RINGS, 18);
                    put(ARMOR_PLATE, 6);
                    put(WOOL, 5);
                }},
                75,
                75
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(PLATE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ARMOR_PLATE, 36);
                    put(LEATHER_STRAP, 10);
                    put(WOOL, 10);
                }},
                80,
                80
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CHAIN, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 3);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(BULLSEYE_LANTERN, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                    put(GLASS, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(BARKING_BOX, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 3);
                    put(GOLD_INGOT, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(LOCK, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(LOCKPICK, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CROWBAR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 4);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(HUNTING_TRAP, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(BALL_BEARINGS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 3);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(MANACLES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 4);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(GUARD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(BLADE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(SPEARHEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(AXEHEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(HAMMER_HEAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CBOW_MECHANISM, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 2);
                    put(FLUX, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(POMMEL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(ARROW_HEAD, 4),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(NAILS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(ARMOR_PLATE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CHAIN_RINGS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(FLUX, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(CLUB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                3,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(GREATCLUB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(LUMBER, 3);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(QUARTERSTAFF, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 3);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(DART, 4),
                new HashMap<ItemType, Integer>() {{
                    put(ARROW_BODY, 1);
                    put(SPEARHEAD, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(SHORTBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 1);
                    put(LUMBER, 6);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(SLING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LEATHER_STRAP, 1);
                    put(STRING, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(WHIP, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LEATHER_STRAP, 1);
                    put(STRING, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(BLOWGUN, 1),
                new HashMap<ItemType, Integer>() {{

                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                3,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(LONGBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 2);
                    put(LUMBER, 8);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                5,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(LONGERBOW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LONGBOW, 2);
                    put(STRING, 2);
                }},
                100,
                100
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(HANDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(LEATHER_STRAP, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(ARROW_BODY, 4),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(BEAM, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 10);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(STORAGE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 4);
                    put(LEATHER_STRAP, 1);
                    put(GLUE, 1);
                    put(NAILS, 5);
                }},
                25,
                25
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(DOOR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 6);
                    put(NAILS, 5);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(SMALL_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 4);
                    put(NAILS, 5);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(MEDIUM_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 8);
                    put(NAILS, 5);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(LARGE_FURNITURE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 10);
                    put(NAILS, 5);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(TRINKET, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(GLUE, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(BLOCK_AND_TACKLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                    put(NAILS, 5);
                    put(GLUE, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(FISHING_ROD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(STRING, 6);
                    put(GLUE, 1);
                }},
                40,
                40
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(SHIELD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 5);
                    put(NAILS, 10);
                    put(GLUE, 1);
                    put(LEATHER_STRAP, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(REINFORCED_SHIELD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 5);
                    put(NAILS, 10);
                    put(GLUE, 1);
                    put(IRON_INGOT, 4);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(STAFF, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 1);
                    put(LUMBER, 2);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(WAND, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 4);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(SUGAR, 3),
                new HashMap<ItemType, Integer>() {{
                    put(HAIRBEET, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(FLOUR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(REEDGRAIN, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(BREAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FLOUR, 1);
                    put(SALT, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(SMOKED_FISH, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FISH, 1);
                    put(LUMBER, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(GRILLED_MEAT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(MEAT, 1);
                    put(LUMBER, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(BAKED_SNEIP, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SNEIP, 1);
                    put(BUTTER, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(CHEESE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(MILK, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(BUTTER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(MILK, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(STEW, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SNEIP, 1);
                    put(MEAT, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(SWEET_BREAD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FLOUR, 1);
                    put(SUGAR, 1);
                    put(EGG, 1);
                    put(BUTTER, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(ROASTED_DINNER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SNEIP, 1);
                    put(MEAT, 1);
                    put(SALT, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(ALE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(REEDGRAIN, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(LIQUOR, 1),
                new HashMap<ItemType, Integer>() {{
                    put(REEDGRAIN, 10);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(WINE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SPRINGBERRIES, 1);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(SPRINGBERRY_JAM, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SPRINGBERRIES, 4);
                    put(SUGAR, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(SPRINGBERRY_PIE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SPRINGBERRY_JAM, 1);
                    put(FLOUR, 1);
                    put(EGG, 1);
                    put(BUTTER, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.FURNACE,
                professionService.getProfession("Cook"),
                1,
                CHEFS_TOOLS,
                emptyList(),
                new PenItemStack(GOURD_BOTTLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SKELTIE, 1);
                    put(GLUE, 1);
                }},
                5,
                5
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(FLUX, 1),
                new HashMap<ItemType, Integer>() {{
                    put(POTASH, 1);
                    put(FINE_SAND, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTASH, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(GLUE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SINEW, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(VIAL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(GLASS, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(GLASS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FINE_SAND, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(ORB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(GLASS, 8);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(GLASS_BOTTLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(GLASS, 2);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_HEALING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(AMARELL, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_GREATER_HEALING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(POTION_OF_HEALING, 1);
                    put(HILLCAT_BLOOD, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_NUMBED_PAIN, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(ASHBLOOM, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_REGENERATION, 1),
                new HashMap<ItemType, Integer>() {{
                    put(POTION_OF_HEALING, 1);
                    put(ASHBLOOM, 2);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_GIANTS_LUNG, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(ASHBLOOM, 2);
                    put(GIANTS_ROOT, 2);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_WARMTH, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(WOOLEN_BLOOD, 3);
                    put(GIANTS_ROOT, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(PERFUME, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(GILLIWEED, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_MEMORY, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(CANDETINE, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_SIGHT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(RABBITS_EYE, 3);
                    put(ASHBLOOM, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(POTION_OF_MANLINESS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(GILLIWEED, 2);
                    put(GIANTS_ROOT, 2);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(ANTITOXIN_VIAL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(AMARELL, 3);
                    put(ASHBLOOM, 1);
                    put(MILK, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(ASSASSINS_BLOOD, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ACID_VIAL, 1);
                    put(SKINSBANE, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(HEMOPHILE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(AMARELL, 8);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(ACID_VIAL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(GLUE, 3);
                    put(POTASH, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(SKINSBANE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(ASHBLOOM, 8);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(MIND_MELT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(CANDETINE, 12);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(MALICE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(RABBITS_EYE, 2);
                    put(FLUX, 4);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(BLASTING_POWDER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(POTASH, 10);
                    put(FINE_SAND, 5);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(BOMB, 1),
                new HashMap<ItemType, Integer>() {{
                    put(IRON_INGOT, 1);
                    put(BLASTING_POWDER, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(TANGLER_GRENADE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(VIAL, 1);
                    put(GLUE, 4);
                    put(BLASTING_POWDER, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.BREWING_STAND,
                professionService.getProfession("Alchemist"),
                1,
                ALCHEMISTS_TOOLS,
                emptyList(),
                new PenItemStack(ALCHEMISTS_FIRE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(GLUE, 1);
                    put(LIQUOR, 1);
                }},
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(AdventuringGearType.LEATHER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HIDE, 1);
                    put(LUMBER, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(LEATHER_STRAP, 2),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(STRING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SINEW, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(STRING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HEMP, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(BARDING, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 30);
                    put(LEATHER_STRAP, 1);
                    put(IRON_INGOT, 2);
                    put(STRING, 15);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(BEDROLL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HIDE, 2);
                    put(WOOL, 10);
                    put(STRING, 10);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(BIT_AND_BRIDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 10);
                    put(LEATHER_STRAP, 1);
                    put(IRON_INGOT, 1);
                    put(STRING, 4);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(SADDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 10);
                    put(LEATHER_STRAP, 1);
                    put(IRON_INGOT, 2);
                    put(STRING, 8);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(EXOTIC_SADDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 10);
                    put(LEATHER_STRAP, 1);
                    put(IRON_INGOT, 2);
                    put(STRING, 8);
                }},
                35,
                35
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(HEMPEN_ROPE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HEMP, 10);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(PACK_SADDLE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SADDLE, 1);
                    put(POUCH, 2);
                    put(STRING, 2);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(POUCH, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 2);
                    put(LEATHER_STRAP, 1);
                    put(STRING, 2);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(BACKPACK, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 4);
                    put(LEATHER_STRAP, 1);
                    put(STRING, 8);
                }},
                15,
                15
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(BOOK, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 2);
                    put(PAPER, 1);
                    put(STRING, 1);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(TRAVELERS_CLOTHES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 1);
                    put(STRING, 4);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(COMMON_CLOTHES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 2);
                    put(STRING, 4);
                }},
                10,
                10
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(COSTUME_CLOTHES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 3);
                    put(STRING, 6);
                    put(IRON_INGOT, 1);
                }},
                20,
                20
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(FINE_CLOTHES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 5);
                    put(STRING, 6);
                    put(IRON_INGOT, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(WARM_CLOTHES, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 2);
                    put(WOOL, 8);
                    put(HIDE, 2);
                    put(STRING, 6);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(PADDED, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ArmorType.LEATHER, 1);
                    put(LEATHER_STRAP, 1);
                    put(WOOL, 1);
                    put(STRING, 6);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(ArmorType.LEATHER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.LEATHER, 1);
                    put(LEATHER_STRAP, 1);
                }},
                45,
                45
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(STUDDED_LEATHER, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ArmorType.LEATHER, 1);
                    put(LEATHER_STRAP, 1);
                    put(NAILS, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(SCALE_MAIL, 1),
                new HashMap<ItemType, Integer>() {{
                    put(ArmorType.LEATHER, 1);
                    put(ARMOR_PLATE, 1);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(ArmorType.HIDE, 1),
                new HashMap<ItemType, Integer>() {{
                    put(AdventuringGearType.HIDE, 1);
                }},
                30,
                30
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(RING_MAIL, 1),
                new HashMap<>(),
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.LOOM,
                professionService.getProfession("Leatherworker"),
                1,
                LEATHERWORKERS_TOOLS,
                emptyList(),
                new PenItemStack(SPLINT, 1),
                new HashMap<>(),
                0,
                0
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(WOODCUTTERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(AXEHEAD, 12);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
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
                Material.ANVIL,
                professionService.getProfession("Blacksmith"),
                1,
                SMITHS_TOOLS,
                emptyList(),
                new PenItemStack(CHEFS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(HANDLE, 8);
                    put(BLADE, 8);
                    put(IRON_INGOT, 4);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(HUNTERS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(SHORTBOW, 1);
                    put(IRON_INGOT, 3);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(FISHERMANS_TOOLS, 1),
                new HashMap<ItemType, Integer>() {{
                    put(FISHING_ROD, 1);
                    put(NET, 2);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(SCAFFOLDING_KIT, 1),
                new HashMap<ItemType, Integer>() {{
                    put(LUMBER, 12);
                }},
                50,
                50
        ));
        recipes.add(new BricksRecipe(
                Material.FLETCHING_TABLE,
                professionService.getProfession("Carpenter"),
                1,
                CARPENTERS_TOOLS,
                emptyList(),
                new PenItemStack(DIVING_TOOLS, 1),
                new HashMap<>(),
                50,
                50
        ));
        plugin.getLogger().info("Recipes loaded (" + recipes.size() + ")");
    }

    public List<BricksRecipe> getRecipes() {
        return recipes;
    }

}
