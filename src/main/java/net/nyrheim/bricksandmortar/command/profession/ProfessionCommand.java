package net.nyrheim.bricksandmortar.command.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.profession.Profession;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ProfessionCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    private final ProfessionSetCommand professionSetCommand;
    private final ProfessionListCommand professionListCommand;
    private final ProfessionSetLevelCommand professionSetLevelCommand;

    public ProfessionCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
        this.professionSetCommand = new ProfessionSetCommand(plugin);
        this.professionListCommand = new ProfessionListCommand(plugin);
        this.professionSetLevelCommand = new ProfessionSetLevelCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "set":
                    return professionSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "list":
                    return professionListCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "setlevel":
                    return professionSetLevelCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    Player target = plugin.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(RED + "There is no player online by that name.");
                        return true;
                    }
                    showProfession(sender, target);
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(RED + "You must specify a player when running this command from console.");
                return true;
            }
            Player player = (Player) sender;
            showProfession(sender, player);
        }
        return true;
    }

    private void showProfession(CommandSender sender, Player target) {
        if (!sender.hasPermission("bricksandmortar.command.profession")) {
            sender.sendMessage(RED + "You do not have permission to view your profession level.");
            return;
        }
        if (target != sender && !sender.hasPermission("bricksandmortar.command.profession.other")) {
            sender.sendMessage(RED + "You do not have permission to view other's profession level.");
            return;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return;
        }
        Profession characterProf = professionService.getProfession(character);
        int characterProfLevel = professionService.getLevel(character);
        if (characterProf == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have a profession.");
            return;
        }
        sender.sendMessage(GREEN + (target == sender ? "You are a " : (character.getName() + " is a "))
                + "Level " + characterProfLevel + " " + characterProf.getName() + ".");
    }
}
