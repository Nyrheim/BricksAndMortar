/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.bricksandmortar.database.jooq;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.bricksandmortar.database.jooq.tables.CharacterProfession;
import net.nyrheim.bricksandmortar.database.jooq.tables.DropTable;
import net.nyrheim.bricksandmortar.database.jooq.tables.DropTableItem;
import net.nyrheim.bricksandmortar.database.jooq.tables.Node;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Nyrheim extends SchemaImpl {

    private static final long serialVersionUID = -1882717607;

    /**
     * The reference instance of <code>nyrheim</code>
     */
    public static final Nyrheim NYRHEIM = new Nyrheim();

    /**
     * The table <code>nyrheim.character_profession</code>.
     */
    public final CharacterProfession CHARACTER_PROFESSION = CharacterProfession.CHARACTER_PROFESSION;

    /**
     * The table <code>nyrheim.drop_table</code>.
     */
    public final DropTable DROP_TABLE = DropTable.DROP_TABLE;

    /**
     * The table <code>nyrheim.drop_table_item</code>.
     */
    public final DropTableItem DROP_TABLE_ITEM = DropTableItem.DROP_TABLE_ITEM;

    /**
     * The table <code>nyrheim.node</code>.
     */
    public final Node NODE = Node.NODE;

    /**
     * No further instances allowed
     */
    private Nyrheim() {
        super("nyrheim", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            CharacterProfession.CHARACTER_PROFESSION,
            DropTable.DROP_TABLE,
            DropTableItem.DROP_TABLE_ITEM,
            Node.NODE);
    }
}
