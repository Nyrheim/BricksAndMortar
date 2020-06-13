package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.ALCHEMISTS_TOOLS;

public final class Alchemist extends Profession {
    public Alchemist() {
        super(
                "Alchemist",
                asList(
                        ALCHEMISTS_TOOLS
                )
        );
    }
}
