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
    INTVAR("int"),
    VOID("void"),
    STRINGVAR("string"),
    ARRAYVAR("array"),
    PRINTLN("println"),
    LENGTH("length"),
    ROFL("readfile"),
    WTF("writefile"),
    DECLARATION("declare"),
    WHILE("while"),
    IF("if"),
    FOR("for"),
    ENDDECLARATION("eralced"),
    CALL("call"),
    RETURN("return"),
    INT,
    STRING,
    ID,
    COLON(":"),
    SEMICOLON(";"),
    LPAR("("),
    RPAR(")"),
    DOT("."),
    LBRACE("{"),
    RBRACE("}"),
    LBRACKET("["),
    RBRACKET("]"),
    QUOTE("\""),
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
