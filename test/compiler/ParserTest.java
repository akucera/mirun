/*
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tree.AssignmentTree;
import tree.BinaryTree;
import tree.BodyListTree;
import tree.ExpressionTree;
import tree.ForTree;
import tree.IfTree;
import tree.MethodDeclarationsTree;
import tree.Position;
import tree.PrintTree;
import tree.ProgramTree;
import tree.SymTab;
import tree.VariableDeclarationsTree;
import tree.WhileTree;
import static org.junit.Assert.*;

/**
 * Test syntaktickeho analyzatoru.
 */
public class ParserTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProgram() throws IOException {
        String s = "program ahoj;" +
                "methods" +
                "  method ahojsvete void ()" +
                "   println(ahojsvete)" +
                "   return" +
                "  dohtem" +
                " sdohtem;" +
                " declare" +
                "  int i = 2" +
                " eralced;" +
                "margorp;";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        ProgramTree pt = p.program();
    }

    @Test
    public void testProgramFail() throws IOException {
        try {
            String s = "program ahoj;" +
                    "methods" +
                    "  methodFUJFUJ ahojsvete void ()" +
                    "   println(ahojsvete)" +
                    "  dohtem;" +
                    " sdohtem;" +
                    " declare" +
                    "  int i = 2" +
                    " eralced;" +
                    "margorp;";
            Reader r = new CharArrayReader(s.toCharArray());
            Lexer lex = new Lexer(r);
            Parser p = new Parser(lex);
            p.program();
            fail("Invalid method declaration block!");
        } catch (ParserException e) {
            // ok
        }
    }

    @Test
    public void testMethodDeclarations() throws IOException {
        String s = "methods" +
                "method ahojsvete void ()" +
                "    println(ahojsvete)" +
                "    return" +
                "  dohtem" +
                "sdohtem";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        MethodDeclarationsTree methods = p.methodDeclarations();
    }

    @Test
    public void testDeclarationBlock() throws IOException {
        String s = "declare" +
                "int i = 2;" +
                "real b = 2.3;" +
                "int c = 11111" +
                "eralced";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        VariableDeclarationsTree var = p.declarationBlock();
    }

    @Test
    public void testBodyList() throws IOException {
        SymTab symTab = new SymTab();
        String s = "int i = 2;" +
                "real b = 2.3;" +
                "int c = 11111" +
                "if(a < 3)" +
                "i = 2" +
                "fi;" +
                "for(int i = 1;i<3;i=i+1;)" +
                "println(i)" +
                "rof";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        BodyListTree body = p.bodyList(symTab);
    }

    @Test
    public void testIfBlock() throws IOException {
        SymTab symTab = new SymTab();
        String s = "if(a < 3)" +
                "i = 2" +
                "fi";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        IfTree ift = p.ifBlock(symTab);
    }

    @Test
    public void testForBlock() throws IOException {
        SymTab symTab = new SymTab();
        String s = "for(int i = 1;i<3;i=i+1;)" +
                "println(i)" +
                "rof";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        ForTree fort = p.forBlock(symTab);
    }

    @Test
    public void testWhlieBlock() throws IOException {
        SymTab symTab = new SymTab();
        String s = "while(i>2)" +
                "println(i);" +
                "i = i - 1" +
                "elihw";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        WhileTree wt = p.whileBlock(symTab);
    }

    @Test
    public void testAssignment() throws IOException {
        SymTab symTab = new SymTab();
        String s = "i = i - 1";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        AssignmentTree at = p.assignment(symTab);
    }

    @Test
    public void testPrint() throws IOException {
        SymTab symTab = new SymTab();
        String s = "println(i + i - 1)";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        PrintTree pt = p.print(symTab);
    }

    @Test
    public void testCondition() throws IOException {
        SymTab symTab = new SymTab();
        String s = "(i + i == 1)";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        BinaryTree con = p.condition(symTab);
    }

    @Test
    public void testForCondition() throws IOException {
        SymTab symTab = new SymTab();
        Position p1 = new Position(0, 0);
        String s = "(int i = 2;i + i == 1; i= i -1;)";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        ForTree ft = p.forCondition(p1, symTab);
    }

    @Test
    public void testVyraz() throws IOException {
        SymTab symTab = new SymTab();
        String s = "i + ahoj + ddd + 23 + 2323.232";
        Reader r = new CharArrayReader(s.toCharArray());
        Lexer lex = new Lexer(r);
        Parser p = new Parser(lex);
        ExpressionTree ex = p.vyraz(symTab);
    }
}
