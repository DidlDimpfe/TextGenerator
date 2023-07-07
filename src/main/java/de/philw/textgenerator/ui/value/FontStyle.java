package de.philw.textgenerator.ui.value;

import de.philw.textgenerator.utils.SkullData;

public enum FontStyle {

    PLAIN("Plain", SkullData.PLAIN),
    BOLD("Bold", SkullData.BOLD),
    ITALIC("Italic", SkullData.ITALIC),
    BOLD_ITALIC("Bold + Italic", SkullData.BOLD_ITALIC);

    private final String display;
    private final String skullData;
    public String getSkullData() {
        return skullData;
    }
    FontStyle(String display, String skullData) {
        this.display = display;
        this.skullData = skullData;
    }

    public String getDisplay() {
        return display;
    }


    public String getSavedInFile() {
        String[] words = name().split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            word = word.toLowerCase();
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            stringBuilder.append(word);
        }
        return stringBuilder.toString();
    }
}
