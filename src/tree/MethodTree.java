/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

import java.util.List;

import tree.BinaryTree.Operator;
import tree.MethodDeclarationTree.ReturnType;

/**
 * Vnitrni forma: if.
 */
public class MethodTree extends ExpressionTree {

    private String programName;
    private String methodName;
    private ReturnType ret;
    List<Type> paramTypes;
    List<ExpressionTree> params;

    /*
     * Slouzi pro kontrolu, zda se metoda s navratovym typem prirazuje.
     */
    private boolean isRight;

    public MethodTree(Position start, Position end, String programName,
            String methodName, ReturnType ret, List<Type> paramTypes, List<ExpressionTree> params,
            boolean isRight) {
        super(start, end);
        this.programName = programName;
        this.methodName = methodName;
        this.ret = ret;
        this.paramTypes = paramTypes;
        this.params = params;
        this.isRight = isRight;
    }


    public ReturnType getReturnType(){
        return this.ret;
    }

    public boolean getIsRight(){
        return this.isRight;
    }

    public List<ExpressionTree> getParams(){
        return this.params;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visitMethodBlock(this);
    }

    @Override
    public void generate(Context ctx) {
        for(ExpressionTree e : params){
        	ctx.println("; Zacatek parametru metody "+methodName);
            e.generate(ctx);
            // TODO bug negeneruji se parametry metody length
            ctx.println("; Konec parametru metody "+methodName);
        }
        ctx.println("call " + methodName);
    }

    @Override
    public String toString() {
        //return String.format("If %s: [%s, %s, %s]", super.toString(), condition);
        return null;
    }


	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setType(Type type) {
		// TODO Auto-generated method stub
		
	}
}
