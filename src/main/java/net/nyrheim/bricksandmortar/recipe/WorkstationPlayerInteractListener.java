package net.nyrheim.bricksandmortar.recipe;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.gui.RecipeGUI;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

import static org.bukkit.Material.AIR;

public final class WorkstationPlayerInteractListener implements Listener {

    private final BricksAndMortar plugin;

    public WorkstationPlayerInteractListener(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        BricksRecipeService recipeService = plugin.getServices().get(BricksRecipeService.class);
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            exception.printStackTrace();
            return;
        }
        Set<Material> workstations = recipeService.getRecipes().stream()
                .map(BricksRecipe::getWorkstation).collect(Collectors.toSet());
        if (workstations.contains(clickedBlock.getType())) {
            event.setCancelled(true);
            List<BricksRecipe> recipes = recipeService.getRecipes().stream()
                    .filter(potentialRecipe -> {
                        if (potentialRecipe.getWorkstation() != clickedBlock.getType()) return false;
                        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
                        if (itemInHand.getType() == AIR) return false;
                        if (!potentialRecipe.getPermittedToolkitQualities().isEmpty()) {
                            RPKItemQuality itemQuality = itemQualityProvider.getItemQuality(itemInHand);
                            if (itemQuality == null) return false;
                            if (!potentialRecipe.getPermittedToolkitQualities().stream()
                                    .map(RPKItemQuality::getName)
                                    .collect(Collectors.toSet())
                                    .contains(itemQuality.getName())) return false;
                        }
                        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
                        PenPlayer penPlayer = playerService.getPlayer(event.getPlayer());
                        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
                        PenCharacter character = characterService.getActiveCharacter(penPlayer);
                        if (character == null) return false;
                        if (character.getExhaustion() + potentialRecipe.getExhaustion() > 100) return false;
                        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
                        if (professionService.getProfession(character) != potentialRecipe.getProfession()) return false;
                        if (professionService.getLevel(character) < potentialRecipe.getMinimumLevel()) return false;
                        PenItemStack penItemInHand = PenItemStack.fromItemStack(itemInHand);
                        if (penItemInHand == null) return false;
                        if (penItemInHand.getType() != potentialRecipe.getToolkit()) return false;
                        ItemStack[] inventoryContents = event.getPlayer().getInventory().getContents();
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
                        for (Map.Entry<ItemType, Integer> ingredient : potentialRecipe.getIngredients().entrySet()) {
                            if (penItemCounts.getOrDefault(ingredient.getKey(), 0) < ingredient.getValue()) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            RecipeGUI gui = new RecipeGUI(plugin, recipes);
            gui.openInventory(event.getPlayer());
        }
    }

}
