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

        INTVAR, REALVAR, VOID
    }
    private String name;
    private SymTab symTab;
    private List<Type> paramTypes;
    private BodyListTree body;
    private ReturnType returnType;
    private ExpressionTree retTree;
    private int stackDepth;

    public MethodDeclarationTree(Position start, Position end, String name,
            ReturnType returnType, List<Type> paramTypes,
            BodyListTree body, ExpressionTree retTree, SymTab symTab) {
        super(start, end);
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.body = body;
        this.retTree = retTree;
        this.symTab = symTab;
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

    public ExpressionTree getRetTree() {
        return this.retTree;
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

        ctx.print(".method public static " + name + "(");
        for (Type t : paramTypes) {
            switch (t) {
                case INTVAR:
                    ctx.print("I");
                    break;
                case REALVAR:
                    ctx.print("D");
                    break;
            }
        }
        ctx.print(")");
        switch (getReturnType()) {
            case INTVAR:
                ctx.println("I");
                break;
            case REALVAR:
                ctx.println("D");
                break;
            case VOID:
                ctx.println("V");
                break;
        }
        //test
        //int i = 10;
        ctx.println(".limit stack " + this.getStackDepth());
        if (this.symTab != null) {
            ctx.println(".limit locals " + this.symTab.getSize());
        } else {
            ctx.println(".limit locals " + 0);
        }

        body.generate(ctx);
        switch (getReturnType()) {
            case INTVAR:
                retTree.generate(ctx);
                ctx.println("ireturn");
                break;
            case REALVAR:
                retTree.generate(ctx);
                ctx.println("dreturn");
                break;
            case VOID:
                ctx.println("return");
                break;
        }
        ctx.println(".end method");

    }

    @Override
    public String toString() {
        return String.format("Program %s: ['%s', %s]", super.toString(), name);
    }
}
