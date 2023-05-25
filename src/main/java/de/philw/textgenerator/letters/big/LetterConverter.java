package de.philw.textgenerator.letters.big;

import de.philw.textgenerator.utils.TextInstance;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LetterConverter {

    public static ArrayList<BufferedImage> stringToBufferedImages(TextInstance textInstance) {
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
        graphicsTool.dispose();

        ArrayList<BufferedImage> linesAsBufferedImages = new ArrayList<>();

        for (String line: lines) {
            int width = fontMetrics.stringWidth(line);
            int height = fontMetrics.getHeight();
            BufferedImage letters = new BufferedImage(fontMetrics.stringWidth(line), fontMetrics.getHeight(), BufferedImage.TYPE_INT_RGB);
            graphicsTool = letters.createGraphics();
            graphicsTool.setColor(Color.WHITE);
            graphicsTool.fillRect(0, 0, width, height);
            graphicsTool.setFont(font);
            fontMetrics = graphicsTool.getFontMetrics();
            graphicsTool.setColor(Color.BLACK);
            graphicsTool.drawString(line, 0, fontMetrics.getAscent());
            linesAsBufferedImages.add(letters);
        }

        graphicsTool.dispose();
        return linesAsBufferedImages;
    }


}