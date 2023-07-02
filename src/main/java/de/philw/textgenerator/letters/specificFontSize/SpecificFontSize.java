package de.philw.textgenerator.letters.specificFontSize;

import de.philw.textgenerator.ui.value.Block;
import de.philw.textgenerator.utils.Direction;

public abstract class SpecificFontSize {

    public static SpecificFontSize getClassFromFontSize(int fontSize) {
        if (fontSize == 6) {
            return new SizeSix();
        } else if (fontSize == 7) {
            return new SizeSeven();
        } else if (fontSize == 8) {
            return new SizeEight();
        }
        return null;
    }

    public abstract String[][] getA(Block block, Direction direction);

    public abstract String[][] getB(Block block, Direction direction);

    public abstract String[][] getC(Block block, Direction direction);

    public abstract String[][] getD(Block block, Direction direction);

    public abstract String[][] getE(Block block, Direction direction);

    public abstract String[][] getF(Block block, Direction direction);

    public abstract String[][] getG(Block block, Direction direction);

    public abstract String[][] getH(Block block, Direction direction);

    public abstract String[][] getI(Block block, Direction direction);

    public abstract String[][] getJ(Block block, Direction direction);

    public abstract String[][] getK(Block block, Direction direction);

    public abstract String[][] getL(Block block, Direction direction);

    public abstract String[][] getM(Block block, Direction direction);

    public abstract String[][] getN(Block block, Direction direction);

    public abstract String[][] getO(Block block, Direction direction);

    public abstract String[][] getP(Block block, Direction direction);

    public abstract String[][] getQ(Block block, Direction direction);

    public abstract String[][] getR(Block block, Direction direction);

    public abstract String[][] getS(Block block, Direction direction);

    public abstract String[][] getT(Block block, Direction direction);

    public abstract String[][] getU(Block block, Direction direction);

    public abstract String[][] getV(Block block, Direction direction);

    public abstract String[][] getW(Block block, Direction direction);

    public abstract String[][] getX(Block block, Direction direction);

    public abstract String[][] getY(Block block, Direction direction);

    public abstract String[][] getZ(Block block, Direction direction);

    public abstract String[][] get0(Block block, Direction direction);

    public abstract String[][] get1(Block block, Direction direction);

    public abstract String[][] get2(Block block, Direction direction);

    public abstract String[][] get3(Block block, Direction direction);

    public abstract String[][] get4(Block block, Direction direction);

    public abstract String[][] get5(Block block, Direction direction);

    public abstract String[][] get6(Block block, Direction direction);

    public abstract String[][] get7(Block block, Direction direction);

    public abstract String[][] get8(Block block, Direction direction);

    public abstract String[][] get9(Block block, Direction direction);
    public abstract String[][] getFullStop(Block block, Direction direction);
    public abstract String[][] getComma(Block block, Direction direction);
    public abstract String[][] getSemicolon(Block block, Direction direction);
    public abstract String[][] getColon(Block block, Direction direction);
    public abstract String[][] getQuestionMark(Block block, Direction direction);
    public abstract String[][] getExclamationMark(Block block, Direction direction);
    public abstract String[][] getApostrophe(Block block, Direction direction);
    public abstract String[][] getBackslash(Block block, Direction direction);

    protected String getRightTopNothingStair(Block block, Direction direction) {
        return "minecraft:" + block.getSlabAndStairsID().get(1).toLowerCase() + "[facing=" +
                direction.getOppositeDirection().toString().toLowerCase().split("_")[0] + ",half=bottom," +
                "shape=straight," +
                "waterlogged=false]";
    }

    protected String getRightBottomNothingStair(Block block, Direction direction) {
        return "minecraft:" + block.getSlabAndStairsID().get(1).toLowerCase() + "[facing=" +
                direction.getOppositeDirection().toString().toLowerCase().split("_")[0] + ",half=top," +
                "shape=straight," +
                "waterlogged=false]";
    }

    protected String getLeftTopNothingStair(Block block, Direction direction) {
        return "minecraft:" + block.getSlabAndStairsID().get(1).toLowerCase() + "[facing=" +
                direction.toString().toLowerCase().split("_")[0] + ",half=bottom,shape=straight," +
                "waterlogged=false]";
    }

    protected String getLeftBottomNothingStair(Block block, Direction direction) {
        return "minecraft:" + block.getSlabAndStairsID().get(1).toLowerCase() + "[facing=" +
                direction.toString().toLowerCase().split("_")[0] + ",half=top,shape=straight,waterlogged=false]";
    }

    protected String getBottomSlap(Block block) {
        return "minecraft:" + block.getSlabAndStairsID().get(0).toLowerCase() + "[type=bottom,waterlogged=false]";
    }

    protected String getTopSlap(Block block) {
        return "minecraft:" + block.getSlabAndStairsID().get(0).toLowerCase() + "[type=top,waterlogged=false]";
    }

    protected String getNormalBlock(Block block) {
        return "minecraft:" + block.toString().toLowerCase();
    }

}