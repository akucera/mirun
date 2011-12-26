/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.List;

/**
 *
 * @author aka
 */
public class BodyListTree extends Tree {

    private List<AvailableTree> available;

    public BodyListTree(Position start, Position end, List<AvailableTree> available){
        super(start, end);
        this.available = available;
    }

    public List<AvailableTree> getAvailable(){
        return this.available;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitBodyList(this);
    }

    @Override
    public void generate(Context ctx) {
        for (AvailableTree t : available) {
            t.generate(ctx);
        }
    }

}
