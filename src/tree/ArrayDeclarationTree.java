package tree;

public class ArrayDeclarationTree extends DeclarationTree {
	
	private ArrayTree arrTree;
	
	public ArrayDeclarationTree(Position start, Position end, ArrayTree arrTree) {
		super(start, end);
		this.arrTree = arrTree;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generate(Context ctx) {
		// TODO Auto-generated method stub
	}

	public ArrayTree getArrTree() {
		return arrTree;
	}

	public void setArrTree(ArrayTree arrTree) {
		this.arrTree = arrTree;
	}

}
