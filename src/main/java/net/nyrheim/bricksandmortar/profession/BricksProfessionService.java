package net.nyrheim.bricksandmortar.profession;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.database.table.CharacterProfessionTable;
import net.nyrheim.penandpaper.character.PenCharacter;

import java.util.ArrayList;
import java.util.List;

public final class BricksProfessionService {

    private final BricksAndMortar plugin;

    private final List<Profession> professions = new ArrayList<>();

    public BricksProfessionService(BricksAndMortar plugin) {
        this.plugin = plugin;

        professions.add(new Farmer());
        professions.add(new Fisher());
        professions.add(new Miner());
        professions.add(new Hunter());
        professions.add(new Woodsman());
        professions.add(new Cook());
        professions.add(new Blacksmith());
        professions.add(new Carpenter());
        professions.add(new Alchemist());
        professions.add(new Leatherworker());
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public Profession getProfession(String name) {
        return professions.stream()
                .filter(profession -> profession.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Profession getProfession(PenCharacter character) {
        CharacterProfessionExperience characterProfessionExperience = plugin.getDatabase()
                .getTable(CharacterProfessionTable.class)
                .get(character.getId());
        if (characterProfessionExperience == null) return null;
        return characterProfessionExperience.getProfession();
    }

    public void setProfession(PenCharacter character, Profession profession) {
        plugin.getDatabase()
                .getTable(CharacterProfessionTable.class)
                .insertOrUpdateProfessionExperience(character.getId(),
                        new CharacterProfessionExperience(profession, getExperience(character)));
    }

    public int getExperience(PenCharacter character) {
        CharacterProfessionExperience characterProfessionExperience = plugin.getDatabase()
                .getTable(CharacterProfessionTable.class)
                .get(character.getId());
        if (characterProfessionExperience == null) return 0;
        return characterProfessionExperience.getExperience();
    }

    public void setExperience(PenCharacter character, int experience) {
        plugin.getDatabase()
                .getTable(CharacterProfessionTable.class)
                .insertOrUpdateProfessionExperience(character.getId(),
                        new CharacterProfessionExperience(getProfession(character), experience));
    }

}
