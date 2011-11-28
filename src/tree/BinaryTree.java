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

        ADD, SUB, MUL, DDIV, IDIV,
        EQ, NE, LT, GT, LE, GE
    }
    private Operator operator;
    private ExpressionTree leftOperand;
    private ExpressionTree rightOperand;
    private Type type;

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
        if (operator == Operator.DDIV) {
            return Type.REALVAR;
        }
        if (leftOperand.getType() == Type.INTVAR && rightOperand.getType() == Type.INTVAR) {
            return Type.INTVAR;
        }
        return Type.REALVAR;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitBinary(this);
    }

    @Override
    public void generate(Context ctx) {
        if((type == Type.REALVAR) && (operator == Operator.IDIV) ){
            operator = Operator.DDIV;
        }
        leftOperand.generate(ctx);
        Type t1 = leftOperand.getType();
        if (t1 == Type.INTVAR && operator == Operator.DDIV) {
            ctx.println("i2d");
            t1 = Type.REALVAR;
        }
        if(t1 == Type.REALVAR && rightOperand.getType() == Type.INTVAR){
            rightOperand.setType(Type.REALVAR);
        }
        rightOperand.generate(ctx);
        Type t2 = rightOperand.getType();
        if (t2 == Type.INTVAR && operator == Operator.DDIV) {
            ctx.println("i2d");
            t2 = Type.REALVAR;
        }
        
        boolean integer = (t1 == Type.INTVAR) && (t2 == Type.INTVAR);
        switch (operator) {
            case ADD:
                if (integer) {
                    ctx.println("iadd");
                } else {
                    ctx.println("dadd");
                }
                break;
            case SUB:
                if (integer) {
                    ctx.println("isub");
                } else {
                    ctx.println("dsub");
                }
                break;
            case MUL:
                if (integer) {
                    ctx.println("imul");
                } else {
                    ctx.println("dmul");
                }
                break;
            case DDIV:
                ctx.println("ddiv");
                break;
            case IDIV:
                ctx.println("idiv");
                break;
            case EQ:
                if (integer) {
                    ctx.println("if_icmpne " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpl");
                    ctx.println("ifne " + ctx.getAttr("lab"));
                }
                break;
            case NE:
                if (integer) {
                    ctx.println("if_icmpeq " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpl");
                    ctx.println("ifeq " + ctx.getAttr("lab"));
                }
                break;
            case LT:
                if (integer) {
                    ctx.println("if_icmpge " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpg");
                    ctx.println("ifge " + ctx.getAttr("lab"));
                }
                break;
            case GT:
                if (integer) {
                    ctx.println("if_icmple " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpl");
                    ctx.println("ifle " + ctx.getAttr("lab"));
                }
                break;
            case LE:
                if (integer) {
                    ctx.println("if_icmpgt " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpg");
                    ctx.println("ifgt " + ctx.getAttr("lab"));
                }
                break;
            case GE:
                if (integer) {
                    ctx.println("if_icmplt " + ctx.getAttr("lab"));
                } else {
                    ctx.println("dcmpl");
                    ctx.println("iflt " + ctx.getAttr("lab"));
                }
        }
    }

    @Override
    public String toString() {
        return String.format("Binary %s: [%s, %s, %s]", super.toString(), leftOperand, operator, rightOperand);
    }
}
