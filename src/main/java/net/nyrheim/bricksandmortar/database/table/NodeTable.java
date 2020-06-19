package net.nyrheim.bricksandmortar.database.table;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.node.DropTableId;
import net.nyrheim.bricksandmortar.node.Node;
import net.nyrheim.bricksandmortar.node.NodeId;
import org.bukkit.Location;
import org.bukkit.World;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;

import java.util.List;

import static net.nyrheim.bricksandmortar.database.jooq.Tables.DROP_TABLE;
import static net.nyrheim.bricksandmortar.database.jooq.Tables.NODE;
import static org.jooq.impl.DSL.constraint;

public final class NodeTable implements Table {

    private final BricksAndMortar plugin;
    private final Database database;

    private final Cache<Integer, Node> cache;
    private final Cache<String, Integer> nameCache;

    public NodeTable(BricksAndMortar plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        this.cache = database.getCacheManager().createCache("bricksandmortar.node.id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Node.class,
                        ResourcePoolsBuilder.heap(20L)).build());
        this.nameCache = database.getCacheManager().createCache("bricksandmortar.node.name",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Integer.class,
                        ResourcePoolsBuilder.heap(20L)).build());
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(NODE)
                .column(NODE.ID)
                .column(NODE.NAME)
                .column(NODE.WORLD)
                .column(NODE.MIN_X)
                .column(NODE.MIN_Y)
                .column(NODE.MIN_Z)
                .column(NODE.MAX_X)
                .column(NODE.MAX_Y)
                .column(NODE.MAX_Z)
                .column(NODE.DROP_TABLE_ID)
                .constraints(
                        constraint("node_pk").primaryKey(NODE.ID),
                        constraint("node_drop_table_id_fk")
                                .foreignKey(NODE.DROP_TABLE_ID)
                                .references(DROP_TABLE, DROP_TABLE.ID)
                                .onDeleteRestrict()
                                .onUpdateCascade()
                )
                .execute();
    }

    public void insert(Node node) {
        database.create()
                .insertInto(
                        NODE,
                        NODE.NAME,
                        NODE.WORLD,
                        NODE.MIN_X,
                        NODE.MIN_Y,
                        NODE.MIN_Z,
                        NODE.MAX_X,
                        NODE.MAX_Y,
                        NODE.MAX_Z,
                        NODE.DROP_TABLE_ID
                )
                .values(
                        node.getName(),
                        node.getWorld().getName(),
                        node.getMinLocation().getBlockX(),
                        node.getMinLocation().getBlockY(),
                        node.getMinLocation().getBlockZ(),
                        node.getMaxLocation().getBlockX(),
                        node.getMaxLocation().getBlockY(),
                        node.getMaxLocation().getBlockZ(),
                        node.getDropTable().getId().getValue()
                )
                .execute();
        int id = database.create().lastID().intValue();
        node.setId(new NodeId(id));
        cache.put(id, node);
        nameCache.put(node.getName(), id);
    }

    public void update(Node node) {
        database.create()
                .update(NODE)
                .set(NODE.NAME, node.getName())
                .set(NODE.WORLD, node.getWorld().getName())
                .set(NODE.MIN_X, node.getMinLocation().getBlockX())
                .set(NODE.MIN_Y, node.getMinLocation().getBlockY())
                .set(NODE.MIN_Z, node.getMinLocation().getBlockZ())
                .set(NODE.DROP_TABLE_ID, node.getDropTable().getId().getValue())
                .where(NODE.ID.eq(node.getId().getValue()))
                .execute();
        cache.put(node.getId().getValue(), node);
        nameCache.put(node.getName(), node.getId().getValue());
    }

    public Node get(NodeId id) {
        if (cache.containsKey(id.getValue())) {
            return cache.get(id.getValue());
        }
        Record result = database.create()
                .select(
                        NODE.NAME,
                        NODE.WORLD,
                        NODE.MIN_X,
                        NODE.MIN_Y,
                        NODE.MIN_Z,
                        NODE.MAX_X,
                        NODE.MAX_Y,
                        NODE.MAX_Z,
                        NODE.DROP_TABLE_ID
                )
                .from(NODE)
                .where(NODE.ID.eq(id.getValue()))
                .fetchOne();
        if (result == null) return null;
        World world = plugin.getServer().getWorld(result.get(NODE.WORLD));
        if (world == null) return null;
        Node node = new Node(
                id,
                result.get(NODE.NAME),
                world,
                new Location(
                        world,
                        result.get(NODE.MIN_X),
                        result.get(NODE.MIN_Y),
                        result.get(NODE.MIN_Z)
                ),
                new Location(
                        world,
                        result.get(NODE.MAX_X),
                        result.get(NODE.MAX_Y),
                        result.get(NODE.MAX_Z)
                ),
                database.getTable(DropTableTable.class).get(new DropTableId(result.get(NODE.DROP_TABLE_ID)))
        );
        cache.put(id.getValue(), node);
        nameCache.put(node.getName(), id.getValue());
        return node;
    }

    public Node get(String name) {
        if (nameCache.containsKey(name)) {
            return get(new NodeId(nameCache.get(name)));
        }
        Record result = database.create()
                .select(
                        NODE.ID,
                        NODE.WORLD,
                        NODE.MIN_X,
                        NODE.MIN_Y,
                        NODE.MIN_Z,
                        NODE.MAX_X,
                        NODE.MAX_Y,
                        NODE.MAX_Z
                )
                .from(NODE)
                .where(NODE.NAME.eq(name))
                .fetchOne();
        if (result == null) return null;
        World world = plugin.getServer().getWorld(result.get(NODE.WORLD));
        if (world == null) return null;
        Node node = new Node(
                new NodeId(result.get(NODE.ID)),
                name,
                world,
                new Location(
                        world,
                        result.get(NODE.MIN_X),
                        result.get(NODE.MIN_Y),
                        result.get(NODE.MIN_Z)
                ),
                new Location(
                        world,
                        result.get(NODE.MAX_X),
                        result.get(NODE.MAX_Y),
                        result.get(NODE.MAX_Z)
                ),
                database.getTable(DropTableTable.class).get(new DropTableId(result.get(NODE.DROP_TABLE_ID)))
        );
        cache.put(node.getId().getValue(), node);
        nameCache.put(node.getName(), node.getId().getValue());
        return node;
    }

    public List<Node> getNodesAt(Location location) {
        Result<Record1<Integer>> results = database.create()
                .select(NODE.ID)
                .from(NODE)
                .where(NODE.WORLD.eq(location.getWorld().getName()))
                .and(NODE.MAX_X.greaterOrEqual(location.getBlockX()))
                .and(NODE.MAX_Y.greaterOrEqual(location.getBlockY()))
                .and(NODE.MAX_Z.greaterOrEqual(location.getBlockZ()))
                .and(NODE.MIN_X.lessOrEqual(location.getBlockX()))
                .and(NODE.MIN_Y.lessOrEqual(location.getBlockY()))
                .and(NODE.MIN_Z.lessOrEqual(location.getBlockZ()))
                .fetch();
        return results.map(result -> get(new NodeId(result.get(NODE.ID))));
    }

    public void delete(Node node) {
        database.create()
                .deleteFrom(NODE)
                .where(NODE.ID.eq(node.getId().getValue()))
                .execute();
        cache.remove(node.getId().getValue());
        nameCache.remove(node.getName());
    }

}
