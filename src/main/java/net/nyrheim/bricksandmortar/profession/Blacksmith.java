package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.SMITHS_TOOLS;

public final class Blacksmith extends Profession {
    public Blacksmith() {
        super(
                "Blacksmith",
                asList(
                        SMITHS_TOOLS
                )
        );
    }
}
