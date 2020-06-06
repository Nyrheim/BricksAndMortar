package net.nyrheim.bricksandmortar.recipe;

import net.nyrheim.bricksandmortar.gui.RecipeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static org.bukkit.event.inventory.InventoryType.SlotType.CONTAINER;

public final class RecipeGUIInventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof RecipeGUI)) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        if (event.getSlotType() != CONTAINER) {
            return;
        }
        RecipeGUI gui = (RecipeGUI) event.getInventory().getHolder();
        Player player = (Player) event.getWhoClicked();
        gui.onClick(player, event.getSlot());
        event.setCancelled(true);
    }

}
