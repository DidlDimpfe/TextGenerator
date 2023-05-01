package de.philw.textgenerator.letters;

import de.philw.textgenerator.utils.Direction;

public class TwoTimesTwoLetters extends Letters {


    @Override
    public String[][] getS(String material, Direction rightDirection) {

        String[][] strings = new String[2][2];

        String leftStair = "minecraft:" + material + "_stairs[facing=" + rightDirection.toString().toLowerCase() + ",half=bottom,shape=straight,waterlogged=false]";
        String rightStair = "minecraft:" + material + "_stairs[facing=" + rightDirection.getOppositeDirection().toString().toLowerCase() + ",half=top,shape=straight,waterlogged=false]";

        strings[0][0] = leftStair;
        strings[0][1] = leftStair;
        strings[1][0] = rightStair;
        strings[1][1] = rightStair;


        return strings;
    }
}
