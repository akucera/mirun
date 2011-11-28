/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 * Vnitrni forma: promenna.
 */
public class VariableTree extends Tree {

    private String name;
    private Type type;
    //private int value;

    /**
     * Slot, v nemz je tato promenna.
     */
    private int slot;

    public VariableTree(Position start, Position end, String name) {
        super(start, end);
        this.name = name;
        //this.value = value;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    /*
    public int getValue() {
        return this.value;
    }
     */

    public void setType(Type type) {
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    /*
    public void setValue(int value) {
        this.value = value;
    }
     */


    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitVariable(this);
    }

    @Override
    public void generate(Context ctx) {
        // tato metoda by se nemela nikdy zavolat
        assert false;
    }

    @Override
    public String toString() {
        return String.format("Variable %s: [%s, %s, %d]", super.toString(), name, type, slot);
    }
}
