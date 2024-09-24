package com.thebest12lines.worldmanager.gui;

import java.awt.Font;

public class StyledNodeObject {
    private String text;
    private Font font;

    public StyledNodeObject(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    // You can add other methods or properties as needed
}
