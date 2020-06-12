package net.nyrheim.bricksandmortar.node;

import java.util.ArrayList;
import java.util.List;

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

}
