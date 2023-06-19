package de.philw.textgenerator.letters.specificFontSize;

import de.philw.textgenerator.ui.value.Block;
import de.philw.textgenerator.utils.Direction;

public class ThreeByThreeLetters extends Letters {


    @Override
    public String[][] getA(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getB(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getC(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getD(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getE(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getF(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getG(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getH(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getI(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getJ(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getAir();
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getK(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getLeftTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getL(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getAir();

        return blockDataStrings;
    }

    @Override
    public String[][] getM(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getLeftTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getN(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getO(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getP(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getQ(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getR(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getS(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getT(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getU(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getAir();
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getV(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getW(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getX(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getTopSlap(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getBottomSlap(block);
        blockDataStrings[2][0] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightBottomNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getY(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getBottomSlap(block);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getRightBottomNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] getZ(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get1(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getBottomSlap(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getAir();
        blockDataStrings[2][2] = getAir();

        return blockDataStrings;
    }

    @Override
    public String[][] get2(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get3(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getTopSlap(block);
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get4(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getAir();
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getAir();
        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get5(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[0][2] = getNormalBlock(block);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get6(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get7(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getAir();
        blockDataStrings[0][1] = getAir();
        blockDataStrings[0][2] = getTopSlap(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[1][1] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getAir();
        blockDataStrings[2][1] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get8(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][1] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getRightTopNothingStair(block, rightDirection);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

    @Override
    public String[][] get9(Block block, Direction rightDirection) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[0][1] = getLeftBottomNothingStair(block, rightDirection);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, rightDirection);
        blockDataStrings[1][0] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getTopSlap(block);
        blockDataStrings[2][0] = getRightBottomNothingStair(block, rightDirection);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, rightDirection);

        return blockDataStrings;
    }

}
