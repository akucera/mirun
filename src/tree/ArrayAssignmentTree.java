package tree;

public class ArrayAssignmentTree extends AvailableTree {
	
	private ArrayAccessorTree arrAccessorTree;
    private ExpressionTree expression;
    private MethodTree method;
	
	public ArrayAssignmentTree(Position start, Position end, ArrayAccessorTree arrAccessorTree, ExpressionTree expression, MethodTree method) {
		super(start, end);
        this.arrAccessorTree = arrAccessorTree;
		this.method = method;
        this.expression = expression;
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
