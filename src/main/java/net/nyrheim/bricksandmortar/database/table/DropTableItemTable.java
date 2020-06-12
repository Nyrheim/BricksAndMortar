package net.nyrheim.bricksandmortar.database.table;

import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.itemquality.bukkit.itemquality.RPKItemQualityProvider;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.node.DropTable;
import net.nyrheim.bricksandmortar.node.DropTableId;
import net.nyrheim.bricksandmortar.node.DropTableItem;
import net.nyrheim.bricksandmortar.node.DropTableItemId;
import net.nyrheim.penandpaper.item.ItemType;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

import java.util.List;
import java.util.logging.Level;

import static net.nyrheim.bricksandmortar.database.jooq.Tables.DROP_TABLE;
import static net.nyrheim.bricksandmortar.database.jooq.Tables.DROP_TABLE_ITEM;
import static org.jooq.impl.DSL.constraint;

public final class DropTableItemTable implements Table {

    private final BricksAndMortar plugin;
    private final Database database;

    private final Cache<Integer, DropTableItem> cache;

    public DropTableItemTable(BricksAndMortar plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        this.cache = database.getCacheManager().createCache("bricksandmortar.drop_table_item.id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, DropTableItem.class,
                        ResourcePoolsBuilder.heap(100L)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(DROP_TABLE_ITEM)
                .column(DROP_TABLE_ITEM.ID)
                .column(DROP_TABLE_ITEM.DROP_TABLE_ID)
                .column(DROP_TABLE_ITEM.ITEM_TYPE)
                .column(DROP_TABLE_ITEM.AMOUNT)
                .column(DROP_TABLE_ITEM.QUALITY)
                .column(DROP_TABLE_ITEM.CHANCE)
                .column(DROP_TABLE_ITEM.TOOL_TYPE)
                .constraints(
                        constraint("drop_table_item_pk").primaryKey(DROP_TABLE_ITEM.ID),
                        constraint("drop_table_item_drop_table_id_fk")
                                .foreignKey(DROP_TABLE_ITEM.DROP_TABLE_ID)
                                .references(DROP_TABLE, DROP_TABLE.ID)
                )
                .execute();
    }

    public DropTableItem get(DropTableItemId id) {
        if (cache.containsKey(id.getValue())) {
            return cache.get(id.getValue());
        }
        RPKItemQualityProvider itemQualityProvider;
        try {
            itemQualityProvider = plugin.core.getServiceManager().getServiceProvider(RPKItemQualityProvider.class);
        } catch (UnregisteredServiceException exception) {
            plugin.getLogger().log(Level.SEVERE, "Failed to find item quality provider");
            return null;
        }
        Record result = database.create()
                .select(
                        DROP_TABLE_ITEM.DROP_TABLE_ID,
                        DROP_TABLE_ITEM.ITEM_TYPE,
                        DROP_TABLE_ITEM.AMOUNT,
                        DROP_TABLE_ITEM.QUALITY,
                        DROP_TABLE_ITEM.CHANCE,
                        DROP_TABLE_ITEM.TOOL_TYPE
                )
                .from(DROP_TABLE_ITEM)
                .where(DROP_TABLE_ITEM.ID.eq(id.getValue()))
                .fetchOne();
        DropTableItem dropTableItem = new DropTableItem(
                plugin,
                id,
                ItemType.getByName(result.get(DROP_TABLE_ITEM.ITEM_TYPE)),
                result.get(DROP_TABLE_ITEM.AMOUNT),
                result.get(DROP_TABLE_ITEM.QUALITY) != null ? itemQualityProvider.getItemQuality(result.get(DROP_TABLE_ITEM.QUALITY)) : null,
                result.get(DROP_TABLE_ITEM.CHANCE),
                ItemType.getByName(result.get(DROP_TABLE_ITEM.TOOL_TYPE))
        );
        cache.put(dropTableItem.getId().getValue(), dropTableItem);
        return dropTableItem;
    }

    public List<DropTableItem> get(DropTableId dropTableId) {
        return database.create()
                .select(DROP_TABLE_ITEM.ID)
                .from(DROP_TABLE_ITEM)
                .where(DROP_TABLE_ITEM.DROP_TABLE_ID.eq(dropTableId.getValue()))
                .fetch()
                .map(result -> get(new DropTableItemId(result.get(DROP_TABLE_ITEM.ID))));
    }

    public DropTable getDropTable(DropTableItem dropTableItem) {
        return database.create()
                .select(DROP_TABLE_ITEM.DROP_TABLE_ID)
                .from(DROP_TABLE_ITEM)
                .where(DROP_TABLE_ITEM.ID.eq(dropTableItem.getId().getValue()))
                .fetchOne()
                .map(result ->
                        database.getTable(DropTableTable.class).get(
                                new DropTableId(result.get(DROP_TABLE_ITEM.DROP_TABLE_ID))
                        )
                );
    }

    public void insert(DropTableId dropTableId, DropTableItem item) {
        database.create()
                .insertInto(
                        DROP_TABLE_ITEM,
                        DROP_TABLE_ITEM.DROP_TABLE_ID,
                        DROP_TABLE_ITEM.ITEM_TYPE,
                        DROP_TABLE_ITEM.AMOUNT,
                        DROP_TABLE_ITEM.QUALITY,
                        DROP_TABLE_ITEM.CHANCE,
                        DROP_TABLE_ITEM.TOOL_TYPE
                )
                .values(
                        dropTableId.getValue(),
                        item.getItemType().getName(),
                        item.getAmount(),
                        item.getQuality() != null ? item.getQuality().getName() : null,
                        item.getChance(),
                        item.getToolType().getName()
                )
                .execute();
        DropTableItemId id = new DropTableItemId(database.create().lastID().intValue());
        item.setId(id);
        cache.put(id.getValue(), item);
    }

    public void update(DropTableId dropTableId, DropTableItem item) {
        database.create()
                .update(DROP_TABLE_ITEM)
                .set(DROP_TABLE_ITEM.DROP_TABLE_ID, dropTableId.getValue())
                .set(DROP_TABLE_ITEM.ITEM_TYPE, item.getItemType().getName())
                .set(DROP_TABLE_ITEM.AMOUNT, item.getAmount())
                .set(DROP_TABLE_ITEM.QUALITY, item.getQuality() != null ? item.getQuality().getName() : null)
                .set(DROP_TABLE_ITEM.CHANCE, item.getChance())
                .set(DROP_TABLE_ITEM.TOOL_TYPE, item.getToolType().getName())
                .where(DROP_TABLE_ITEM.ID.eq(item.getId().getValue()))
                .execute();
        cache.put(dropTableId.getValue(), item);
    }

    public void insertOrUpdate(DropTableId dropTableId, DropTableItem item) {
        if (item.getId() == null) {
            insert(dropTableId, item);
        } else {
            update(dropTableId, item);
        }
    }

    public void insertOrUpdate(DropTable dropTable) {
        dropTable.getItems().forEach(item -> insertOrUpdate(dropTable.getId(), item));
    }

    public void delete(DropTableItem item) {
        database.create()
                .deleteFrom(DROP_TABLE_ITEM)
                .where(DROP_TABLE_ITEM.ID.eq(item.getId().getValue()))
                .execute();
        cache.remove(item.getId().getValue());
    }
}
