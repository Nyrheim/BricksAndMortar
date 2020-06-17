package net.nyrheim.bricksandmortar.item;

import org.bukkit.inventory.ItemStack;

public class InteractItemLookup {

    public static boolean isItemInteractSafe(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case BREAD: return true;
            case APPLE: return true;
            case COOKED_BEEF: return true;
            case COOKED_CHICKEN: return true;
            case COOKED_COD: return true;
            case COOKED_MUTTON: return true;
            case COOKED_PORKCHOP: return true;
            case COOKED_RABBIT: return true;
            case COOKED_SALMON: return true;
            default: return false;
        }
    }

}
