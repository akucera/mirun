/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.util.List;
import tree.AssignmentTree;
import tree.AvailableTree;
import tree.BinaryTree;
import tree.BinaryTree.Operator;
import tree.BodyListTree;
import tree.ExpressionTree;
import tree.ForTree;
import tree.IdentifierTree;
import tree.IfTree;
import tree.LiteralTree;
import tree.MethodDeclarationTree;
import tree.MethodDeclarationTree.ReturnType;
import tree.MethodDeclarationsTree;
import tree.MethodTree;
import tree.PrintTree;
import tree.ProgramTree;
import tree.Tree;
import tree.TreeVisitor;
import tree.Type;
import tree.VariableDeclarationTree;
import tree.VariableDeclarationsTree;
import tree.VariableTree;
import tree.WhileTree;

/**
 * Semanticky analyzator.
 * Kontroluje typy a zjistuje maximalni hloubku zasobniku.
 */
public class SemanticAnalyzer implements TreeVisitor {

    /**
     * Aktualni hloubka zasobniku.
     */
    private int depth;
    /**
     * Maximalni hloubka zasobniku.
     */
    private int maxDepth;
    /**
     * Pocet nalezenych chyb.
     */
    private int errorCount;

    /**
     * Vypise chybu.
     */
    private void error(Tree t, String msg) {
        errorCount++;
        System.out.printf("%s%s: %s%n", t.getStart(), t.getEnd(), msg);

    }

