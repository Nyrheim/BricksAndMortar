package net.nyrheim.bricksandmortar.exhaustion;

import org.bukkit.entity.Player;

public class ExhaustionFoodModifierLookupTable {

    public static int lookupExhaustionFoodModifierGatherer(Player player, int exh) {
        int foodLevel = player.getFoodLevel();
        switch (foodLevel) {
            case 20: return -4;
            case 19: case 18: case 17: return -3;
            case 16: case 15: case 14: return -2;
            case 13: case 12: case 11: return -1;
            case 10: return 0;
            case 9: case 8: return 1;
            case 7: case 6: return 2;
            case 5: case 4: return 3;
            case 3: case 2: return 4;
            case 1: case 0: return 5;
        }
        return 5;
    }
}
