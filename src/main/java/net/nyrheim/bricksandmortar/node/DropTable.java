package net.nyrheim.bricksandmortar.node;

import net.nyrheim.penandpaper.item.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class DropTable {

    private DropTableId id;
    private final String name;
    private final List<DropTableItem> items = new ArrayList<>();

    public DropTable(DropTableId id, String name, List<DropTableItem> items) {
        this.id = id;
        this.name = name;
        this.items.addAll(items);
    }

    public DropTable(DropTableId id, String name) {
        this.id = id;
        this.name = name;
    }

    public DropTable(String name) {
        this(null, name);
    }

    public DropTableId getId() {
        return id;
    }

    public void setId(DropTableId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<DropTableItem> getItems() {
        return items;
    }

    public void addItem(DropTableItem item) {
        items.add(item);
    }

    public void removeItem(DropTableItem item) {
        items.remove(item);
    }

    public DropTableItem chooseItem(ItemType toolType) {
        List<DropTableItem> validItems = getItems().stream()
                .filter(item -> item.getToolType() == toolType)
                .collect(Collectors.toList());
        if (validItems.isEmpty()) return null;
        int chanceSum = validItems.stream().map(DropTableItem::getChance).reduce(0, Integer::sum);
        Random random = new Random();
        int choice = random.nextInt(chanceSum);
        int sum = 0;
        for (DropTableItem item : validItems) {
            sum += item.getChance();
            if (sum > choice) return item;
        }
        return null;
    }

}
