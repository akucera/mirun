package tree;

public class ArrayTree extends VariableTree {
	
	protected Integer length;
	
	public ArrayTree(Position start, Position end, String name, Integer length) {
		super(start, end, name);
		this.length = length;
		this.type = Type.ARRAYVAR;
	}

	@Override
    public void accept(TreeVisitor visitor) {
        //visitor.visitVariable(this);
    }

    @Override
    public void generate(Context ctx) {
        ctx.println("pushc " + length);
        ctx.println("arrdef " + address);
    }

    @Override
    public String toString() {
        return String.format("Array %s: [%s, %s, %d, %d]", super.toString(), name, type, length, address);
    }

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}
