package net.nyrheim.bricksandmortar.profession;

public abstract class Profession {

    private final String name;

    public Profession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}