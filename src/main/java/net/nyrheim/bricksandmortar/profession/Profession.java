package net.nyrheim.bricksandmortar.profession;

import net.nyrheim.penandpaper.item.ItemType;

import java.util.ArrayList;
import java.util.List;

public abstract class Profession {

    private final String name;
    private final List<ItemType> tools = new ArrayList<>();

    public Profession(String name, List<ItemType> tools) {
        this.name = name;
        this.tools.addAll(tools);
    }

    public String getName() {
        return name;
    }

    public List<ItemType> getTools() {
        return tools;
    }

}
