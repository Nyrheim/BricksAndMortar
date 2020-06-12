package net.nyrheim.bricksandmortar.node;

import org.bukkit.Location;
import org.bukkit.World;

public final class Node {

    private NodeId id;
    private final String name;
    private final World world;
    private final Location minLocation;
    private final Location maxLocation;
    private final DropTable dropTable;

    public Node(NodeId id,
                String name,
                World world,
                Location minLocation,
                Location maxLocation,
                DropTable dropTable) {
        this.id = id;
        this.name = name;
        this.world = world;
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
        this.dropTable = dropTable;
    }

    public Node(String name,
                World world,
                Location minLocation,
                Location maxLocation,
                DropTable dropTable) {
        this(null, name, world, minLocation, maxLocation, dropTable);
    }

    public NodeId getId() {
        return id;
    }

    public void setId(NodeId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public World getWorld() {
        return world;
    }

    public Location getMinLocation() {
        return minLocation;
    }

    public Location getMaxLocation() {
        return maxLocation;
    }

    public DropTable getDropTable() {
        return dropTable;
    }

}
