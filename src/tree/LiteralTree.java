/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import tree.BinaryTree.Operator;

/**
 * Vnitrni forma: literal.
 */
public class LiteralTree extends ExpressionTree {

    private Type type;
    /**
     * Value je instance java.lang.Integer nebo java.lang.Double.
     */
    private Object value;

    public LiteralTree(Position start, Position end, Type type, Object value) {
        super(start, end);
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void setType(Type type) {
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitLiteral(this);
    }

    @Override
    public void generate(Context ctx) {
        switch (type) {
            case INTVAR:
                ctx.println("ldc " + value);
                break;
            case REALVAR: {
                Double d = (Double) value;
                ctx.printf("ldc2_w %.2f%n", d.doubleValue());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Literal %s: [%s]", super.toString(), value);
    }

    @Override
    public Operator getOperator() {
        return null;
    }
}
