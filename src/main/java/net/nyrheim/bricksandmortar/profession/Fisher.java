package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.DIVING_TOOLS;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.FISHERMANS_TOOLS;

public final class Fisher extends Profession {
    public Fisher() {
        super(
                "Fisher",
                asList(
                        FISHERMANS_TOOLS,
                        DIVING_TOOLS
                )
        );
    }
}
