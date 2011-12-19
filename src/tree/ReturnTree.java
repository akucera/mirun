package tree;

public class ReturnTree extends AvailableTree {
	
	private ExpressionTree returnExpression;
	
	public ReturnTree(Position start, Position end, ExpressionTree returnExpression) {
		super(start, end);
		this.returnExpression = returnExpression;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generate(Context ctx) {
		if (returnExpression != null) {
			returnExpression.generate(ctx);
		}
		ctx.println("mret");
	}

}
