package net.nyrheim.bricksandmortar.profession;

import static java.util.Arrays.asList;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.MINERS_TOOLS;

public final class Miner extends Profession {
    public Miner() {
        super(
                "Miner",
                asList(
                        MINERS_TOOLS
                )
        );
    }
}
