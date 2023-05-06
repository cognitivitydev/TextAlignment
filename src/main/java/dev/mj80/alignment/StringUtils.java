package dev.mj80.alignment;

import java.util.List;

public class StringUtils {
    public static String align(String string, double width) {
        StringBuilder builder = new StringBuilder();
        List<String> list = List.of(string.split("%ALIGN%"));
        list.forEach(strings -> {
            builder.append(strings).append(generateSpace(width - Width.of(strings)));
        });
        return builder.toString();
    }
    public static String generateSpace(double width) {
        StringBuilder space = new StringBuilder();
        double i = width;
        while(i > 0) {
            if (i >= 2) {
                space.append(Width.SPACE.getCharacter());
                i -= 2;
            } else if(i >= 1) {
                space.append(Width.SINGLE_WIDTH.getCharacter());
                i--;
            } else if(i >= 0.5) {
                space.append(Width.HALF_WIDTH.getCharacter());
                i -= 0.5;
            }
        }
        return space.toString();
    }
    public static String center(String string, TextType textType, boolean padLeft, boolean padRight) {
        return center(string, textType.getWidth(), padLeft, padRight);
    }
    public static String center(String string, double width, boolean padLeft, boolean padRight) {
        double stringWidth = Width.of(string);
        StringBuilder centered = new StringBuilder();
        if(padLeft) {
            centered.append(generateSpace((width - stringWidth) / 2));
        }
        centered.append(string);
        if(padRight) {
            centered.append(generateSpace((width - stringWidth) / 2));
        }
        return centered.toString();
    }
}
