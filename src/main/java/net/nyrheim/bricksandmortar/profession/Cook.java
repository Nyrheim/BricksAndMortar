package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.CHEFS_TOOLS;

public final class Cook extends Profession {
    public Cook() {
        super(
                "Cook",
                asList(
                        CHEFS_TOOLS
                )
        );
    }
}
