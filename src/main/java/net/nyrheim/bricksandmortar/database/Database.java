package net.nyrheim.bricksandmortar.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.table.*;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.jooq.SQLDialect.MYSQL;

public final class Database {

    private final BricksAndMortar plugin;

    private final DataSource dataSource;
    private final CacheManager cacheManager;

    private final Map<Class<? extends Table>, Table> tables;

    public Database(BricksAndMortar plugin, String url, String username, String password) {
        this.plugin = plugin;

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        dataSource = new HikariDataSource(hikariConfig);

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        tables = new HashMap<>();

        // Order is important - node and drop table item both have FK depending on drop table
        addTable(new CharacterProfessionTable(plugin, this));
        addTable(new DropTableTable(plugin, this));
        addTable(new DropTableItemTable(plugin, this));
        addTable(new NodeTable(plugin, this));
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public DSLContext create() {
        return DSL.using(dataSource, MYSQL);
    }

    public <T extends Table> void addTable(T table) {
        table.create();
        tables.put(table.getClass(), table);
    }

    public <T extends Table> T getTable(Class<T> type) {
        return (T) tables.get(type);
    }

}
