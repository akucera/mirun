/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Kontext pouzity pri generovani kodu.
 */
public interface Context {

    /**
     * Zapis do vystupniho streamu.
     */
    void print(String msg);

    void println(String msg);

    void printf(String format, Object... args);

    /**
     * Vrati dalsi navesti.
     */
    String nextLabel();

    /**
     * Prida atribut.
     */
    void addAttr(String name, Object value);

    /**
     * Vrati hodnotu atributu.
     */
    Object getAttr(String name);

    /**
     * Odstrani atribut.
     */
    void removeAttr(String name);
}
