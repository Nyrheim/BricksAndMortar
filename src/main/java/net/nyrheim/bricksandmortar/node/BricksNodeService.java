package net.nyrheim.bricksandmortar.node;

import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.database.table.DropTableItemTable;
import net.nyrheim.bricksandmortar.database.table.DropTableTable;
import net.nyrheim.bricksandmortar.database.table.NodeTable;
import org.bukkit.Location;

import java.util.List;

public final class BricksNodeService {

    private final Database database;

    public BricksNodeService(Database database) {
        this.database = database;
    }

    public Node getNode(NodeId id) {
        return database.getTable(NodeTable.class)
                .get(id);
    }

    public Node getNode(String name) {
        return database.getTable(NodeTable.class).get(name);
    }

    public void addNode(Node node) {
        database.getTable(NodeTable.class).insert(node);
    }

    public void updateNode(Node node) {
        database.getTable(NodeTable.class).update(node);
    }

    public void deleteNode(Node node) {
        database.getTable(NodeTable.class).delete(node);
    }

    public DropTable getDropTable(DropTableId id) {
        return database.getTable(DropTableTable.class)
                .get(id);
    }

    public DropTable getDropTable(String name) {
        return database.getTable(DropTableTable.class)
                .get(name);
    }

    public List<DropTable> getDropTables() {
        return database.getTable(DropTableTable.class)
                .getAll();
    }

    public void addDropTable(DropTable dropTable) {
        database.getTable(DropTableTable.class).insert(dropTable);
    }

    public void updateDropTable(DropTable dropTable) {
        database.getTable(DropTableTable.class).update(dropTable);
    }

    public void deleteDropTable(DropTable dropTable) {
        database.getTable(DropTableTable.class).delete(dropTable);
    }

    public DropTableItem getDropTableItem(DropTableItemId dropTableItemId) {
        return database.getTable(DropTableItemTable.class).get(dropTableItemId);
    }

    public DropTable getDropTable(DropTableItem dropTableItem) {
        return database.getTable(DropTableItemTable.class).getDropTable(dropTableItem);
    }

    public List<Node> getNodesAt(Location location) {
        return database.getTable(NodeTable.class).getNodesAt(location);
    }

}
