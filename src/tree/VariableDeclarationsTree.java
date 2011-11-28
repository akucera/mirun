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
public class VariableDeclarationsTree extends Tree{

    private List<VariableDeclarationTree> declarations;

    public VariableDeclarationsTree(Position start, Position end, List<VariableDeclarationTree> declarations){
        super(start, end);
        this.declarations = declarations;
    }

    public List<VariableDeclarationTree> getDeclarations(){
        return this.declarations;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitDeclarations(this);
    }

    @Override
    public void generate(Context ctx) {
        for (VariableDeclarationTree t : declarations) {
            t.generate(ctx);
        }
    }

}
