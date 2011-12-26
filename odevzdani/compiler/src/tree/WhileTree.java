/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: cyklus while.
 */
public class WhileTree extends AvailableTree {

    private BinaryTree condition;
    private BodyListTree body;

    public WhileTree(Position start, Position end, BinaryTree condition, BodyListTree body) {
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
        visitor.visitWhile(this);
    }

    @Override
    public void generate(Context ctx) {
        String lab1 = ctx.nextLabel();
        ctx.println(lab1 + ":");
        String lab2 = ctx.nextLabel();
        ctx.addAttr("lab", lab2);
        condition.generate(ctx);
        ctx.removeAttr("lab");
        body.generate(ctx);
        ctx.println("jmp " + lab1);
        ctx.println(lab2 + ":");
    }

    @Override
    public String toString() {
        return String.format("While %s: [%s, %s]", super.toString(), condition, body);
    }
}
