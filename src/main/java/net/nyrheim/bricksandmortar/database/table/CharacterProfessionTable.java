package net.nyrheim.bricksandmortar.database.table;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.Database;
import net.nyrheim.bricksandmortar.profession.BricksProfessionService;
import net.nyrheim.bricksandmortar.profession.CharacterProfessionExperience;
import net.nyrheim.bricksandmortar.profession.Profession;
import net.nyrheim.penandpaper.character.CharacterId;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;
import org.jooq.impl.SQLDataType;

import static net.nyrheim.bricksandmortar.database.jooq.Tables.CHARACTER_PROFESSION;
import static org.jooq.impl.DSL.constraint;
import static org.jooq.impl.DSL.table;

public class CharacterProfessionTable implements Table {

    private final BricksAndMortar plugin;
    private final Database database;

    private final Cache<Integer, CharacterProfessionExperience> cache;

    public CharacterProfessionTable(BricksAndMortar plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        this.cache = database.getCacheManager().createCache("bricksandmortar.character_profession.character_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, CharacterProfessionExperience.class,
                        ResourcePoolsBuilder.heap(50)));
    }

    @Override
    public void create() {
        database.create().createTableIfNotExists(CHARACTER_PROFESSION)
                .column(CHARACTER_PROFESSION.CHARACTER_ID)
                .column(CHARACTER_PROFESSION.PROFESSION)
                .column(CHARACTER_PROFESSION.EXPERIENCE)
                .constraints(
                        constraint("character_profession_pk")
                                .primaryKey(CHARACTER_PROFESSION.CHARACTER_ID),
                        constraint("character_profession_character_id_fk")
                                .foreignKey(CHARACTER_PROFESSION.CHARACTER_ID)
                                .references(table("character"), table("character").field("id", SQLDataType.INTEGER))
                                .onDeleteCascade()
                                .onUpdateCascade()
                );
    }

    public CharacterProfessionExperience get(CharacterId characterId) {
        if (cache.containsKey(characterId.getValue())) {
            return cache.get(characterId.getValue());
        }
        Record result = database.create()
                .select(
                        CHARACTER_PROFESSION.PROFESSION,
                        CHARACTER_PROFESSION.EXPERIENCE
                )
                .from(CHARACTER_PROFESSION)
                .where(CHARACTER_PROFESSION.CHARACTER_ID.eq(characterId.getValue()))
                .fetchOne();
        if (result == null) return null;
        Profession profession = plugin.getServices().get(BricksProfessionService.class).getProfession(result.get(CHARACTER_PROFESSION.PROFESSION));
        CharacterProfessionExperience characterProfessionExperience = new CharacterProfessionExperience(
                profession,
                result.get(CHARACTER_PROFESSION.EXPERIENCE)
        );
        cache.put(characterId.getValue(), characterProfessionExperience);
        return characterProfessionExperience;
    }

    public void insert(CharacterId characterId, CharacterProfessionExperience professionExperience) {
        database.create()
                .insertInto(
                        CHARACTER_PROFESSION,
                        CHARACTER_PROFESSION.CHARACTER_ID,
                        CHARACTER_PROFESSION.PROFESSION,
                        CHARACTER_PROFESSION.EXPERIENCE
                )
                .values(
                        characterId.getValue(),
                        professionExperience.getProfession().getName(),
                        professionExperience.getExperience()
                )
                .execute();
        cache.put(characterId.getValue(), professionExperience);
    }

    public void update(CharacterId characterId, CharacterProfessionExperience professionExperience) {
        database.create()
                .update(CHARACTER_PROFESSION)
                .set(CHARACTER_PROFESSION.PROFESSION, professionExperience.getProfession().getName())
                .set(CHARACTER_PROFESSION.EXPERIENCE, professionExperience.getExperience())
                .where(CHARACTER_PROFESSION.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
        cache.put(characterId.getValue(), professionExperience);
    }

    public void insertOrUpdateProfessionExperience(CharacterId characterId, CharacterProfessionExperience professionExperience) {
        if (get(characterId) != null) {
            update(characterId, professionExperience);
        } else {
            insert(characterId, professionExperience);
        }
    }
}
