package net.nyrheim.bricksandmortar.item;

import net.nyrheim.penandpaper.item.PenItemStack;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class ItemPlayerInteractListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (InteractItemLookup.isItemInteractSafe(item)) return;
        PenItemStack penItemStack = PenItemStack.fromItemStack(item);
        if (penItemStack == null) return;
        event.setCancelled(true);
    }

}
