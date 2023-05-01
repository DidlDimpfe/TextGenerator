package de.philw.textgenerator.letters;

import de.philw.textgenerator.utils.Direction;

public class TwoTimesTwoLetters extends Letters {

    public String[][] getA(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    public String[][] getB(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getBottomSlap(font);

        return blockDataStrings;
    }

    public String[][] getC(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);

        return blockDataStrings;
    }

    public String[][] getD(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    public String[][] getE(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getF(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[1][0] = getTopSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getG(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getH(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getI(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getRightBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getJ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getK(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getL(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();

        return blockDataStrings;
    }

    @Override
    public String[][] getM(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getN(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getO(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getP(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[1][0] = getTopSlap(font);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getQ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getR(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getS(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getRightBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getT(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getBottomSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getU(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getV(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getW(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getX(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getTopSlap(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getY(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getBottomSlap(font);
        blockDataStrings[0][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getZ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[2][2];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getTopSlap(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getRightBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }
}
