/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.bricksandmortar.database.jooq;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.bricksandmortar.database.jooq.tables.CharacterProfession;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Nyrheim extends SchemaImpl {

    private static final long serialVersionUID = 762598040;

    /**
     * The reference instance of <code>nyrheim</code>
     */
    public static final Nyrheim NYRHEIM = new Nyrheim();

    /**
     * The table <code>nyrheim.character_profession</code>.
     */
    public final CharacterProfession CHARACTER_PROFESSION = CharacterProfession.CHARACTER_PROFESSION;

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
            CharacterProfession.CHARACTER_PROFESSION);
    }
}