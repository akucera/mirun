/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import tree.BinaryTree.Operator;

/**
 * Vnitrni forma: identifikator.
 */
public class IdentifierTree extends ExpressionTree {

    private String name;
    private VariableTree variable;

    /**
     * Je identifikator na leve strane prirazeni?
     */
    private boolean leftValue;

    public IdentifierTree(Position start, Position end, String name) {
        super(start, end);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VariableTree getVariable() {
        return variable;
    }

    public void setVariable(VariableTree variable) {
        this.variable = variable;
    }

    public boolean isLeftValue() {
        return leftValue;
    }

    public void setLeftValue(boolean leftValue) {
        this.leftValue = leftValue;
    }

    public int getVarAddress() {
        return variable.getAddress();
    }

    @Override
    public Type getType() {
        return variable.getType();
    }

    @Override
    public void setType(Type type) {
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitIdentifier(this);
    }

    @Override
    public void generate(Context ctx) {
        switch (variable.getType()) {
            case INTVAR:
                ctx.println("pushv " + variable.getAddress());
                break;
            case STRINGVAR:
            	ctx.println("pushsc " + variable.getAddress());
            	break;
            case ARRAYVAR:
            	ctx.println("pushv " + variable.getAddress());
            	break;
        }
    }

    @Override
    public String toString() {
        return String.format("Identifier %s: '%s'", super.toString(), name);
    }

    @Override
    public Operator getOperator() {
        return null;
    }
}
