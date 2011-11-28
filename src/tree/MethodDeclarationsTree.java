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
public class MethodDeclarationsTree extends Tree{

    private List<MethodDeclarationTree> methods;

    public MethodDeclarationsTree(Position start, Position end, List<MethodDeclarationTree> methods){
        super(start, end);
        this.methods = methods;
    }

    public List<MethodDeclarationTree> getMethods(){
        return this.methods;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitMethods(this);
    }

    @Override
    public void generate(Context ctx) {
        for (MethodDeclarationTree t : methods) {
            t.generate(ctx);
        }
    }

}
