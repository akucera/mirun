/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: if.
 */
public class IfTree extends AvailableTree {

    private BinaryTree condition;
    private BodyListTree body;

    public IfTree(Position start, Position end, BinaryTree condition,
            BodyListTree body) {
        super(start, end);
        this.condition = condition;
        this.body = body;
    }

    public BinaryTree getCondition() {
        return condition;
    }

    public BodyListTree getBody() {
        return body;
    }


    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitIf(this);
    }

    @Override
    public void generate(Context ctx) {
        String lab1 = ctx.nextLabel();
        ctx.addAttr("lab", lab1);
        condition.generate(ctx);
        ctx.removeAttr("lab");
        body.generate(ctx);
        ctx.println(lab1 + ":");
    }

    @Override
    public String toString() {
        return String.format("If %s: [%s, %s, %s]", super.toString(), condition);
    }
}
