/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import tree.Position;
import java.io.Reader;
import java.util.Scanner;
import static compiler.Token.*;

/**
 *
 * @author aka
 */
public class Lexer {

    /**
     * Vstup.
     */
    private Scanner scanner;
    /**
     * Slovo ze vstupu.
     */
    private String buf;
    /**
     * Index do retezce buf.
     */
    private int index;
    /**
     * Jeden precteny znak.
     */
    private int ch;
    /**
     * Pozice aktualniho tokenu.
     */
    private int line, col;
    /**
     * Predchozi pozice.
     */
    private int pline, pcol;
    /**
     * Atribut lexikalniho symbolu IDENT.
     */
    private StringBuilder word = new StringBuilder();
    /**
     * Atribut lexikalnich symbolu INT_CONST, REAL_CONST.
     */
    private Object value;

    /**
     * Vytvori lexer, ktery cte vstup ze zadaneho streamu.
     */
    public Lexer(Reader r) {
        scanner = new Scanner(r);
        ch = nextChar();
    }

    /**
     * Vypise chybu.
     */
    private void error(String f, Object... o) {
        System.out.printf("%s%s ", getBeginPosition(), getEndPosition());
        String s = String.format(f, o);
        System.out.println(s);
    }

    /**
     * Precte jeden znak.
     */
    private int nextChar() {
        if (buf == null || index >= buf.length()) {
            if (scanner.hasNext()) {
                buf = scanner.nextLine() + ' ';
                line++;
                index = 0;
            } else {
                scanner.close();
                return -1;
            }
        }
        return buf.charAt(index++);
    }

    /**
     * Vrati jmeno identifikatoru.
     */
    public String getIdentifier() {
        return word.toString();
    }

    /**
     * Vrati hodnotu konstanty.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Vrati pocatecni pozici aktualniho symbolu.
     */
    public Position getBeginPosition() {
        return new Position(line, col);
    }

    /**
     * Vrati koncovou pozici aktualniho symbolu.
     */
    public Position getEndPosition() {
        return new Position(line, index);
    }

    /**
     * Vrati koncovou pozici predchoziho symbolu.
     */
    public Position getLastEndPosition() {
        return new Position(pline, pcol);
    }

    /**
     * Vrati dalsi lexikalni symbol.
     */
    public Token nextToken() {
        pline = line;
        pcol = index;
        // preskocime mezery
        while (Character.isWhitespace(ch)) {
            ch = nextChar();
        }
        col = index;
        word.setLength(0);
        if (Character.isLetter(ch)) {
            do {
                word.append((char) ch);
                ch = nextChar();
            } while (Character.isLetterOrDigit(ch));
            return searchForKeyword(word.toString());
        }
        if (Character.isDigit(ch)) {
            do {
                word.append((char) ch);
                ch = nextChar();
            } while (Character.isDigit(ch));
            if (ch == '.') {
                do {
                    word.append((char) ch);
                    ch = nextChar();
                } while (Character.isDigit(ch));
                value = Double.valueOf(word.toString());
                return REAL;
            }
            try {
                value = Integer.valueOf(word.toString());
                return INT;
            } catch (NumberFormatException e) {
                error("number %s is too large", word);
                throw new LexerException();
            }
        }
        if (ch == '(') {
            ch = nextChar();
            return LPAR;
        }
        if (ch == ')') {
            ch = nextChar();
            return RPAR;
        }
        if (ch == ';') {
            ch = nextChar();
            return SEMICOLON;
        }
        if (ch == '+') {
            ch = nextChar();
            return PLUS;
        }
        if (ch == '-') {
            ch = nextChar();
            return MINUS;
        }
        if (ch == '*') {
            ch = nextChar();
            return TIMES;
        }
        if (ch == '/') {
            ch = nextChar();
            return DIVIDED;
        }
        if (ch == '=') {
            ch = nextChar();
            if (ch == '=') {
                ch = nextChar();
                return EQ;
            }
            return ASSIGN;
        }
        if (ch == '!') {
            ch = nextChar();
            if (ch == '=') {
                ch = nextChar();
                return NE;
            }
        }
        if (ch == '<') {
            ch = nextChar();
            if (ch == '=') {
                ch = nextChar();
                return LE;
            }
            return LT;
        }
        if (ch == '>') {
            ch = nextChar();
            if (ch == '=') {
                ch = nextChar();
                return GE;
            }
            return GT;
        }
        if (ch == '.') {
            ch = nextChar();
            return DOT;
        }
        if (ch == -1) {
            return EOI;
        }
        error("unknown symbol '%c'", ch);
        throw new LexerException();
    }
}
