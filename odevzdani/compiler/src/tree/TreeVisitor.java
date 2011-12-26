/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package tree;

/**
 *
 * @author aka
 */
public interface TreeVisitor {

    void visitVariable(VariableTree t);

    void visitIdentifier(IdentifierTree t);

    void visitAssignment(AssignmentTree t);

    void visitProgram(ProgramTree t);

    void visitDeclarations(VariableDeclarationsTree t);

    void visitDeclaration(VariableDeclarationTree t);

    void visitBinary(BinaryTree t);

    void visitLiteral(LiteralTree t);

    void visitBodyList(BodyListTree t);

    void visitIf(IfTree t);
    
    void visitWhile(WhileTree t);

    void visitFor(ForTree t);

    void visitMethod(MethodDeclarationTree t);

    void visitMethods(MethodDeclarationsTree t);

    void visitMethodBlock(MethodTree t);
}
