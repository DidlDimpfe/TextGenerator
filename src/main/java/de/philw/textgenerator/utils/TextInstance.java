package de.philw.textgenerator.utils;

import de.philw.textgenerator.letters.Block;
import org.bukkit.Location;

public class TextInstance {

    private Block block;
    private Location startLocation;
    private Direction direction;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private String text;
    private boolean underline;
    private int spaceBetweenEachLine;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getSpaceBetweenEachLine() {
        return spaceBetweenEachLine;
    }

    public void setSpaceBetweenEachLine(int spaceBetweenEachLine) {
        this.spaceBetweenEachLine = spaceBetweenEachLine;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isUnderline() {
        return underline;
    }

    public static TextInstanceBuilder getTextInstanceBuilder() {
        return new TextInstanceBuilder();
    }

    public static class TextInstanceBuilder {

        private Block block;
        private Location startLocation;
        private Direction direction;
        private String fontName;
        private int fontStyle;
        private int fontSize = 0;
        private String text;
        private boolean underline;
        private int spaceBetweenEachLine = 2;

        public TextInstanceBuilder withBlock(Block block) {
            this.block = block;
            return this;
        }

        public TextInstanceBuilder withStartLocation(Location startLocation) {
            this.startLocation = startLocation;
            return this;
        }

        public TextInstanceBuilder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public TextInstanceBuilder withFontName(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public TextInstanceBuilder withFontStyle(int fontStyle) {
            this.fontStyle = fontStyle;
            return this;
        }

        public TextInstanceBuilder withFontSize(int fontSize)  {
            this.fontSize = fontSize;
            return this;
        }

        public TextInstanceBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public TextInstanceBuilder withUnderline(boolean underline) {
            this.underline = underline;
            return this;
        }

        public TextInstanceBuilder withSpaceBetweenEachLine(int spaceBetweenEachLine) {
            this.spaceBetweenEachLine = spaceBetweenEachLine;
            return this;
        }

        public TextInstance build() {
            TextInstance textInstance = new TextInstance();
            textInstance.setBlock(block);
            textInstance.setStartLocation(startLocation);
            textInstance.setDirection(direction);
            textInstance.setFontName(fontName);
            textInstance.setFontStyle(fontStyle);
            textInstance.setFontSize(fontSize);
            textInstance.setText(text);
            textInstance.setUnderline(underline);
            textInstance.setSpaceBetweenEachLine(spaceBetweenEachLine);
            return textInstance;
        }

    }

}