package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.FARMERS_TOOLS;

public final class Farmer extends Profession {
    public Farmer() {
        super(
                "Farmer",
                asList(
                        FARMERS_TOOLS
                )
        );
    }
}
