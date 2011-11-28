/*
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Pozice ve vstupnim souboru.
 */
public class Position {

    /**
     * Cislo radky.
     */
    private int line;
    /**
     * Cislo sloupce.
     */
    private int col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", line, col);
    }
}
