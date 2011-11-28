/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Vnitrni forma: program.
 */
public class ProgramTree extends Tree {

    private String name;
    private MethodDeclarationsTree methods;
    private VariableDeclarationsTree declarations;
    private BodyListTree body;
    private SymTab symTab;
    private int stackDepth;

    public ProgramTree(Position start, Position end, String name,
            MethodDeclarationsTree methods, VariableDeclarationsTree declarations,
            BodyListTree body, SymTab symTab) {
        super(start, end);
        this.name = name;
        this.methods = methods;
        this.declarations = declarations;
        this.body = body;
        this.symTab = symTab;
    }

    public int getStackDepth() {
        return stackDepth;
    }

    public void setStackDepth(int stackDepth) {
        this.stackDepth = stackDepth;
    }

    public MethodDeclarationsTree getMethods() {
        return this.methods;
    }

    public VariableDeclarationsTree getDeclarations() {
        return this.declarations;
    }

    public BodyListTree getBody() {
        return this.body;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitProgram(this);
    }

    @Override
    public void generate(Context ctx) {
        DateFormat df = DateFormat.getDateTimeInstance();
        Calendar c = Calendar.getInstance();
        ctx.println("; Compiled on " + df.format(c.getTime()));
        ctx.println(".class public output/" + name);
        ctx.println(".super java/lang/Object");
        methods.generate(ctx);
        //test
        //int i = 10;
        ctx.println(".method public static main([Ljava/lang/String;)V");
        ctx.println(".limit stack " + this.getStackDepth());
        if (this.symTab != null) {
            ctx.println(".limit locals " + this.symTab.getSize());
        } else {
            ctx.println(".limit locals " + 0);
        }
        declarations.generate(ctx);
        body.generate(ctx);
        ctx.println("return");
        ctx.println(".end method");

    }

    @Override
    public String toString() {
        return String.format("Program %s: ['%s', %s]", super.toString(), name);
    }
}
