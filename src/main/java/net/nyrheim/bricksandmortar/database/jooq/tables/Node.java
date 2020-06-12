/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.bricksandmortar.database.jooq.tables;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.bricksandmortar.database.jooq.Keys;
import net.nyrheim.bricksandmortar.database.jooq.Nyrheim;
import net.nyrheim.bricksandmortar.database.jooq.tables.records.NodeRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
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
public class Node extends TableImpl<NodeRecord> {

    private static final long serialVersionUID = -628100808;

    /**
     * The reference instance of <code>nyrheim.node</code>
     */
    public static final Node NODE = new Node();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NodeRecord> getRecordType() {
        return NodeRecord.class;
    }

    /**
     * The column <code>nyrheim.node.id</code>.
     */
    public final TableField<NodeRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>nyrheim.node.name</code>.
     */
    public final TableField<NodeRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.world</code>.
     */
    public final TableField<NodeRecord, String> WORLD = createField(DSL.name("world"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.min_x</code>.
     */
    public final TableField<NodeRecord, Integer> MIN_X = createField(DSL.name("min_x"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.min_y</code>.
     */
    public final TableField<NodeRecord, Integer> MIN_Y = createField(DSL.name("min_y"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.min_z</code>.
     */
    public final TableField<NodeRecord, Integer> MIN_Z = createField(DSL.name("min_z"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.max_x</code>.
     */
    public final TableField<NodeRecord, Integer> MAX_X = createField(DSL.name("max_x"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.max_y</code>.
     */
    public final TableField<NodeRecord, Integer> MAX_Y = createField(DSL.name("max_y"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.max_z</code>.
     */
    public final TableField<NodeRecord, Integer> MAX_Z = createField(DSL.name("max_z"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.node.drop_table_id</code>.
     */
    public final TableField<NodeRecord, Integer> DROP_TABLE_ID = createField(DSL.name("drop_table_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>nyrheim.node</code> table reference
     */
    public Node() {
        this(DSL.name("node"), null);
    }

    /**
     * Create an aliased <code>nyrheim.node</code> table reference
     */
    public Node(String alias) {
        this(DSL.name(alias), NODE);
    }

    /**
     * Create an aliased <code>nyrheim.node</code> table reference
     */
    public Node(Name alias) {
        this(alias, NODE);
    }

    private Node(Name alias, Table<NodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Node(Name alias, Table<NodeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Node(Table<O> child, ForeignKey<O, NodeRecord> key) {
        super(child, key, NODE);
    }

    @Override
    public Schema getSchema() {
        return Nyrheim.NYRHEIM;
    }

    @Override
    public Identity<NodeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_NODE;
    }

    @Override
    public UniqueKey<NodeRecord> getPrimaryKey() {
        return Keys.KEY_NODE_PRIMARY;
    }

    @Override
    public List<UniqueKey<NodeRecord>> getKeys() {
        return Arrays.<UniqueKey<NodeRecord>>asList(Keys.KEY_NODE_PRIMARY);
    }

    @Override
    public List<ForeignKey<NodeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<NodeRecord, ?>>asList(Keys.NODE_DROP_TABLE_ID_FK);
    }

    public DropTable dropTable() {
        return new DropTable(this, Keys.NODE_DROP_TABLE_ID_FK);
    }

    @Override
    public Node as(String alias) {
        return new Node(DSL.name(alias), this);
    }

    @Override
    public Node as(Name alias) {
        return new Node(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Node rename(String name) {
        return new Node(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Node rename(Name name) {
        return new Node(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, String, String, Integer, Integer, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
