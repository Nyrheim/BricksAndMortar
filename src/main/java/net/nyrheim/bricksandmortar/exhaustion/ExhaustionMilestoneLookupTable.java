package net.nyrheim.bricksandmortar.exhaustion;

import static org.bukkit.ChatColor.*;

public class ExhaustionMilestoneLookupTable {

    public static String lookupExhaustionMilestone(int exh, int priorExh) {
        if (exh == 100 && priorExh < 100) {
            return (DARK_RED + "You are physically incapable of moving.");
        }
        else if (exh >= 80 && priorExh < 80) {
            return (RED + "You feel like you're about to collapse.");
        }
        else if (exh >= 60 && priorExh < 60) {
            return (GRAY + "You're absolutely exhausted, you can't carry on for much longer.");
        }
        else if (exh >= 40 && priorExh < 40) {
            return (YELLOW + "You're starting to tire. Perhaps you should take a break.");
        }
        else if (exh >= 20 && priorExh < 20) {
            return (DARK_AQUA + "You feel fine.");
        }
        return null;
    }

}
