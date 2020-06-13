package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.HERBALISTS_TOOLS;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.WOODCUTTERS_TOOLS;

public final class Woodsman extends Profession {
    public Woodsman() {
        super(
                "Woodsman",
                asList(
                        WOODCUTTERS_TOOLS,
                        HERBALISTS_TOOLS
                )
        );
    }
}
