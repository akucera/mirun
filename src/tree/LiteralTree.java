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
    private ConstTab constTab;
    /**
     * Value je instance java.lang.Integer nebo String
     */
    private Object value;

    public LiteralTree(Position start, Position end, Type type, Object value, ConstTab constTab) {
        super(start, end);
        this.type = type;
        this.value = value;
        this.constTab = constTab;
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
                ctx.println("pushc " + value);
                break;
            case STRINGVAR:
            	ctx.println("pushsc " + constTab.getAddress(value.toString()));
            	break;
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
