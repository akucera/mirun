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
    private ConstTab constTab;
    private int stackDepth;

    public ProgramTree(Position start, Position end, String name,
            MethodDeclarationsTree methods, VariableDeclarationsTree declarations,
            BodyListTree body, SymTab symTab, ConstTab constTab) {
        super(start, end);
        this.name = name;
        this.methods = methods;
        this.declarations = declarations;
        this.body = body;
        this.symTab = symTab;
        this.constTab = constTab;
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
        constTab.genereta(ctx);
        methods.generate(ctx);
        declarations.generate(ctx);
        body.generate(ctx);
        ctx.println("stop");
    }

    @Override
    public String toString() {
        return String.format("Program %s: ['%s', %s]", super.toString(), name);
    }
}
