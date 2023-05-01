package de.philw.textgenerator.letters;

public enum Font {
    COBBLESTONE(""),
    OAK("_planks");

    private String mustAddAfterNormalBlock;

    Font(String mustAddAfterNormalBlock) {
        this.mustAddAfterNormalBlock = mustAddAfterNormalBlock;
    }

    public String getMustAddAfterNormalBlock() {
        return mustAddAfterNormalBlock;
    }

}
