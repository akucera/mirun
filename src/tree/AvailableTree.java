/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: prikaz.
 */
public abstract class AvailableTree extends Tree {

    public AvailableTree(Position start, Position end) {
        super(start, end);
    }
}
