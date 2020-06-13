package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.LEATHERWORKERS_TOOLS;

public final class Leatherworker extends Profession {
    public Leatherworker() {
        super(
                "Leatherworker",
                asList(
                        LEATHERWORKERS_TOOLS
                )
        );
    }
}
