package dev.mj80.alignment;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static String alignLeft(String string, double width) {
        return string + "<reset>"+generateSpace(width-Width.of(string));
    }
    public static String alignRight(String string, double width) {
        return generateSpace(width-Width.of(string))+string;
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
            } else {
                return space.toString();
            }
        }
        return space.toString();
    }
    public static String center(String string, TextType textType, boolean padLeft, boolean padRight) {
        return center(string, textType.getWidth(), padLeft, padRight);
    }
    public static String center(String string, double width, boolean padLeft, boolean padRight) {
        StringBuilder centered = new StringBuilder();
        List<String> lines = Arrays.stream(string.split("\n")).toList();

        lines.forEach(line -> {
            double stringWidth = Width.of(line);
            if(padLeft) {
                centered.append("<reset>").append(generateSpace((width - stringWidth) / 2));
            }
            centered.append(line);
            if(padRight) {
                centered.append("<reset>").append(generateSpace((width - stringWidth) / 2));
            }
            if(lines.indexOf(line) != lines.size()-1) {
                centered.append("\n");
            }
        });
        return centered.toString();
    }
    public static String wrapText(String text, double maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] inputLines = text.split("\n");

        for (String inputLine : inputLines) {
            StringBuilder currentLine = new StringBuilder();
            String[] words = inputLine.split("\\s+");

            for (String word : words) {
                if (Width.of(word) > maxWidth) {
                    String remainingWord = word;
                    while (!remainingWord.isEmpty()) {
                        int splitIndex = getSplitIndex(remainingWord, maxWidth);
                        lines.add(remainingWord.substring(0, splitIndex));
                        remainingWord = remainingWord.substring(splitIndex).trim();
                    }
                } else if (currentLine.isEmpty()) {
                    currentLine.append(word);
                } else if (Width.of(currentLine + " " + word) <= maxWidth) {
                    currentLine.append(" ").append(word);
                } else {
                    lines.add(currentLine.toString());
                    currentLine.setLength(0);
                    currentLine.append(word);
                }
            }

            if (!currentLine.isEmpty()) {
                lines.add(currentLine.toString());
            }
        }
        return String.join("\n", lines);
    }

    private static int getSplitIndex(String word, double maxWidth) {
        int index = 0;
        double currentWidth = 0;
        while (index < word.length() && currentWidth + Width.of(String.valueOf(word.charAt(index))) <= maxWidth) {
            currentWidth += Width.of(String.valueOf(word.charAt(index)));
            index++;
        }
        return index;
    }
    protected static JsonElement parseJson(String json) {
        return new Gson().fromJson(json, JsonElement.class);
    }
}
