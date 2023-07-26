package de.philw.textgenerator.ui.value;

import de.philw.textgenerator.utils.SkullData;

public enum Font {

    ARIAL("Arial", SkullData.ARIAL),
    COURIER_NEW("Courier New", SkullData.COURIER_NEW),
    FORTE("Forte", SkullData.FORTE),
    IMPACT("Impact", SkullData.IMPACT),
    JOKERMAN("Jokerman", SkullData.JOKERMAN),
    PAPYRUS("Papyrus", SkullData.PAPYRUS),
    RAVIE("Ravie", SkullData.RAVIE),
    TIMES_NEW_ROMAN("Times New Roman", SkullData.TIMES_NEW_ROMAN),
    VIVALDI("Vivaldi", SkullData.VIVALDI);

    private final String skullData;
    private final String fontName;
    Font(String fontName, String skullData) {
        this.skullData = skullData;
        this.fontName = fontName;
    }

    public String getSkullData() {
        return skullData;
    }

    public String getFontName() {
        return fontName;
    }
}
