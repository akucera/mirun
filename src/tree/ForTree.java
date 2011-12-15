/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: cyklus for.
 */
public class ForTree extends AvailableTree {

    private DeclarationTree identifier;
    private BinaryTree condition;
    private AssignmentTree step;
    private BodyListTree body;

    /**
     * Slot pro docasnou promennou.
     */
    private int tempVar;

    public ForTree(Position start, Position end, DeclarationTree identifier,
            BinaryTree condition, AssignmentTree step, BodyListTree body) {
        super(start, end);
        this.identifier = identifier;
        this.condition = condition;
        this.step = step;
        this.body = body;
    }

    public DeclarationTree getIdentifier() {
        return this.identifier;
    }

    public BodyListTree getBody() {
        return this.body;
    }

    public void setBody( BodyListTree body) {
        this.body = body;
    }

    
    public BinaryTree getCondition() {
        return this.condition;
    }

    public AssignmentTree getStep() {
        return this.step;
    }
/*
    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }
     */

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitFor(this);
    }

    @Override
    public void generate(Context ctx) {
        identifier.generate(ctx);
        String lab2 = ctx.nextLabel();
        String lab1 = ctx.nextLabel();
        ctx.println(lab2 + ":");
        ctx.addAttr("lab", lab1);
        condition.generate(ctx);
        ctx.removeAttr("lab");
        body.generate(ctx);
        step.generate(ctx);
        ctx.println("goto " + lab2);
        ctx.println(lab1 + ":");
    }

    @Override
    public String toString() {
        return String.format("For %s: [%s, %s, %s, %s]", super.toString(), identifier, condition, step, body);
    }
}
