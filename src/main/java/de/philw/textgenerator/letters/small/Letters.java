package de.philw.textgenerator.letters.small;

import de.philw.textgenerator.letters.Block;
import de.philw.textgenerator.utils.Direction;

public abstract class Letters {

    public abstract String[][] getA(Block block, Direction rightDirection);

    public abstract String[][] getB(Block block, Direction rightDirection);

    public abstract String[][] getC(Block block, Direction rightDirection);

    public abstract String[][] getD(Block block, Direction rightDirection);

    public abstract String[][] getE(Block block, Direction rightDirection);

    public abstract String[][] getF(Block block, Direction rightDirection);

    public abstract String[][] getG(Block block, Direction rightDirection);

    public abstract String[][] getH(Block block, Direction rightDirection);

    public abstract String[][] getI(Block block, Direction rightDirection);

    public abstract String[][] getJ(Block block, Direction rightDirection);

    public abstract String[][] getK(Block block, Direction rightDirection);

    public abstract String[][] getL(Block block, Direction rightDirection);

    public abstract String[][] getM(Block block, Direction rightDirection);

    public abstract String[][] getN(Block block, Direction rightDirection);

    public abstract String[][] getO(Block block, Direction rightDirection);

    public abstract String[][] getP(Block block, Direction rightDirection);

    public abstract String[][] getQ(Block block, Direction rightDirection);

    public abstract String[][] getR(Block block, Direction rightDirection);

    public abstract String[][] getS(Block block, Direction rightDirection);

    public abstract String[][] getT(Block block, Direction rightDirection);

    public abstract String[][] getU(Block block, Direction rightDirection);

    public abstract String[][] getV(Block block, Direction rightDirection);

    public abstract String[][] getW(Block block, Direction rightDirection);

    public abstract String[][] getX(Block block, Direction rightDirection);

    public abstract String[][] getY(Block block, Direction rightDirection);

    public abstract String[][] getZ(Block block, Direction rightDirection);

    public abstract String[][] get1(Block block, Direction rightDirection);

    public abstract String[][] get2(Block block, Direction rightDirection);

    public abstract String[][] get3(Block block, Direction rightDirection);

    public abstract String[][] get4(Block block, Direction rightDirection);

    public abstract String[][] get5(Block block, Direction rightDirection);

    public abstract String[][] get6(Block block, Direction rightDirection);

    public abstract String[][] get7(Block block, Direction rightDirection);

    public abstract String[][] get8(Block block, Direction rightDirection);

    public abstract String[][] get9(Block block, Direction rightDirection);

    protected String getRightTopNothingStair(Block block, Direction rightDirection) {
        return "minecraft:" + block.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.getOppositeDirection().toString().toLowerCase() + ",half=bottom,shape=straight,waterlogged=false]";
    }

    protected String getRightBottomNothingStair(Block block, Direction rightDirection) {
        return "minecraft:" + block.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.getOppositeDirection().toString().toLowerCase() + ",half=top,shape=straight,waterlogged=false]";
    }

    protected String getLeftTopNothingStair(Block block, Direction rightDirection) {
        return "minecraft:" + block.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.toString().toLowerCase() + ",half=bottom,shape=straight,waterlogged=false]";
    }

    protected String getLeftBottomNothingStair(Block block, Direction rightDirection) {
        return "minecraft:" + block.toString().toLowerCase() + "_stairs[facing=" +
                rightDirection.toString().toLowerCase() + ",half=top,shape=straight,waterlogged=false]";
    }

    protected String getBottomSlap(Block block) {
        return "minecraft:" + block.toString().toLowerCase() + "_slab[type=bottom,waterlogged=false]";
    }

    protected String getTopSlap(Block block) {
        return "minecraft:" + block.toString().toLowerCase() + "_slab[type=top,waterlogged=false]";
    }

    protected String getNormalBlock(Block block) {
        return "minecraft:" + block.toString().toLowerCase() + block.getMustAddAfterNormalBlock();
    }

    protected String getAir() {
        return "minecraft:air";
    }

}
