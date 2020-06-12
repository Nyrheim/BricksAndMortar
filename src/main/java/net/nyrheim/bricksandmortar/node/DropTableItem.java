package net.nyrheim.bricksandmortar.node;

import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import net.nyrheim.penandpaper.item.ItemType;

public final class DropTableItem {

    private DropTableItemId id;
    private final ItemType itemType;
    private final int amount;
    private final RPKItemQuality quality;
    private final int chance;
    private final ItemType toolType;

    public DropTableItem(DropTableItemId id,
                        ItemType itemType,
                         int amount,
                         RPKItemQuality quality,
                         int chance,
                         ItemType toolType) {
        this.id = id;
        this.itemType = itemType;
        this.amount = amount;
        this.quality = quality;
        this.chance = chance;
        this.toolType = toolType;
    }

    public DropTableItem(ItemType itemType,
                         int amount,
                         RPKItemQuality quality,
                         int chance,
                         ItemType toolType) {
        this(null, itemType, amount, quality, chance, toolType);
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

}
