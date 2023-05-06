package dev.mj80.alignment;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final ArrayList<String> table;
    @Getter private final int rows,columns;
    @Getter private final double boxWidth;
    public Table(int rows, int columns, double boxWidth) {
        this.rows = rows;
        this.columns = columns;
        this.boxWidth = boxWidth;
        table = new ArrayList<>(rows * columns - 1);
        for (int i = 0; i < rows * columns; i++) {
            table.add("");
        }
    }
    public Table set(int row, int column, String string) {
        set((row - 1) * columns + (column - 1), string);
        return this;
    }
    public Table set(int index, String string) {
        table.set(index, string);
        return this;
    }
    public String get(int row, int column) {
        return get((row - 1) * columns + (column - 1));
    }
    public String get(int index) {
        return table.get(index);
    }
    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String string : table) {
            if(table.indexOf(string) % columns == 0 && table.indexOf(string) != 0) {
                stringBuilder.append("\n");
            }
            if((table.indexOf(string) + 1) % columns != 0) {
                stringBuilder.append(StringUtils.align(string+"%ALIGN%", boxWidth));
            } else {
                stringBuilder.append(string);
            }
        }
        return stringBuilder.toString();
    }
    public List<String> toList() {
        return List.of(this.toString().split("\n"));
    }
}