    /**
     * Vlozi hodnotu na zasobnik.
     */
    private void push(Type t) {
        depth += t.getSize();
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    /**
     * Vybere hodnotu ze zasobniku.
     */
    private void pop(Type t) {
        depth -= t.getSize();
    }

    /**
     * Vynuluje hodnoty velikosti zasobniku pri navratu z metody.
     */
    private void nullStack() {
        this.depth = 0;
        this.maxDepth = 0;
    }

    public void visitProgram(ProgramTree t) {
        MethodDeclarationsTree methods = t.getMethods();
        methods.accept(this);
        VariableDeclarationsTree declarations = t.getDeclarations();
        declarations.accept(this);
        BodyListTree body = t.getBody();
        body.accept(this);
        t.setStackDepth(maxDepth);
        if (errorCount > 0) {
            throw new SemanticException();
        }
    }

    public void visitMethods(MethodDeclarationsTree t) {
        List<MethodDeclarationTree> methods = t.getMethods();
        for (MethodDeclarationTree method : methods) {
            method.accept(this);
        }
    }

    public void visitMethod(MethodDeclarationTree t) {
        BodyListTree body = t.getBody();
        body.accept(this);
        ExpressionTree retTree = t.getRetTree();
        if (retTree != null) {
            retTree.accept(this);
            ReturnType retType = t.getReturnType();
            switch (retType) {
                case INTVAR:
                    if (retTree == null) {
                        error(t, "null return in INT method!");
                    }
                    if (retTree.getType() != Type.INTVAR) {
                        error(t, "wrong return tree type, INT required!");
                    }
                    push(Type.INTVAR);
                    break;
                case REALVAR:
                    if (retTree == null) {
                        error(t, "null return in REAL method!");
                    }
                    if (retTree.getType() != Type.REALVAR) {
                        error(t, "wrong return tree type, REAL required!");
                    }
                    push(Type.REALVAR);
                    break;
                case VOID:
                    if (retTree != null) {
                        error(t, "not null return in VOID method!");
                    }
                    break;
            }
        }
        t.setStackDepth(maxDepth);
        nullStack();
    }

    public void visitAssignment(AssignmentTree t) {
        IdentifierTree i = t.getIdentifier();
        i.accept(this);
        if (t.getLeftMethod()) {
            MethodTree m = t.getMethod();
            m.accept(this);
        } else {
            ExpressionTree e = t.getExpression();
            e.accept(this);
            if (i.getType() == Type.REALVAR) {
                if (e.getType() == Type.INTVAR && e.getOperator() == Operator.IDIV) {
                    pop(i.getType());
                    return;
                }
            }
            if (i.getType() != e.getType()) {
                error(e, "cannot assign " + e.getType() + " to " + i.getType());
            }
            pop(i.getType());
        }

    }

    public void visitBinary(BinaryTree t) {
        ExpressionTree e1 = t.getLeftOperand();

        Type t1 = e1.getType();
        ExpressionTree e2 = t.getRightOperand();

        Type t2 = e2.getType();
        if (t1 == Type.REALVAR) {
            if (t2 == Type.INTVAR && e2.getOperator() == Operator.IDIV) {
                push(Type.REALVAR);
                push(Type.REALVAR);
                pop(t2);
                pop(t2);
                t2 = Type.REALVAR;
            }
        }
        e1.accept(this);
        e2.accept(this);
        if (t.getOperator() == Operator.DDIV && t1 == Type.INTVAR) {
            // i2d
            pop(Type.INTVAR);
            push(Type.REALVAR);
            t1 = Type.REALVAR;
        }
        if (t.getOperator() == Operator.DDIV && t2 == Type.REALVAR) {
            // i2d
            pop(Type.INTVAR);
            push(Type.REALVAR);
        }
        if (t1 != t2) {
            error(t, "operands must be of the same type");
        }
        pop(t1);
        pop(t2);
        switch (t.getOperator()) {
            case ADD:
            case SUB:
            case MUL:
                if (t1 == Type.INTVAR) {
                    push(Type.INTVAR);
                } else {
                    push(Type.REALVAR);
                }
                break;
            case DDIV:
                push(Type.REALVAR);
                break;
            case IDIV:
                push(Type.INTVAR);
                break;
            case EQ:
            case NE:
            case LT:
            case GT:
            case LE:
            case GE:
        }
    }

    public void visitIdentifier(IdentifierTree t) {
        if (!t.isLeftValue()) {
            push(t.getType());
        }
    }

    public void visitLiteral(LiteralTree t) {
        push(t.getType());
    }

    public void visitVariable(VariableTree t) {
    }

    public void visitFor(ForTree t) {
        VariableDeclarationTree i = t.getIdentifier();
        Type type;
        i.accept(this);
        if ((i.getType() == Type.INTVAR)) {
            type = Type.INTVAR;
        } else {
            type = Type.REALVAR;
        }
        BinaryTree b = t.getCondition();
        b.accept(this);
        if (b.getType() != type) {
            error(b, type + " value expected");
        }
        AssignmentTree a = t.getStep();
        a.accept(this);
        IdentifierTree i2 = a.getIdentifier();
        if (i2.getType() != type) {
            error(i2, type + " value expected");
        }
        ExpressionTree e2 = a.getExpression();
        if (e2.getType() != type) {
            error(e2, type + " value expected");
        }

        BodyListTree body = t.getBody();
        body.accept(this);
    }

    public void visitIf(IfTree t) {
        BinaryTree c = t.getCondition();
        c.accept(this);
        BodyListTree body = t.getBody();
        body.accept(this);
    }

    public void visitWhile(WhileTree t) {
        BinaryTree c = t.getCondition();
        c.accept(this);
        BodyListTree body = t.getBody();
        body.accept(this);
    }

    public void visitPrint(PrintTree t) {
        push(Type.INTVAR);
        ExpressionTree e = t.getExpression();
        e.accept(this);
        pop(e.getType());
        pop(Type.INTVAR);
    }

    public void visitDeclarations(VariableDeclarationsTree t) {
        List<VariableDeclarationTree> declarations = t.getDeclarations();
        for (VariableDeclarationTree declaration : declarations) {
            declaration.accept(this);
        }
    }

    public void visitDeclaration(VariableDeclarationTree t) {
        AssignmentTree a = t.getAssignment();
        a.accept(this);
    }

    public void visitBodyList(BodyListTree t) {
        List<AvailableTree> available = t.getAvailable();
        for (AvailableTree a : available) {
            a.accept(this);
        }
    }

    public void visitMethodBlock(MethodTree t) {
        push(Type.INTVAR);
        boolean isRight = t.getIsRight();
        ReturnType retType = t.getReturnType();
        List<ExpressionTree> params = t.getParams();
        for (ExpressionTree e : params) {
            e.accept(this);
        }
        switch (retType) {
            case INTVAR:
                if (!isRight) {
                    error(t, "nonVOID method must be assigned!");
                }
                push(Type.INTVAR);
                break;
            case REALVAR:
                if (!isRight) {
                    error(t, "nonVOID method must be assigned!");
                }
                push(Type.REALVAR);
                break;
            case VOID:
                if (isRight) {
                    error(t, "VOID method can't be assigned!");
                }
                break;
        }
        pop(Type.INTVAR);
    }
}
