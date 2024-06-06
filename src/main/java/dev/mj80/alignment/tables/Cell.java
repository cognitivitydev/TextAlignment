package dev.mj80.alignment.tables;

import dev.mj80.alignment.TextAlignment;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Cell {
    @Getter @Setter private String content;
    @Getter @Setter private TextAlignment alignment;

    public Cell(String content, TextAlignment alignment) {
        this.content = content;
        this.alignment = alignment;
    }
    public Cell(Component content, TextAlignment alignment) {
        this.content = MiniMessage.miniMessage().serialize(content);
        this.alignment = alignment;
    }
    @Override
    public String toString() {
        return "Cell{content=\""+content+"\", align="+alignment+"}";
    }
}
