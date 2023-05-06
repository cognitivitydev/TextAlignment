package dev.mj80.alignment;

import lombok.Getter;

public enum Width {
    ZERO_WIDTH('\u200C', 0),
    HALF_WIDTH('\uEFFE', 0.5),
    SINGLE_WIDTH('\uEFFF', 1),
    SPACE(' ', 2),
    HYPHEN('-', 3), UNDERSCORE('_', 3),
    EQUALS('=', 3), PLUS('+', 3),
    VERTICAL_BAR('|', 2),
    SINGLE_QUOTE('\'', 2),
    DOUBLE_QUOTE('"', 2),
    FWD_SLASH('/', 3), BWD_SLASH('\\', 3),
    OPENING_BRACKET('[', 2), OPENING_BRACE('{', 2),
    CLOSING_BRACKET(']', 2), CLOSING_BRACE('}', 2),
    COMMA(',', 1), PERIOD('.', 1),
    LESS_THAN('<', 2.5), GREATER_THAN('>', 2.5),
    
    a('a', 3), A('A', 3),
    b('b', 3), B('B', 3),
    c('c', 3), C('C', 3),
    d('d', 3), D('D', 3),
    e('e', 3), E('E', 3),
    f('f', 2.5), F('F', 3),
    g('g', 3), G('G', 3),
    h('h', 3), H('H', 3),
    i('i', 1), I('I', 2),
    j('j', 3), J('J', 3),
    k('k', 2.5), K('K', 3),
    l('l', 1.5), L('L', 3),
    m('m', 3), M('M', 3),
    n('n', 3), N('N', 3),
    o('o', 3), O('O', 3),
    p('p', 3), P('P', 3),
    q('q', 3), Q('Q', 3),
    r('r', 3), R('R', 3),
    s('s', 3), S('S', 3),
    t('t', 2), T('T', 3),
    u('u', 3), U('U', 3),
    v('v', 3), V('V', 3),
    w('w', 3), W('W', 3),
    x('x', 3), X('X', 3),
    y('y', 3), Y('Y', 3),
    z('z', 3), Z('Z', 3),
    
    GRAVE('`', 1.5), TILDE('~', 3.5),
    NUM0('0', 3), EXCLAMATION_MARK('!', 1),
    NUM1('1', 3), AT_SIGN('@', 3.5),
    NUM2('2', 3), POUND_SIGN('#', 3),
    NUM3('3', 3), DOLLAR_SIGN('$', 3),
    NUM4('4', 3), PERCENT_SIGN('%', 3),
    NUM5('5', 3), CARET('^', 3),
    NUM6('6', 3), AMPERSAND('&', 3),
    NUM7('7', 3), ASTERISK('*', 2),
    NUM8('8', 3), OPENING_PARENTHESIS('(', 2),
    NUM9('9', 3), CLOSING_PARENTHESIS(')', 2),
    
    DEFAULT('\n', 0),
    ;
    
    @Getter private final char character;
    @Getter private final double width;
    
    Width(char character, double width) {
        this.character = character;
        this.width = width;
    }
    
    public double getBoldWidth() {
        return width + 0.5;
    }
    
    public static Width get(char character) {
        for(Width characters : Width.values()) {
            if(characters.getCharacter() == character) {
                return characters;
            }
        }
        return ZERO_WIDTH;
    }
    public static double of(String string) {
        double width = 0;
        for(int i = 0; i < string.toCharArray().length; i++) {
            char character = string.charAt(i);
            width += get(character).getWidth();
        }
        return width;
    }
}
