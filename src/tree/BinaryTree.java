/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: binarni operator.
 */
public class BinaryTree extends ExpressionTree {

	public enum Operator {

		ADD, SUB, MUL,
		EQ, NE, LT, GT, LE, GE
	}
	private Operator operator;
	private ExpressionTree leftOperand;
	private ExpressionTree rightOperand;

	public BinaryTree(Position start, Position end, Operator operator,
			ExpressionTree leftOperand, ExpressionTree rightOperand) {
		super(start, end);
		this.operator = operator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}



	@Override
	public Operator getOperator() {
		return operator;
	}

	public ExpressionTree getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(ExpressionTree leftOperand) {
		this.leftOperand = leftOperand;
	}

	public ExpressionTree getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(ExpressionTree rightOperand) {
		this.rightOperand = rightOperand;
	}

	/**
	 * Vraci typ vysledku.
	 */
	 public Type getType() {
		return Type.INTVAR;
	}

	@Override
	public void setType(Type type) {
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitBinary(this);
	}

	@Override
	public void generate(Context ctx) {
		leftOperand.generate(ctx);
		rightOperand.generate(ctx);

		switch (operator) {
			case ADD:
				ctx.println("badd");
				break;
			case SUB:
				ctx.println("bsub");
				break;
			case MUL:
				ctx.println("bmul");
				break;
			case EQ:
				ctx.println("jeq " + ctx.getAttr("lab"));
				break;
			case NE:
				ctx.println("jneq " + ctx.getAttr("lab"));
				break;
			case LT:
				ctx.println("jlt " + ctx.getAttr("lab"));
				break;
			case GT:
				ctx.println("jgt " + ctx.getAttr("lab"));
				break;
			case LE:
				ctx.println("jelt " + ctx.getAttr("lab"));
				break;
			case GE:
				ctx.println("jegt " + ctx.getAttr("lab"));
		}
	}

	@Override
	public String toString() {
		return String.format("Binary %s: [%s, %s, %s]", super.toString(), leftOperand, operator, rightOperand);
	}
}
