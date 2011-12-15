/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: prikaz writeln.
 */
public class PrintTree extends AvailableTree {

    private ExpressionTree expression;

    public PrintTree(Position start, Position end, ExpressionTree expression) {
        super(start, end);
        this.expression = expression;
    }

    public ExpressionTree getExpression() {
        return expression;
    }

    public void setExpression(ExpressionTree expression) {
        this.expression = expression;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitPrint(this);
    }

    @Override
    public void generate(Context ctx) {
        ctx.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        expression.generate(ctx);
        switch (expression.getType()) {
            case INTVAR:
                ctx.println("invokevirtual java/io/PrintStream/println(I)V");
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("Writeln %s: [%s]", super.toString(), expression);
    }
}
