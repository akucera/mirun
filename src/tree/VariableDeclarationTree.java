/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 *
 * @author aka
 */
public class VariableDeclarationTree extends Tree{

    private AssignmentTree a;
    /*
     * IdentifierTree tu je pro pomocne zjistovani typu do semantickeho analyzatoru
     */
    private IdentifierTree i;

    public VariableDeclarationTree(Position start, Position end, AssignmentTree a){
        super(start,end);
        this.a = a;
        setIdentifier(a);
    }

    public void setIdentifier(AssignmentTree a) {
        this.i = a.getIdentifier();
    }


    
    public Type getType() {
        return this.i.getType();
    }

    public String getName() {
        return this.i.getName();
     }
     

    public AssignmentTree getAssignment(){
        return this.a;
    }


    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitDeclaration(this);
    }

    @Override
    public void generate(Context ctx) {
        a.generate(ctx);
    }


}
