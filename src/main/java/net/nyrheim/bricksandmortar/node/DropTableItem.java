package net.nyrheim.bricksandmortar.node;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import org.bukkit.inventory.ItemStack;

import static java.util.logging.Level.WARNING;

public final class DropTableItem {

    private final BricksAndMortar plugin;

    private DropTableItemId id;
    private final ItemType itemType;
    private final int amount;
    private final RPKItemQuality quality;
    private final int chance;
    private final ItemType toolType;

    public DropTableItem(BricksAndMortar plugin,
                         DropTableItemId id,
                         ItemType itemType,
                         int amount,
                         RPKItemQuality quality,
                         int chance,
                         ItemType toolType) {
        this.plugin = plugin;
        this.id = id;
        this.itemType = itemType;
        this.amount = amount;
        this.quality = quality;
        this.chance = chance;
        this.toolType = toolType;
    }

    public DropTableItem(BricksAndMortar plugin,
                         ItemType itemType,
                         int amount,
                         RPKItemQuality quality,
                         int chance,
                         ItemType toolType) {
        this(plugin, null, itemType, amount, quality, chance, toolType);
    }

    public DropTableItemId getId() {
        return id;
    }

    public void setId(DropTableItemId id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getAmount() {
        return amount;
    }

    public RPKItemQuality getQuality() {
        return quality;
    }

    public int getChance() {
        return chance;
    }

    public ItemType getToolType() {
        return toolType;
    }

    public ItemStack createItemStack() {
        PenItemStack penItemStack = new PenItemStack(getItemType(), getAmount());
        ItemStack itemStack = penItemStack.toItemStack();
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(WARNING, "Failed to find item quality provider", exception);
            return itemStack;
        }
        if (getQuality() != null) {
            itemQualityProvider.setItemQuality(itemStack, getQuality());
        }
        return itemStack;
    }

}
