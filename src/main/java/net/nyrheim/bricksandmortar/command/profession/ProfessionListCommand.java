package net.nyrheim.bricksandmortar.command.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;

public final class ProfessionListCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public ProfessionListCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        BricksProfessionService professionService = plugin.getServices().get(BricksProfessionService.class);
        sender.sendMessage(GREEN + "Professions: ");
        professionService.getProfessions().forEach((profession) -> {
            sender.sendMessage(GREEN + "\u2022 " + profession.getName());
        });
        return true;
    }
}
