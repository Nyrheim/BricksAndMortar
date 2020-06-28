package net.nyrheim.bricksandmortar.item;

import org.bukkit.inventory.ItemStack;

public final class InteractItemLookup {

    private InteractItemLookup() {}

    public static boolean isItemInteractSafe(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case BREAD:
            case APPLE:
            case COOKED_BEEF:
            case COOKED_CHICKEN:
            case COOKED_COD:
            case COOKED_MUTTON:
            case COOKED_PORKCHOP:
            case COOKED_RABBIT:
            case COOKED_SALMON:
                return true;
            default: return false;
        }
    }

}
