package net.nyrheim.bricksandmortar.database.table;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.DropTableId;
import net.nyrheim.bricksandmortar.node.DropTableItem;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

import java.util.List;

import static net.nyrheim.bricksandmortar.database.jooq.Tables.DROP_TABLE;
import static org.jooq.impl.DSL.constraint;

public final class DropTableTable implements Table {

    private final BricksAndMortar plugin;
    private final Database database;

    private final Cache<Integer, DropTable> cache;
    private final Cache<String, Integer> nameCache;

    public DropTableTable(BricksAndMortar plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        this.cache = database.getCacheManager().createCache("bricksandmortar.drop_table.id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, DropTable.class,
                        ResourcePoolsBuilder.heap(20L)));
        this.nameCache = database.getCacheManager().createCache("bricksandmortar.drop_table.name",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Integer.class,
                        ResourcePoolsBuilder.heap(20L)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(DROP_TABLE)
                .column(DROP_TABLE.ID)
                .column(DROP_TABLE.NAME)
                .constraints(
                        constraint("drop_table_pk").primaryKey(DROP_TABLE.ID)
                )
                .execute();
    }

    public DropTable get(DropTableId id) {
        if (cache.containsKey(id.getValue())) {
            return cache.get(id.getValue());
        }
        Record result = database.create()
                .select(DROP_TABLE.NAME)
                .from(DROP_TABLE)
                .where(DROP_TABLE.ID.eq(id.getValue()))
                .fetchOne();
        if (result == null) return null;
        DropTableItemTable itemTable = database.getTable(DropTableItemTable.class);
        List<DropTableItem> items = itemTable.get(id);
        DropTable dropTable = new DropTable(
                id,
                result.get(DROP_TABLE.NAME),
                items
        );
        cache.put(id.getValue(), dropTable);
        nameCache.put(dropTable.getName(), id.getValue());
        return dropTable;
    }

    public DropTable get(String name) {
        if (nameCache.containsKey(name)) {
            return get(new DropTableId(nameCache.get(name)));
        }
        Record result = database.create()
                .select(DROP_TABLE.ID)
                .from(DROP_TABLE)
                .where(DROP_TABLE.NAME.eq(name))
                .fetchOne();
        if (result == null) return null;
        DropTableItemTable itemTable = database.getTable(DropTableItemTable.class);
        DropTableId id = new DropTableId(result.get(DROP_TABLE.ID));
        List<DropTableItem> items = itemTable.get(id);
        DropTable dropTable = new DropTable(
                id,
                name,
                items
        );
        cache.put(id.getValue(), dropTable);
        nameCache.put(dropTable.getName(), id.getValue());
        return dropTable;
    }

    public List<DropTable> getAll() {
        return database.create()
                .select(DROP_TABLE.ID)
                .from(DROP_TABLE)
                .fetch()
                .map(result -> get(new DropTableId(result.get(DROP_TABLE.ID))));
        //TODO maybe make the full object instead of just invoking get idk.
        // its going to miss the cache anyway but we might as well reduce queries.
    }

    public void insert(DropTable dropTable) {
        database.create()
                .insertInto(
                        DROP_TABLE,
                        DROP_TABLE.NAME
                )
                .values(
                        dropTable.getName()
                )
                .execute();
        DropTableId id = new DropTableId(database.create().lastID().intValue());
        dropTable.setId(id);
        DropTableItemTable itemTable = database.getTable(DropTableItemTable.class);
        itemTable.insertOrUpdate(dropTable);
        cache.put(dropTable.getId().getValue(), dropTable);
        nameCache.put(dropTable.getName(), id.getValue());
    }

    public void update(DropTable dropTable) {
        database.create()
                .update(DROP_TABLE)
                .set(DROP_TABLE.NAME, dropTable.getName())
                .where(DROP_TABLE.ID.eq(dropTable.getId().getValue()))
                .execute();
        DropTableItemTable itemTable = database.getTable(DropTableItemTable.class);
        itemTable.insertOrUpdate(dropTable);
        cache.put(dropTable.getId().getValue(), dropTable);
        nameCache.put(dropTable.getName(), dropTable.getId().getValue());
    }

    public void delete(DropTable dropTable) {
        database.create()
                .deleteFrom(DROP_TABLE)
                .where(DROP_TABLE.ID.eq(dropTable.getId().getValue()))
                .execute();
        cache.remove(dropTable.getId().getValue());
        nameCache.remove(dropTable.getName());
    }
}
