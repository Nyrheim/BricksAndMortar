package net.nyrheim.bricksandmortar.item;

import org.bukkit.inventory.ItemStack;

public final class InteractItemLookup {

    private InteractItemLookup() {}

    public static boolean isItemInteractSafe(ItemStack itemStack) {
        return itemStack.getType().isEdible();
    }

}
