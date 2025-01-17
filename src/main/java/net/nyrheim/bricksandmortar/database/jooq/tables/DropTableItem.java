/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.bricksandmortar.database.jooq.tables;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.bricksandmortar.database.jooq.Keys;
import net.nyrheim.bricksandmortar.database.jooq.Nyrheim;
import net.nyrheim.bricksandmortar.database.jooq.tables.records.DropTableItemRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DropTableItem extends TableImpl<DropTableItemRecord> {

    private static final long serialVersionUID = -1396480205;

    /**
     * The reference instance of <code>nyrheim.drop_table_item</code>
     */
    public static final DropTableItem DROP_TABLE_ITEM = new DropTableItem();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DropTableItemRecord> getRecordType() {
        return DropTableItemRecord.class;
    }

    /**
     * The column <code>nyrheim.drop_table_item.id</code>.
     */
    public final TableField<DropTableItemRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.drop_table_id</code>.
     */
    public final TableField<DropTableItemRecord, Integer> DROP_TABLE_ID = createField(DSL.name("drop_table_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.item_type</code>.
     */
    public final TableField<DropTableItemRecord, String> ITEM_TYPE = createField(DSL.name("item_type"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.amount</code>.
     */
    public final TableField<DropTableItemRecord, Integer> AMOUNT = createField(DSL.name("amount"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.quality</code>.
     */
    public final TableField<DropTableItemRecord, String> QUALITY = createField(DSL.name("quality"), org.jooq.impl.SQLDataType.VARCHAR(256), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.chance</code>.
     */
    public final TableField<DropTableItemRecord, Integer> CHANCE = createField(DSL.name("chance"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.drop_table_item.tool_type</code>.
     */
    public final TableField<DropTableItemRecord, String> TOOL_TYPE = createField(DSL.name("tool_type"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * Create a <code>nyrheim.drop_table_item</code> table reference
     */
    public DropTableItem() {
        this(DSL.name("drop_table_item"), null);
    }

    /**
     * Create an aliased <code>nyrheim.drop_table_item</code> table reference
     */
    public DropTableItem(String alias) {
        this(DSL.name(alias), DROP_TABLE_ITEM);
    }

    /**
     * Create an aliased <code>nyrheim.drop_table_item</code> table reference
     */
    public DropTableItem(Name alias) {
        this(alias, DROP_TABLE_ITEM);
    }

    private DropTableItem(Name alias, Table<DropTableItemRecord> aliased) {
        this(alias, aliased, null);
    }

    private DropTableItem(Name alias, Table<DropTableItemRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DropTableItem(Table<O> child, ForeignKey<O, DropTableItemRecord> key) {
        super(child, key, DROP_TABLE_ITEM);
    }

    @Override
    public Schema getSchema() {
        return Nyrheim.NYRHEIM;
    }

    @Override
    public Identity<DropTableItemRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DROP_TABLE_ITEM;
    }

    @Override
    public UniqueKey<DropTableItemRecord> getPrimaryKey() {
        return Keys.KEY_DROP_TABLE_ITEM_PRIMARY;
    }

    @Override
    public List<UniqueKey<DropTableItemRecord>> getKeys() {
        return Arrays.<UniqueKey<DropTableItemRecord>>asList(Keys.KEY_DROP_TABLE_ITEM_PRIMARY);
    }

    @Override
    public List<ForeignKey<DropTableItemRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DropTableItemRecord, ?>>asList(Keys.DROP_TABLE_ITEM_DROP_TABLE_ID_FK);
    }

    public DropTable dropTable() {
        return new DropTable(this, Keys.DROP_TABLE_ITEM_DROP_TABLE_ID_FK);
    }

    @Override
    public DropTableItem as(String alias) {
        return new DropTableItem(DSL.name(alias), this);
    }

    @Override
    public DropTableItem as(Name alias) {
        return new DropTableItem(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DropTableItem rename(String name) {
        return new DropTableItem(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DropTableItem rename(Name name) {
        return new DropTableItem(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, String, Integer, String, Integer, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
