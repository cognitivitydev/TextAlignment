package dev.mj80.alignment;

import lombok.Getter;

public enum TextType {
    CHAT(160), ;

    @Getter private final double width;
    TextType(double width) {
        this.width = width;
    }
}
