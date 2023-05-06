package de.philw.textgenerator.letters;

import de.philw.textgenerator.utils.Direction;

public class ThreeByThreeLetters extends Letters {


    @Override
    public String[][] getA(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getNormalBlock(font);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getB(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getC(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getD(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getE(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getF(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getG(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getBottomSlap(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getH(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(font);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getI(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(font);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getNormalBlock(font);
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getJ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getAir();
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getK(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getL(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getAir();

        return blockDataStrings;
    }

    @Override
    public String[][] getM(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(font);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getLeftTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getN(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getO(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getP(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getQ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getNormalBlock(font);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getR(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(font);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getS(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(font);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getT(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getNormalBlock(font);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getU(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getV(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getW(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] getX(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getTopSlap(font);
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getBottomSlap(font);
        blockDataStrings[2][0] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getY(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getBottomSlap(font);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightBottomNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getZ(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] get1(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(font);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getBottomSlap(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getNormalBlock(font);
        blockDataStrings[1][2] = getNormalBlock(font);
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getAir();

        return blockDataStrings;
    }

    @Override
    public String[][] get2(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getBottomSlap(font);
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get3(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getTopSlap(font);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get4(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(font);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] get5(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getTopSlap(font);
        blockDataStrings[0][2] = getNormalBlock(font);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] get6(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(font);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getTopSlap(font);

        return blockDataStrings;
    }

    @Override
    public String[][] get7(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(font);
        blockDataStrings[1][0] = getNormalBlock(font);
        blockDataStrings[1][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(font);

        return blockDataStrings;
    }

    @Override
    public String[][] get8(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getTopSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(font, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get9(Font font, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(font);
        blockDataStrings[0][1] = getLeftBottomNothingStair(font, rightDirection);
        blockDataStrings[0][2] = getLeftTopNothingStair(font, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(font);
        blockDataStrings[1][1] = getBottomSlap(font);
        blockDataStrings[1][2] = getTopSlap(font);
        blockDataStrings[2][0] = getRightBottomNothingStair(font, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(font);
        blockDataStrings[2][2] = getRightTopNothingStair(font, rightDirection);

        return blockDataStrings;
    }

}
