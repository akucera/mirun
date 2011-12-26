package tree;

public abstract class DeclarationTree extends AvailableTree {

	public DeclarationTree(Position start, Position end) {
		super(start, end);
	}

	@Override
	public void accept(TreeVisitor visitor) {
	}

	@Override
	public void generate(Context ctx){
	}

}
