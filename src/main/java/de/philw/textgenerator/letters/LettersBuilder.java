package de.philw.textgenerator.letters;

import de.philw.textgenerator.utils.Direction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.Objects;

public class LettersBuilder {

    public static void build(String[][] letterData, Direction rightDirection, Location startLocation) {
        int length = letterData.length;
        int height = letterData[0].length;

        World world = startLocation.getWorld();

        for (int lengthIndex = 0; lengthIndex < length; lengthIndex++) {
            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                BlockData blockData = Bukkit.createBlockData(letterData[lengthIndex][heightIndex]);
                Block block = null;
                if (rightDirection == Direction.NORTH) {
                    block = Objects.requireNonNull(world).getBlockAt(startLocation.clone().add(0, heightIndex, 0).subtract(0, 0, lengthIndex));
                } else if (rightDirection == Direction.EAST) {
                    block = Objects.requireNonNull(world).getBlockAt(startLocation.clone().add(lengthIndex, heightIndex, 0));
                } else if (rightDirection == Direction.SOUTH) {
                    block = Objects.requireNonNull(world).getBlockAt(startLocation.clone().add(0, heightIndex, lengthIndex));
                } else if (rightDirection == Direction.WEST){
                    block = Objects.requireNonNull(world).getBlockAt(startLocation.clone().add(0, heightIndex, 0).subtract(lengthIndex, 0, 0));
                }
                Objects.requireNonNull(block).setBlockData(blockData);
            }
        }

    }

}
