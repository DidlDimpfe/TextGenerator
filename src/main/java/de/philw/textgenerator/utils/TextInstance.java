package de.philw.textgenerator.utils;

import de.philw.textgenerator.ui.value.Block;
import org.bukkit.Location;

public class TextInstance {

    private Block block;
    private Location middleLocation, topLeftLocation;
    private Direction direction;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private String text;
    private boolean underline;
    private int lineSpacing;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Location getMiddleLocation() {
        return middleLocation;
    }

    public void setMiddleLocation(Location middleLocation) {
        this.middleLocation = middleLocation;
    }

    public Location getTopLeftLocation() {
        return topLeftLocation;
    }

    public void setTopLeftLocation(Location topLeftLocation) {
        this.topLeftLocation = topLeftLocation;
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

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
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
        private Location middleLocation, topLeftLocation;
        private Direction direction;
        private String fontName;
        private int fontStyle;
        private int fontSize = 0;
        private String text;
        private boolean underline;
        private int lineSpacing = 2;

        public TextInstanceBuilder withBlock(Block block) {
            this.block = block;
            return this;
        }

        public TextInstanceBuilder withMiddleLocation(Location  middleLocation) {
            this.middleLocation = middleLocation;
            return this;
        }

        public TextInstanceBuilder withTopLeftLocation(Location  topLeftLocation) {
            this.topLeftLocation = topLeftLocation;
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

        public TextInstanceBuilder withFontSize(int fontSize) {
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

        public TextInstanceBuilder withLineSpacing(int lineSpacing) {
            this.lineSpacing = lineSpacing;
            return this;
        }

        public TextInstance build() {
            TextInstance textInstance = new TextInstance();
            textInstance.setBlock(block);
            textInstance.setMiddleLocation(middleLocation);
            textInstance.setTopLeftLocation(topLeftLocation);
            textInstance.setDirection(direction);
            textInstance.setFontName(fontName);
            textInstance.setFontStyle(fontStyle);
            textInstance.setFontSize(fontSize);
            textInstance.setText(text);
            textInstance.setUnderline(underline);
            textInstance.setLineSpacing(lineSpacing);
            return textInstance;
        }

    }

}