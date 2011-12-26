/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: strom.
 */
public abstract class Tree {

    /**
     * Pocatecni a koncova pozice ve vstupnim souboru.
     */
    protected Position start, end;

    public Tree(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return String.format("%s%s", start, end);
    }

    /**
     * Implementace navrhoveho vzoru Visitor.
     */
    public abstract void accept(TreeVisitor visitor);

    /**
     * Generovani assembleru.
     */
    public abstract void generate(Context ctx);
}
