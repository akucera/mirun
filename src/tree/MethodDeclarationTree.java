/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.List;

/**
 * Vnitrni forma: program.
 */
public class MethodDeclarationTree extends Tree {

    public enum ReturnType {
        INTVAR, VOID, STRINGVAR, ARRAYVAR
    }
    private String name;
    private SymTab symTab;
    private List<Type> paramTypes;
    private BodyListTree body;
    private ReturnType returnType;
    private int stackDepth;
    private List<AssignmentTree> params;

    public MethodDeclarationTree(Position start, Position end, String name,
            ReturnType returnType, List<Type> paramTypes, List<AssignmentTree> params,
            BodyListTree body, SymTab symTab) {
        super(start, end);
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.body = body;
        this.symTab = symTab;
        this.params = params;
    }

    public int getStackDepth() {
        return stackDepth;
    }

    public void setStackDepth(int stackDepth) {
        this.stackDepth = stackDepth;
    }

    public ReturnType getReturnType() {
        return this.returnType;
    }

    public BodyListTree getBody() {
        return this.body;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitMethod(this);
    }

    @Override
    public void generate(Context ctx) {
        ctx.println(name + ":");
        for (AssignmentTree a : params) {
        	a.generate(ctx);
        }
        body.generate(ctx);
    }

    @Override
    public String toString() {
        return String.format("Program %s: ['%s', %s]", super.toString(), name);
    }
}
