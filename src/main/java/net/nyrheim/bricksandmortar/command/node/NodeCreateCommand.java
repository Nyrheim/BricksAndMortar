package net.nyrheim.bricksandmortar.command.node;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.players.bukkit.profile.RPKMinecraftProfile;
import com.rpkit.players.bukkit.profile.RPKMinecraftProfileProvider;
import com.rpkit.selection.bukkit.selection.RPKSelection;
import com.rpkit.selection.bukkit.selection.RPKSelectionProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.Node;
import net.nyrheim.bricksandmortar.node.BricksNodeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.util.logging.Level.SEVERE;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class NodeCreateCommand implements CommandExecutor {

    private final BricksAndMortar plugin;

    public NodeCreateCommand(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("bricksandmortar.command.node.create")) {
            sender.sendMessage(RED + "You do not have permission to create nodes.");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        RPKMinecraftProfileProvider minecraftProfileProvider;
        try {
            minecraftProfileProvider = plugin.core.getServiceManager().getServiceProvider(RPKMinecraftProfileProvider.class);
        } catch (UnregisteredServiceException exception) {
            sender.sendMessage(RED + "An internal error occurred. Please report this to an admin.");
            plugin.getLogger().log(SEVERE, "Failed to find Minecraft profile provider", exception);
            return true;
        }
        RPKMinecraftProfile minecraftProfile = minecraftProfileProvider.getMinecraftProfile(player);
        if (minecraftProfile == null) {
            sender.sendMessage(RED + "You do not currently have a Minecraft profile. This is probably in error, try relogging or contact an admin.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "Usage: /" + label + " create [name] [drop table]");
            return true;
        }
        String name = args[0];
        RPKSelectionProvider selectionProvider;
        try {
            selectionProvider = plugin.core.getServiceManager().getServiceProvider(RPKSelectionProvider.class);
        } catch (UnregisteredServiceException exception) {
            sender.sendMessage(RED + "An internal error occurred. Please report this to an admin.");
            plugin.getLogger().log(SEVERE, "Failed to find selection provider", exception);
            return true;
        }
        RPKSelection selection = selectionProvider.getSelection(minecraftProfile);
        BricksNodeService nodeService = plugin.getServices().get(BricksNodeService.class);
        String dropTableName = args[1];
        DropTable dropTable = nodeService.getDropTable(dropTableName);
        if (dropTable == null) {
            sender.sendMessage(RED + "There is no drop table by that name.");
            return true;
        }
        nodeService.addNode(new Node(
                name,
                selection.getWorld(),
                selection.getMinimumPoint().getLocation(),
                selection.getMaximumPoint().getLocation(),
                dropTable
        ));
        sender.sendMessage(GREEN + "Node \"" + name + "\" created.");
        return true;
    }
}
