package net.nyrheim.bricksandmortar.gui;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.profession.ProfessionExperienceLookupTable;
import net.nyrheim.bricksandmortar.recipe.BricksRecipe;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.exhaustion.ExhaustionTier;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.Material.AIR;
import static org.bukkit.Sound.BLOCK_ANVIL_USE;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public final class RecipeGUI implements InventoryHolder {

    private final BricksAndMortar plugin;
    private final Inventory inventory;
    private final List<BricksRecipe> recipes;
    private int page = 0;

    public RecipeGUI(BricksAndMortar plugin, List<BricksRecipe> recipes) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, 27, "Recipes");
        this.recipes = recipes;
        showCurrentPage();
    }

    private void showCurrentPage() {
        showPage(page);
    }

    private void showPage(int page) {
        getInventory().clear();
        recipes.stream()
                .skip(page * 18)
                .limit(18)
                .forEach(recipe -> {
                    ItemStack item = recipe.getResult().toItemStack();
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        List<String> lore = meta.getLore();
                        if (lore == null) {
                            lore = new ArrayList<>();
                        }
                        lore.add(WHITE + "Ingredients: ");
                        lore.addAll(recipe.getIngredients().entrySet().stream()
                                .map(entry -> WHITE + "\u2022 " + entry.getValue() + " \u00d7 " + entry.getKey().getName())
                                .collect(Collectors.toList()));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                    }
                    getInventory().addItem(item);
                });
        if (page > 0) {
            ItemStack previousArrow = new ItemStack(Material.LIME_WOOL);
            ItemMeta meta = previousArrow.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(WHITE + "Previous page");
                previousArrow.setItemMeta(meta);
            }
            getInventory().setItem(18, previousArrow);
        }
        if ((page + 1) * 18 <= recipes.size()) {
            ItemStack nextArrow = new ItemStack(Material.LIME_WOOL);
            ItemMeta meta = nextArrow.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(WHITE + "Next page");
                nextArrow.setItemMeta(meta);
            }
            getInventory().setItem(26, nextArrow);
        }
    }

    public void onClick(Player player, int slot) {
        if (slot == 18 && page > 0) {
            page--;
            showCurrentPage();
            return;
        }
        if (slot == 26 && (page + 1) * 18 <= recipes.size()) {
            page++;
            showCurrentPage();
            return;
        }
        if (slot >= 18) return;
        BricksRecipe recipe = recipes.stream().skip((page * 18) + slot).findFirst().orElse(null);
        if (recipe != null) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand.getType() == AIR) return;
            RPKItemQualityProvider itemQualityProvider;
            try {
                itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
            } catch (UnregisteredServiceException exception) {
                exception.printStackTrace();
                return;
            }
            if (!recipe.getPermittedToolkitQualities().isEmpty()) {
                RPKItemQuality itemQuality = itemQualityProvider.getItemQuality(itemInHand);
                if (itemQuality == null) return;
                if (!recipe.getPermittedToolkitQualities().stream()
                        .map(RPKItemQuality::getName)
                        .collect(Collectors.toSet())
                        .contains(itemQuality.getName())) return;
            }
            PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
            PenPlayer penPlayer = playerService.getPlayer(player);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            PenCharacter character = characterService.getActiveCharacter(penPlayer);
            if (character == null) return;
            if (character.getExhaustion() + recipe.getExhaustion() > 100) return;
            BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
            if (professionService.getProfession(character) != recipe.getProfession()) return;
            if (professionService.getLevel(character) < recipe.getMinimumLevel()) return;
            PenItemStack penItemInHand = PenItemStack.fromItemStack(itemInHand);
            if (penItemInHand == null) return;
            if (penItemInHand.getType() != recipe.getToolkit()) return;
            ItemStack[] inventoryContents = player.getInventory().getContents();
            PenItemStack[] penContents = Arrays.stream(inventoryContents)
                    .map(item -> item == null ? null : PenItemStack.fromItemStack(item))
                    .filter(Objects::nonNull)
                    .toArray(PenItemStack[]::new);
            Map<ItemType, Integer> penItemCounts = new HashMap<>();
            for (PenItemStack penItem : penContents) {
                int amount = penItemCounts.getOrDefault(penItem.getType(), 0);
                amount += penItem.getAmount();
                penItemCounts.put(penItem.getType(), amount);
            }
            for (Map.Entry<ItemType, Integer> ingredient : recipe.getIngredients().entrySet()) {
                if (penItemCounts.getOrDefault(ingredient.getKey(), 0) < ingredient.getValue()) {
                    return;
                }
            }
            for (Map.Entry<ItemType, Integer> ingredient : recipe.getIngredients().entrySet()) {
                ItemType itemType = ingredient.getKey();
                int amountRequired = ingredient.getValue();
                for (int i = 0; i < player.getInventory().getSize(); i++) {
                    ItemStack item = player.getInventory().getItem(i);
                    if (item == null) continue;
                    PenItemStack penItemStack = PenItemStack.fromItemStack(item);
                    if (penItemStack == null) continue;
                    if (penItemStack.getType() == itemType) {
                        if (item.getAmount() > amountRequired) {
                            item.setAmount(item.getAmount() - amountRequired);
                            player.getInventory().setItem(i, item);
                            amountRequired = 0;
                        } else {
                            player.getInventory().setItem(i, null);
                            amountRequired -= item.getAmount();
                        }
                    }
                }
            }
            player.getInventory().addItem(recipe.getResult().toItemStack())
                    .values()
                    .forEach(item -> player.getWorld().dropItem(player.getLocation(), item));
            updateExhaustion(player, recipe, characterService, character);
            player.setFoodLevel(player.getFoodLevel() - 1);
            updateExperience(player, recipe, character, professionService);
            player.playSound(player.getLocation(), BLOCK_ANVIL_USE, 1f, 1f);
            player.closeInventory();
        }
    }

    private void updateExhaustion(Player player, BricksRecipe recipe, PenCharacterService characterService, PenCharacter character) {
        int oldExhaustion = character.getExhaustion();
        int newExhaustion = min(character.getExhaustion() + recipe.getExhaustion() + getFoodExhaustionModifier(player), 100);
        character.setExhaustion(newExhaustion);
        characterService.updateCharacter(character);
        ExhaustionTier oldExhaustionTier = ExhaustionTier.forExhaustionValue(oldExhaustion);
        ExhaustionTier newExhaustionTier = ExhaustionTier.forExhaustionValue(newExhaustion);
        if (oldExhaustionTier != newExhaustionTier) {
            player.sendMessage(newExhaustionTier.getMessageSelf());
        }
    }

    private void updateExperience(Player player, BricksRecipe recipe, PenCharacter character, BricksProfessionService professionService) {
        int oldExperience = professionService.getExperience(character);
        int oldLevel = ProfessionExperienceLookupTable.getLevelAtExperience(oldExperience);
        int newExperience = oldExperience + recipe.getExperience();
        professionService.setExperience(character, newExperience);
        int newLevel = ProfessionExperienceLookupTable.getLevelAtExperience(newExperience);
        if (newLevel > oldLevel) {
            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage(YELLOW + "Your extensive work has led to an improvement in your knowledge.");
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(getInventory());
    }

    private int getFoodExhaustionModifier(Player player) {
        return getFoodExhaustionModifier(player.getFoodLevel());
    }

    private int getFoodExhaustionModifier(int foodLevel) {
        if (foodLevel >= 17) return -2;
        if (foodLevel >= 11) return -1;
        if (foodLevel >= 10) return 0;
        if (foodLevel >= 8) return 3;
        if (foodLevel >= 6) return 6;
        if (foodLevel >= 4) return 8;
        if (foodLevel >= 2) return 12;
        return 15;
    }

}
