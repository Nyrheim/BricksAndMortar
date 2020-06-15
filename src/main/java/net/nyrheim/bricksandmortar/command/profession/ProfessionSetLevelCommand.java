package net.nyrheim.bricksandmortar.command.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ProfessionSetLevelCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public ProfessionSetLevelCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.profession.setlevel")) {
            sender.sendMessage(RED + "You do not have permission to set profession experience.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "Usage: /" + label + " setlevel [player] [level]");
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(RED + "No player by that name was found.");
            return true;
        }
        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Level must be an integer.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "That player has no active character.");
            return true;
        }
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        if (professionService.getProfession(character) == null) {
            sender.sendMessage(RED + "That player has no profession set.");
            return true;
        }
        professionService.setLevel(character, level);
        sender.sendMessage(GREEN + character.getName() + "'s profession level was set to " + level);
        target.sendMessage(GREEN + "You grew more experienced in your profession.");
        return true;
    }
}
