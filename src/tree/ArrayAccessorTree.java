package tree;

import tree.BinaryTree.Operator;

public class ArrayAccessorTree extends ExpressionTree {

	private ArrayTree array;
	private ExpressionTree accessExpression;
	private MethodTree accessMethod;
	/**
	 * Urcuje, zda ma byt hodnota pole vlozena na zasobnik.
	 */
	private boolean isAccess;
	
	public ArrayAccessorTree(Position start, Position end, ArrayTree array, ExpressionTree accessExpression, MethodTree accessMethod, boolean isAccess) {
		super(start, end);
		this.array = array;
		this.accessExpression = accessExpression;
		this.accessMethod = accessMethod;
		this.isAccess = isAccess;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(Type type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accept(TreeVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generate(Context ctx) {
		// TODO Auto-generated method stub

	}

}
