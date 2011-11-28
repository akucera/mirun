/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import tree.BinaryTree.Operator;

/**
 * Vnitrni forma: vyraz.
 */
public abstract class ExpressionTree extends Tree {

    public ExpressionTree(Position start, Position end) {
        super(start, end);
    }

    /**
     * Vraci typ vyrazu.
     */
    public abstract Type getType();

    public abstract Operator getOperator();

    public abstract void setType(Type type);
}
