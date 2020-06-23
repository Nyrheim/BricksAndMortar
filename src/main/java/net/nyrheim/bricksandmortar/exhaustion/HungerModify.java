package net.nyrheim.bricksandmortar.exhaustion;

import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.entity.Player;

public class HungerModify {

    public static void updateHunger(Player player) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel > 15) {
            player.setFoodLevel(foodLevel - 4);
        }
        else if (foodLevel < 15 && foodLevel > 10) {
            player.setFoodLevel(foodLevel - 2);
        }
        else if (foodLevel <= 10) {
            player.setFoodLevel(foodLevel - 1);
        }
    }

    public static void updateHunger(Player player, int itemExh) {
        int foodLevel = player.getFoodLevel();
        double exhMod = Math.round(itemExh / 5.0);
        if (foodLevel > 15) {
            player.setFoodLevel(foodLevel - (6 + (int) exhMod));
        }
        else if (foodLevel < 15 && foodLevel > 10) {
            player.setFoodLevel(foodLevel - (4 + (int) exhMod));
        }
        else if (foodLevel <= 10) {
            player.setFoodLevel(foodLevel - (2 + (int) exhMod));
        }
    }

}
