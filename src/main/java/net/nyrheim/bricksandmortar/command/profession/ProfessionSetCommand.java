package net.nyrheim.bricksandmortar.command.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.profession.Profession;
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

public final class ProfessionSetCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public ProfessionSetCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " set [profession]");
            return true;
        }
        Player player = (Player) sender;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        String professionName = args[0];
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        Profession profession = professionService.getProfession(professionName);
        if (profession == null) {
            sender.sendMessage(RED + "There is no profession by that name. See /profession list.");
            return true;
        }
        if (professionService.getProfession(character) != null) {
            sender.sendMessage(RED + "You already have a profession.");
            return true;
        }
        professionService.setProfession(character, profession);
        sender.sendMessage(GREEN + "Profession set to " + profession.getName() + ".");
        return true;
    }
}