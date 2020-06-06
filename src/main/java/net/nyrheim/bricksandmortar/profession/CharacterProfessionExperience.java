package net.nyrheim.bricksandmortar.profession;

public final class CharacterProfessionExperience {

    private final Profession profession;
    private int experience;

    public CharacterProfessionExperience(Profession profession, int experience) {
        this.profession = profession;
        this.experience = experience;
    }

    public CharacterProfessionExperience(Profession profession) {
        this(profession, 0);
    }

    public Profession getProfession() {
        return profession;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

}
