package de.philw.textgenerator.ui.value;

import de.philw.textgenerator.utils.SkullData;

import java.util.Arrays;
import java.util.List;

public enum LineSpacing {

    ZERO(0, SkullData.ZERO, Arrays.asList("0", "ZERO")),
    ONE(1, SkullData.ONE, Arrays.asList("1", "ONE")),
    TWO(2, SkullData.TWO, Arrays.asList("2", "TWO")),
    THREE(3, SkullData.THREE, Arrays.asList("3", "THREE")),
    FOUR(4, SkullData.FOUR, Arrays.asList("4", "FOUR")),
    FIVE(5, SkullData.FIVE, Arrays.asList("5", "FIVE")),
    SIX(6, SkullData.SIX, Arrays.asList("6", "SIX")),
    SEVEN(7, SkullData.SEVEN, Arrays.asList("7", "SEVEN")),
    EIGHT(8, SkullData.EIGHT, Arrays.asList("8", "EIGHT")),
    NINE(9, SkullData.NINE, Arrays.asList("9", "NINE")),
    TEN(10, SkullData.TEN, Arrays.asList("10", "TEN")),
    ELEVEN(11, SkullData.ELEVEN, Arrays.asList("11", "ELEVEN")),
    TWELVE(12, SkullData.TWELVE, Arrays.asList("12", "TWELVE")),
    THIRTEEN(13, SkullData.THIRTEEN, Arrays.asList("13", "THIRTEEN")),
    FOURTEEN(14, SkullData.FOURTEEN, Arrays.asList("14", "FOURTEEN")),
    FIFTEEN(15, SkullData.FIFTEEN, Arrays.asList("15", "FIFTEEN")),
    SIXTEEN(16, SkullData.SIXTEEN, Arrays.asList("16", "SIXTEEN")),
    SEVENTEEN(17, SkullData.SEVENTEEN, Arrays.asList("17", "SEVENTEEN")),
    EIGHTEEN(18, SkullData.EIGHTEEN, Arrays.asList("18", "EIGHTEEN")),
    NINETEEN(19, SkullData.NINETEEN, Arrays.asList("19", "NINETEEN")),
    TWENTY(20, SkullData.TWENTY, Arrays.asList("20", "TWENTY")),
    TWENTY_ONE(21, SkullData.TWENTY_ONE, Arrays.asList("21", "TWENTY_ONE", "TWENTYONE")),
    TWENTY_TWO(22, SkullData.TWENTY_TWO, Arrays.asList("22", "TWENTY_TWO", "TWENTYTWO")),
    TWENTY_THREE(23, SkullData.TWENTY_THREE, Arrays.asList("23", "TWENTY_THREE", "TWENTYTHREE")),
    TWENTY_FOUR(24, SkullData.TWENTY_FOUR, Arrays.asList("24", "TWENTY_FOUR", "TWENTYFOUR")),
    TWENTY_FIVE(25, SkullData.TWENTY_FIVE, Arrays.asList("25", "TWENTY_FIVE", "TWENTYFIVE")),
    TWENTY_SIX(26, SkullData.TWENTY_SIX, Arrays.asList("26", "TWENTY_SIX", "TWENTYSIX")),
    TWENTY_SEVEN(27, SkullData.TWENTY_SEVEN, Arrays.asList("27", "TWENTY_SEVEN", "TWENTYSEVEN")),
    TWENTY_EIGHT(28, SkullData.TWENTY_EIGHT, Arrays.asList("28", "TWENTY_EIGHT", "TWENTYEIGHT")),
    TWENTY_NINE(29, SkullData.TWENTY_NINE, Arrays.asList("29", "TWENTY_NINE", "TWENTYNINE")),
    THIRTY(30, SkullData.THIRTY, Arrays.asList("30", "THIRTY")),
    THIRTY_ONE(31, SkullData.THIRTY_ONE, Arrays.asList("31", "THIRTY_ONE", "THIRTYONE")),
    THIRTY_TWO(32, SkullData.THIRTY_TWO, Arrays.asList("32", "THIRTY_TWO", "THIRTYTWO")),
    THIRTY_THREE(33, SkullData.THIRTY_THREE, Arrays.asList("33", "THIRTY_THREE", "THIRTYTHREE")),
    THIRTY_FOUR(34, SkullData.THIRTY_FOUR, Arrays.asList("34", "THIRTY_FOUR", "THIRTYFOUR")),
    THIRTY_FIVE(35, SkullData.THIRTY_FIVE, Arrays.asList("35", "THIRTY_FIVE", "THIRTYFIVE")),
    THIRTY_SIX(36, SkullData.THIRTY_SIX, Arrays.asList("36", "THIRTY_SIX", "THIRTYSIX")),
    THIRTY_SEVEN(37, SkullData.THIRTY_SEVEN, Arrays.asList("37", "THIRTY_SEVEN", "THIRTYSEVEN")),
    THIRTY_EIGHT(38, SkullData.THIRTY_EIGHT, Arrays.asList("38", "THIRTY_EIGHT", "THIRTYEIGHT")),
    THIRTY_NINE(39, SkullData.THIRTY_NINE, Arrays.asList("39", "THIRTY_NINE", "THIRTYNINE")),
    FORTY(40, SkullData.FORTY, Arrays.asList("40", "FORTY")),
    FORTY_ONE(41, SkullData.FORTY_ONE, Arrays.asList("41", "FORTY_ONE", "FORTYONE")),
    FORTY_TWO(42, SkullData.FORTY_TWO, Arrays.asList("42", "FORTY_TWO", "FORTYTWO")),
    FORTY_THREE(43, SkullData.FORTY_THREE, Arrays.asList("43", "FORTY_THREE", "FORTYTHREE")),
    FORTY_FOUR(44, SkullData.FORTY_FOUR, Arrays.asList("44", "FORTY_FOUR", "FORTYFOUR")),
    FORTY_FIVE(45, SkullData.FORTY_FIVE, Arrays.asList("45", "FORTY_FIVE", "FORTYFIVE")),
    FORTY_SIX(46, SkullData.FORTY_SIX, Arrays.asList("46", "FORTY_SIX", "FORTYSIX")),
    FORTY_SEVEN(47, SkullData.FORTY_SEVEN, Arrays.asList("47", "FORTY_SEVEN", "FORTYSEVEN")),
    FORTY_EIGHT(48, SkullData.FORTY_EIGHT, Arrays.asList("48", "FORTY_EIGHT", "FORTYEIGHT")),
    FORTY_NINE(49, SkullData.FORTY_NINE, Arrays.asList("49", "FORTY_NINE", "FORTYNINE")),
    FIFTY(50, SkullData.FIFTY, Arrays.asList("50", "FIFTY"));

    private final int number;
    private final String skullData;
    private final List<String> searchKeywords;

    LineSpacing(int number, String skullData, List<String> searchKeywords) {
        this.number = number;
        this.skullData = skullData;
        this.searchKeywords = searchKeywords;
    }

    public int getNumber() {
        return number;
    }

    public String getSkullData() {
        return skullData;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public static int getMinLineSpacing() {
        return 0;
    }
    public static int getMaxLineSpacing() {
        return 50;
    }

}
