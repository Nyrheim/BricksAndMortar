package net.nyrheim.bricksandmortar.recipe;

import com.rpkit.itemquality.bukkit.itemquality.RPKItemQuality;
import net.nyrheim.bricksandmortar.profession.Profession;
import net.nyrheim.penandpaper.item.ItemType;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public final class BricksRecipe {

    private final Material workstation;
    private final Profession profession;
    private final int minimumLevel;
    private final ItemType toolkit;
    private final List<RPKItemQuality> permittedToolkitQualities;
    private final ItemType result;
    private final Map<ItemType, Integer> ingredients;

    public BricksRecipe(
            Material workstation,
            Profession profession,
            int minimumLevel,
            ItemType toolkit,
            List<RPKItemQuality> permittedToolkitQualities,
            ItemType result,
            Map<ItemType, Integer> ingredients
    ) {
        this.workstation = workstation;
        this.profession = profession;
        this.minimumLevel = minimumLevel;
        this.toolkit = toolkit;
        this.permittedToolkitQualities = permittedToolkitQualities;
        this.result = result;
        this.ingredients = ingredients;
    }

    public Material getWorkstation() {
        return workstation;
    }

    public Profession getProfession() {
        return profession;
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }

    public ItemType getToolkit() {
        return toolkit;
    }

    public List<RPKItemQuality> getPermittedToolkitQualities() {
        return permittedToolkitQualities;
    }

    public ItemType getResult() {
        return result;
    }

    public Map<ItemType, Integer> getIngredients() {
        return ingredients;
    }

}
