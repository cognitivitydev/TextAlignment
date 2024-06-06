package dev.mj80.alignment.tables;

import dev.mj80.alignment.StringUtils;
import dev.mj80.alignment.TextAlignment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final ArrayList<Cell> table;
    @Getter private final int rows,columns;
    @Getter private final double boxWidth;
    public Table(int rows, int columns, double boxWidth) {
        this.rows = rows;
        this.columns = columns;
        this.boxWidth = boxWidth;
        table = new ArrayList<>(rows * columns - 1);
        for (int i = 0; i < rows * columns; i++) {
            table.add(new Cell("", TextAlignment.LEFT));
        }
    }
    public Table set(int row, int column, String string) {
        set((row - 1) * columns + (column - 1), string);
        return this;
    }
    public Table set(int index, String string) {
        set(index, new Cell(string, TextAlignment.LEFT));
        return this;
    }
    public Table set(int row, int column, Cell cell) {
        set((row - 1) * columns + (column - 1), cell);
        return this;
    }
    public Table set(int index, Cell cell) {
        table.set(index, cell);
        return this;
    }
    public Cell get(int row, int column) {
        return get((row - 1) * columns + (column - 1));
    }
    public Cell get(int index) {
        return table.get(index);
    }
    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Cell cell : table) {
            stringBuilder.append("<reset>");
            if(table.indexOf(cell) % columns == 0 && table.indexOf(cell) != 0) {
                stringBuilder.append("\n");
            }

            switch (cell.getAlignment()) {
                case LEFT -> stringBuilder.append(StringUtils.alignLeft(cell.getContent(), boxWidth));
                case CENTER -> stringBuilder.append(StringUtils.center(cell.getContent(), boxWidth, true, true));
                case RIGHT -> stringBuilder.append(StringUtils.alignRight(cell.getContent(), boxWidth));
            }
        }
        return stringBuilder.toString();
    }
    public List<String> toList() {
        return List.of(this.toString().split("\n"));
    }
}