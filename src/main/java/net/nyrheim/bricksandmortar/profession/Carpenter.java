package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.*;

public final class Carpenter extends Profession {
    public Carpenter() {
        super(
                "Carpenter",
                asList(
                        CARPENTERS_TOOLS,
                        ARCHITECTS_TOOLS,
                        SCAFFOLDING_KIT
                )
        );
    }
}
