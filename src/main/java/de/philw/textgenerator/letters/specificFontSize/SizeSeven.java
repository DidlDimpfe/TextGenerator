package de.philw.textgenerator.letters.specificFontSize;

import de.philw.textgenerator.ui.value.Block;
import de.philw.textgenerator.utils.Direction;

public class SizeSeven extends SpecificFontSize {

    // 3x3

    @Override
    public String[][] getA(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getB(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getC(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getD(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getE(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getF(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getG(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getLeftTopNothingStair(block, direction);
        blockDataStrings[1][2] = getBottomSlap(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getH(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getI(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getTopSlap(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getJ(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = null;
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getK(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getL(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = null;

        return blockDataStrings;
    }

    @Override
    public String[][] getM(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getRightTopNothingStair(block, direction);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getLeftTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getN(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getRightTopNothingStair(block, direction);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getO(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getP(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getQ(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getLeftTopNothingStair(block, direction);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getR(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getS(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getT(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getTopSlap(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getU(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getV(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = null;
        blockDataStrings[1][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getW(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getX(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[2][1] = getTopSlap(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = getBottomSlap(block);
        blockDataStrings[2][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getRightBottomNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getY(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = getBottomSlap(block);
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = getRightBottomNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getZ(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getTopSlap(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getLeftTopNothingStair(block, direction);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    public String[][] get0(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = null;
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get1(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[0][1] = getNormalBlock(block);
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = null;
        blockDataStrings[0][2] = null;

        return blockDataStrings;
    }

    @Override
    public String[][] get2(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getBottomSlap(block);
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] get3(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getTopSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] get4(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = null;
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[2][2] = getNormalBlock(block);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get5(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getTopSlap(block);
        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get6(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getNormalBlock(block);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get7(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[0][0] = getTopSlap(block);
        blockDataStrings[2][1] = getNormalBlock(block);
        blockDataStrings[1][1] = getLeftTopNothingStair(block, direction);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = null;
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[0][2] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] get8(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getTopSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] get9(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[1][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[2][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[1][2] = getNormalBlock(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);

        return blockDataStrings;
    }

    @Override
    public String[][] getFullStop(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][1];

        blockDataStrings[0][0] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[2][0] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getComma(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][2];

        blockDataStrings[0][0] = null;
        blockDataStrings[0][1] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getSemicolon(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][2];

        blockDataStrings[0][0] = null;
        blockDataStrings[0][1] = getBottomSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[2][0] = getBottomSlap(block);
        blockDataStrings[2][1] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getColon(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][1];

        blockDataStrings[0][0] = getBottomSlap(block);
        blockDataStrings[1][0] = null;
        blockDataStrings[2][0] = getTopSlap(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getQuestionMark(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getLeftTopNothingStair(block, direction);
        blockDataStrings[0][1] = getTopSlap(block);
        blockDataStrings[0][2] = getRightTopNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[1][1] = getBottomSlap(block);
        blockDataStrings[1][2] = getRightBottomNothingStair(block, direction);
        blockDataStrings[2][0] = null;
        blockDataStrings[2][1] = getBottomSlap(block);
        blockDataStrings[2][2] = null;

        return blockDataStrings;
    }

    @Override
    public String[][] getExclamationMark(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][1];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[1][0] = getTopSlap(block);
        blockDataStrings[2][0] = getNormalBlock(block);

        return blockDataStrings;
    }

    @Override
    public String[][] getApostrophe(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][1];

        blockDataStrings[0][0] = getLeftBottomNothingStair(block, direction);
        blockDataStrings[1][0] = null;
        blockDataStrings[2][0] = null;

        return blockDataStrings;
    }

    @Override
    public String[][] getBackslash(Block block, Direction direction) {
        String[][] blockDataStrings = new String[3][3];

        blockDataStrings[0][0] = getNormalBlock(block);
        blockDataStrings[0][1] = null;
        blockDataStrings[0][2] = null;
        blockDataStrings[1][0] = null;
        blockDataStrings[1][1] = getNormalBlock(block);
        blockDataStrings[1][2] = null;
        blockDataStrings[2][0] = null;
        blockDataStrings[2][1] = null;
        blockDataStrings[2][2] = getNormalBlock(block);

        return blockDataStrings;
    }

}