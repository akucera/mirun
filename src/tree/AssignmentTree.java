/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: prirazeni.
 */
public class AssignmentTree extends AvailableTree {

    private IdentifierTree identifier;
    private ExpressionTree expression;
    private MethodTree method;
    private boolean leftMethod;

    public AssignmentTree(Position start, Position end, IdentifierTree identifier, ExpressionTree expression, MethodTree method, boolean leftMethod) {
        super(start, end);
        this.identifier = identifier;
        this.leftMethod = leftMethod;
        if (leftMethod) {
            this.method = method;
        } else {
            this.expression = expression;
        }
    }

    public IdentifierTree getIdentifier() {
        return identifier;
    }

    public ExpressionTree getExpression() {
        return expression;
    }

    public MethodTree getMethod() {
        return this.method;
    }

    public boolean getLeftMethod() {
        return this.leftMethod;
    }

    public void setExpression(ExpressionTree expression) {
        this.expression = expression;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitAssignment(this);
    }

    @Override
    public void generate(Context ctx) {
        if (leftMethod) {
            method.generate(ctx);
        } else {
            switch (identifier.getType()) {
                case INTVAR:
                    expression.setType(Type.INTVAR);
                    break;
            }
            expression.generate(ctx);
        }
        switch (identifier.getType()) {
            case INTVAR:
                ctx.print("i");
                break;
        }
        ctx.println("store " + identifier.getSlot());
    }

    @Override
    public String toString() {
        return String.format("Assignment %s: [%s, %s]", super.toString(), identifier, expression);
    }
}
