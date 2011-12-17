/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Typ promenne.
 */
public enum Type {

    INTVAR(1),
    STRINGVAR(1),
    ARRAYVAR(1);
    private int size;

    private Type(int size) {
        this.size = size;
    }

    /**
     * Vraci pocet slotu pro dany typ.
     */
    public int getSize() {
        return size;
    }
}
