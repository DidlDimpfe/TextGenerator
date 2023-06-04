package de.philw.textgenerator.letters;

public enum Block {
    COBBLESTONE("", true, "cobblestone"),
    OAK("_planks", true, "oak_planks"),
    QUARTZ("_block", true, "quartz_block");

    private final String mustAddAfterNormalBlock;
    private final boolean isCompatibleWithSmallLetters;
    private final String normalBlockData;

    Block(String mustAddAfterNormalBlock, boolean isCompatibleWithSmallLetters, String normalBlockData) {
        this.mustAddAfterNormalBlock = mustAddAfterNormalBlock;
        this.isCompatibleWithSmallLetters = isCompatibleWithSmallLetters;
        this.normalBlockData = normalBlockData;
    }

    public String getMustAddAfterNormalBlock() {
        return mustAddAfterNormalBlock;
    }

    public boolean isCompatibleWithSmallLetters() {
        return isCompatibleWithSmallLetters;
    }

    public String getNormalBlockData() {
        return normalBlockData;
    }
}
