package net.nyrheim.bricksandmortar.node;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.profession.Profession;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.exhaustion.ExhaustionTier;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static java.lang.Math.min;
import static org.bukkit.ChatColor.*;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;
import static org.bukkit.inventory.EquipmentSlot.HAND;

public final class NodePlayerInteractListener implements Listener {

    private final BricksAndMortar plugin;

    public NodePlayerInteractListener(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != HAND) return;
        if (!event.hasBlock()) return;
        Block block = event.getClickedBlock();
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(event.getPlayer());
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            event.getPlayer().sendMessage(RED + "You do not have an active character.");
            return;
        }
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        List<Node> nodes = nodeService.getNodesAt(block.getLocation());
        if (nodes.isEmpty()) {
            return;
        }
        if (nodes.size() > 1) {
            event.getPlayer().sendMessage(RED + "This area has multiple overlapping nodes defined, please contact staff to fix this.");
            return;
        }
        Node node = nodes.get(0);
        PenItemStack tool = PenItemStack.fromItemStack(event.getPlayer().getInventory().getItemInMainHand());
        if (tool == null) return;
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        Profession profession = professionService.getProfession(character);
        if (profession == null) {
            event.getPlayer().sendMessage(RED + "You must set your profession before interacting with nodes.");
            return;
        }
        if (!profession.getToolTypes().contains(tool.getType())) {
            event.getPlayer().sendMessage(RED + "You do not know how to use those tools.");
            return;
        }
        if (character.getExhaustion() >= 100) {
            event.getPlayer().sendMessage(RED + "You are too exhausted to do that!");
            return;
        }
        DropTableItem drop = node.getDropTable().chooseItem(tool.getType());
        if (drop == null) {
            return;
        }
        ItemStack dropItemStack = drop.createItemStack();
        if (dropItemStack != null) {
            Player player = event.getPlayer();
            updateExhaustion(characterService, character, player);
            updateHunger(player);
            block.getWorld().dropItemNaturally(block.getRelative(event.getBlockFace()).getLocation(), dropItemStack);
            player.sendMessage(GREEN + "You got: " + drop.getAmount() + " \u00d7 " +
                    (drop.getQuality() != null ? drop.getQuality().getName() + " " : "")
                    + drop.getItemType().getName());
        }
    }

    private void updateExhaustion(PenCharacterService characterService, PenCharacter character, Player player) {
        int oldExhaustion = character.getExhaustion();
        int newExhaustion = min(character.getExhaustion() + plugin.getConfig().getInt("nodes.exhaustion") + getExhaustionFoodModifier(player), 100);
        character.setExhaustion(newExhaustion);
        characterService.updateCharacter(character);
        ExhaustionTier oldExhaustionTier = ExhaustionTier.forExhaustionValue(oldExhaustion);
        ExhaustionTier newExhaustionTier = ExhaustionTier.forExhaustionValue(newExhaustion);
        if (oldExhaustionTier != newExhaustionTier) {
            player.sendMessage(newExhaustionTier.getMessageSelf());
        }
    }

    private void updateHunger(Player player) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel > 15) {
            player.setFoodLevel(foodLevel - 2);
        } else {
            player.setFoodLevel(foodLevel - 1);
        }
    }

    private int getExhaustionFoodModifier(Player player) {
        return getExhaustionFoodModifier(player.getFoodLevel());
    }

    private int getExhaustionFoodModifier(int foodLevel) {
        if (foodLevel >= 17) return -2;
        if (foodLevel >= 11) return -1;
        if (foodLevel >= 10) return 0;
        if (foodLevel >= 8) return 1;
        if (foodLevel >= 6) return 2;
        if (foodLevel >= 4) return 3;
        if (foodLevel >= 2) return 4;
        return 5;
    }

}
