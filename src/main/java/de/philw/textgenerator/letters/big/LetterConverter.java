package de.philw.textgenerator.letters.big;

import de.philw.textgenerator.utils.TextInstance;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LetterConverter {

    public static BufferedImage stringToBufferedImage(TextInstance textInstance) {
        String text = textInstance.getText();
        boolean underline = textInstance.isUnderline();
        String fontName = textInstance.getFontName();
        int fontStyle = textInstance.getFontStyle();
        int fontSize = textInstance.getFontSize();

        String[] lines = text.split("\\\\n");

        BufferedImage createGraphicsImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphicsTool = createGraphicsImage.createGraphics();

        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
        if (underline) fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font font = new Font(fontName, fontStyle, fontSize).deriveFont(fontAttributes);

        graphicsTool.setFont(font);
        FontMetrics fontMetrics = graphicsTool.getFontMetrics();
        String longestText = "";
        for(String row: lines){
            if(row.length() > longestText.length()){
                longestText = row;
            }
        }

        int width = fontMetrics.stringWidth(longestText);
        int height = fontMetrics.getHeight()*lines.length;
        graphicsTool.dispose();

        BufferedImage letters = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        graphicsTool = letters.createGraphics();
        graphicsTool.setColor(Color.WHITE);
        graphicsTool.fillRect(0, 0, width, height);
        graphicsTool.setFont(font);
        fontMetrics = graphicsTool.getFontMetrics();
        graphicsTool.setColor(Color.BLACK);
        int y = fontMetrics.getAscent();

        for (String line: lines) {
            graphicsTool.drawString(line, 0, y);
            y += fontMetrics.getHeight();
        }
        graphicsTool.dispose();
        return letters;
    }


}