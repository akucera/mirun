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
    private boolean fromStack;

    public AssignmentTree(Position start, Position end, IdentifierTree identifier, ExpressionTree expression, MethodTree method, boolean leftMethod, boolean fromStack) {
        super(start, end);
        this.identifier = identifier;
        this.leftMethod = leftMethod;
        this.fromStack = fromStack;
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
    	if (fromStack) {
    		switch (identifier.getType()) {
				case INTVAR:
				case ARRAYVAR:
					ctx.println("pop " + identifier.getVarAddress());
					break;
				case STRINGVAR:
					// string is in constant table
					break;
    		}
    	} else {
    		if (leftMethod) {
    			method.generate(ctx);
    		} else {
    			switch (identifier.getType()) {
	    			case INTVAR:
	    				expression.generate(ctx);
	    				ctx.println("pop " + identifier.getVarAddress());
	    				break;
	    			case STRINGVAR:
	    				// string is in constant table
	    				break;
    			}
    		}
    	}
    }

    @Override
    public String toString() {
        return String.format("Assignment %s: [%s, %s]", super.toString(), identifier, expression);
    }
}
