package de.philw.textgenerator.letters;

import de.philw.textgenerator.utils.Direction;

public abstract class Letters {

    public abstract String[][] getA(Font font, Direction rightDirection);

    public abstract String[][] getB(Font font, Direction rightDirection);

    public abstract String[][] getC(Font font, Direction rightDirection);

    public abstract String[][] getD(Font font, Direction rightDirection);

    public abstract String[][] getE(Font font, Direction rightDirection);

    public abstract String[][] getF(Font font, Direction rightDirection);

    public abstract String[][] getG(Font font, Direction rightDirection);

    public abstract String[][] getH(Font font, Direction rightDirection);

    public abstract String[][] getI(Font font, Direction rightDirection);

    public abstract String[][] getJ(Font font, Direction rightDirection);

    public abstract String[][] getK(Font font, Direction rightDirection);

    public abstract String[][] getL(Font font, Direction rightDirection);

    public abstract String[][] getM(Font font, Direction rightDirection);

    public abstract String[][] getN(Font font, Direction rightDirection);

    public abstract String[][] getO(Font font, Direction rightDirection);

    public abstract String[][] getP(Font font, Direction rightDirection);

    public abstract String[][] getQ(Font font, Direction rightDirection);

    public abstract String[][] getR(Font font, Direction rightDirection);

    public abstract String[][] getS(Font font, Direction rightDirection);

    public abstract String[][] getT(Font font, Direction rightDirection);

    public abstract String[][] getU(Font font, Direction rightDirection);

    public abstract String[][] getV(Font font, Direction rightDirection);

    public abstract String[][] getW(Font font, Direction rightDirection);

    public abstract String[][] getX(Font font, Direction rightDirection);

    public abstract String[][] getY(Font font, Direction rightDirection);

    public abstract String[][] getZ(Font font, Direction rightDirection);

    public abstract String[][] get1(Font font, Direction rightDirection);

    public abstract String[][] get2(Font font, Direction rightDirection);

    public abstract String[][] get3(Font font, Direction rightDirection);

    public abstract String[][] get4(Font font, Direction rightDirection);

    public abstract String[][] get5(Font font, Direction rightDirection);

    public abstract String[][] get6(Font font, Direction rightDirection);

    public abstract String[][] get7(Font font, Direction rightDirection);

    public abstract String[][] get8(Font font, Direction rightDirection);

    public abstract String[][] get9(Font font, Direction rightDirection);

    protected String getRightTopNothingStair(Font font, Direction rightDirection) {
        return "minecraft:" + font.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.getOppositeDirection().toString().toLowerCase() + ",half=bottom,shape=straight,waterlogged=false]";
    }

    protected String getRightBottomNothingStair(Font font, Direction rightDirection) {
        return "minecraft:" + font.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.getOppositeDirection().toString().toLowerCase() + ",half=top,shape=straight,waterlogged=false]";
    }

    protected String getLeftTopNothingStair(Font font, Direction rightDirection) {
        return "minecraft:" + font.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.toString().toLowerCase() + ",half=bottom,shape=straight,waterlogged=false]";
    }

    protected String getLeftBottomNothingStair(Font font, Direction rightDirection) {
        return "minecraft:" + font.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.toString().toLowerCase() + ",half=top,shape=straight,waterlogged=false]";
    }

    protected String getBottomSlap(Font font) {
        return "minecraft:" + font.toString().toLowerCase() + "_slab[type=bottom,waterlogged=false]";
    }

    protected String getTopSlap(Font font) {
        return "minecraft:" + font.toString().toLowerCase() + "_slab[type=top,waterlogged=false]";
    }

    protected String getNormalBlock(Font font) {
        return "minecraft:" + font.toString().toLowerCase() + font.getMustAddAfterNormalBlock();
    }

    protected String getAir() {
        return "minecraft:air";
    }

}
