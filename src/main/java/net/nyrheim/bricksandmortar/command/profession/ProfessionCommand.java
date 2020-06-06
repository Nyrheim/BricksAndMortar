package net.nyrheim.bricksandmortar.command.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class ProfessionCommand implements CommandExecutor {

    private final ProfessionSetCommand professionSetCommand;
    private final ProfessionListCommand professionListCommand;

    public ProfessionCommand(BricksAndMortar plugin) {
        this.professionSetCommand = new ProfessionSetCommand(plugin);
        this.professionListCommand = new ProfessionListCommand(plugin);
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
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " [set|list]");
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " [set|list]");
        }
        return true;
    }
}
