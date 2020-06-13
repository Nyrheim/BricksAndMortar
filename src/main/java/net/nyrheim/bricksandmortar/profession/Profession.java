package net.nyrheim.bricksandmortar.profession;

import net.nyrheim.penandpaper.item.ItemType;

import java.util.ArrayList;
import java.util.List;

public abstract class Profession {

    private final String name;
    private final List<ItemType> toolTypes = new ArrayList<>();

    public Profession(String name, List<ItemType> toolTypes) {
        this.name = name;
        this.toolTypes.addAll(toolTypes);
    }

    public String getName() {
        return name;
    }

    public List<ItemType> getToolTypes() {
        return toolTypes;
    }

}
