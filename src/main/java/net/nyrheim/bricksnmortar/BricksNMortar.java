package net.nyrheim.bricksnmortar;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.plugin.java.JavaPlugin;

public class BricksNMortar extends JavaPlugin {

    private PenAndPaper penAndPaper;

    @Override
    public void onEnable() {
        penAndPaper = (PenAndPaper) getServer().getPluginManager().getPlugin("PenAndPaper");
    }

    public PenAndPaper getPenAndPaper() {
        return penAndPaper;
    }

}
