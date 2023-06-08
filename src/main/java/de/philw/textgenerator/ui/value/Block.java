package de.philw.textgenerator.ui.value;

import org.hamcrest.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Block {

    OAK_LOG(null),
    OAK_WOOD(null),
    STRIPPED_OAK_LOG(null),
    STRIPPED_OAK_WOOD(null),
    OAK_PLANKS(Arrays.asList("oak_slab", "oak_stairs")),
    SPRUCE_LOG(null),
    SPRUCE_WOOD(null),
    STRIPPED_SPRUCE_LOG(null),
    STRIPPED_SPRUCE_WOOD(null),
    SPRUCE_PLANKS(Arrays.asList("spruce_slab", "spruce_stairs")),
    BIRCH_LOG(null),
    BIRCH_WOOD(null),
    STRIPPED_BIRCH_LOG(null),
    STRIPPED_BIRCH_WOOD(null),
    BIRCH_PLANKS(Arrays.asList("birch_slab", "birch_stairs")),
    JUNGLE_LOG(null),
    JUNGLE_WOOD(null),
    STRIPPED_JUNGLE_LOG(null),
    STRIPPED_JUNGLE_WOOD(null),
    JUNGLE_PLANKS(Arrays.asList("jungle_slab", "jungle_stairs")),
    ACACIA_LOG(null),
    ACACIA_WOOD(null),
    STRIPPED_ACACIA_LOG(null),
    STRIPPED_ACACIA_WOOD(null),
    ACACIA_PLANKS(Arrays.asList("acacia_slab", "acacia_stairs")),
    DARK_OAK_LOG(null),
    DARK_OAK_WOOD(null),
    STRIPPED_DARK_OAK_LOG(null),
    STRIPPED_DARK_OAK_WOOD(null),
    DARK_OAK_PLANKS(Arrays.asList("dark_oak_slab", "dark_oak_stairs")),
    MANGROVE_LOG(null),
    MANGROVE_WOOD(null),
    STRIPPED_MANGROVE_LOG(null),
    STRIPPED_MANGROVE_WOOD(null),
    MANGROVE_PLANKS(Arrays.asList("mangrove_slab", "mangrove_stairs")),
    CRIMSON_STEM(null),
    CRIMSON_HYPHAE(null),
    STRIPPED_CRIMSON_STEM(null),
    STRIPPED_CRIMSON_HYPHAE(null),
    CRIMSON_PLANKS(Arrays.asList("crimson_slab", "crimson_stairs")),
    WARPED_STEM(null),
    WARPED_HYPHAE(null),
    STRIPPED_WARPED_STEM(null),
    STRIPPED_WARPED_HYPHAE(null),
    WARPED_PLANKS(Arrays.asList("warped_slab", "warped_stairs")),
    STONE(Arrays.asList("stone_slab", "stone_stairs")),
    COBBLESTONE(Arrays.asList("cobblestone_slab", "cobblestone_stairs")),
    MOSSY_COBBLESTONE(Arrays.asList("mossy_cobblestone_slab", "mossy_cobblestone_stairs")), // BIS HIER
    QUARTZ_BLOCK(Arrays.asList("quartz_slab", "quartz_stairs"));
    private final List<String> slabAndStairsID;

    Block(List<String> slabAndStairsID) {
        this.slabAndStairsID = slabAndStairsID;
    }

    public String getDisplay() {
        String[] words = name().split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word: words) {
            word = word.toLowerCase();
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            stringBuilder.append(word).append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public List<String> getSlabAndStairsID() {
        return slabAndStairsID;
    }

    // Get search keywords?

}