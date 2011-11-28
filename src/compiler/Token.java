/*
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

/**
 * Lexikalni symboly.
 */
public enum Token {

    PROG("program"),
    ENDPROG("margorp"),
    METHODS("methods"),
    ENDMETHODS("sdohtem"),
    METHOD("method"),
    ENDMETHOD("dohtem"),
    INTVAR("int"),
    REALVAR("real"),
    VOID("void"),
    PRINTLN("println"),
    DECLARATION("declare"),
    WHILE("while"),
    IF("if"),
    FOR("for"),
    ENDDECLARATION("eralced"),
    ENDWHILE("elihw"),
    ENDIF("fi"),
    ENDFOR("rof"),
    RETURN("return"),
    INT,
    REAL,
    ID,
    SEMICOLON(";"),
    LPAR("("),
    RPAR(")"),
    DOT("."),
    ASSIGN("="),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDED("/"),
    EQ("=="),
    NE("!="),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">="),
    EOI;
    private String term;

    private Token() {
    }

    private Token(String term) {
        this.term = term;
    }

    /**
     * Zjistuje, zda je zadany retezec klicovym slovem.
     */
    public static Token searchForKeyword(String s) {
        for (Token t : values()) {
            if (s.equalsIgnoreCase(t.term)) {
                return t;
            }
        }
        return ID;
    }
}
