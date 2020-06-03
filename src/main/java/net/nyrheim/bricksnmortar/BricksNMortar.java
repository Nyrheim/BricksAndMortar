package net.nyrheim.bricksnmortar;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.plugin.java.JavaPlugin;

public class BricksNMortar extends JavaPlugin {

    private Pen pnp;

    @Override
    public void onEnable() {
        pnp = (Pen) getServer().getPluginManager().getPlugin("PenandPaper");
    }

    public Pen getPnP() {
        return pnp;
    }

}
