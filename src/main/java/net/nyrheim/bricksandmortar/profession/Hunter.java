package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.HUNTERS_TOOLS;

public final class Hunter extends Profession {
    public Hunter() {
        super(
                "Hunter",
                asList(
                        HUNTERS_TOOLS
                )
        );
    }
}
