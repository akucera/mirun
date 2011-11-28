/*
 * Prekladac jazyka MiniPascal do Java assembleru.
 * Autor: Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test prekladace.
 */
public class CompilerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Je nacteny radek komentar?
     * Komentare v .j souboru zacinaji strednikem.
     */
    private boolean isComment(String s) {
        if (s.length() > 0) {
            return s.charAt(0) == ';';
        }
        return false;
    }

    /**
     * Posloupnost bilych znaku nahradi jednou mezerou.
     */
    private String normalizeString(String orig) {
        String[] parts = orig.split("\\s+");
        String res = "";
        for (String s : parts) {
            if (s.length() > 0) {
                res += s + " ";
            }
        }
        return res;
    }

    /**
     * Nacte obsah souboru do retezce. Bile znaky nahradi jednou mezerou.
     */
    private String normalize(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String s, c = "";
        while ((s = in.readLine()) != null) {
            if (isComment(s)) {
                continue;
            }
            c += s + " ";
        }
        in.close();
        return normalizeString(c);
    }

    /**
     * Prelozi soubor a vystup porovna s ocekavanym vystupem.
     */
    private void test(String s) throws IOException {
        String in = "test/input/" + s + ".source";
        String out = "test/output/" + s + ".j";
        Compiler c = new Compiler();
        c.compile(in, out);
        String exp = normalize("test/expect/" + s + ".j");
        String res = normalize(out);
        assertEquals(exp, res);
    }

    /**
     * Prelozi soubor.
     */
    private void testCompile(String s) throws IOException {
        String in = "test/input/" + s + ".source";
        String out = "test/output/" + s + ".j";
        Compiler c = new Compiler();
        c.compile(in, out);
    }

    @Test
    public void testAssignment() throws Exception {
        test("Assignment");
    }

    @Test
    public void testBinary() throws Exception {
        test("BinaryAdd");
        test("BinarySub");
        test("BinaryMul");
        test("BinaryIDiv");
        test("BinaryDDiv");
    }

    @Test
    public void testBlock() throws Exception {
        test("Block");
    }

    @Test
    public void testFor() throws Exception {
        test("For1");
        test("For2");
    }

    @Test
    public void testIf() throws Exception {
        test("IfEQ1");
        test("IfEQ2");
        test("IfGE1");
        test("IfGE2");
        test("IfGT1");
        test("IfGT2");
        test("IfLE1");
        test("IfLE2");
        test("IfLT1");
        test("IfLT2");
        test("IfNE1");
        test("IfNE2");
    }

    @Test
    public void testIfIf() throws Exception {
        test("IfIf");
    }

    @Test
    public void testMethods() throws Exception {
        test("MethodInt");
        test("MethodReal");
        test("MethodVoid");
    }

    @Test
    public void testWhile() throws Exception {
        test("While1");
        test("While2");
    }

    @Test
    public void testWriteln() throws Exception {
        test("Writeln1");
        test("Writeln2");
    }

    
    @Test
    public void testCannotAssign() throws Exception {
        try {
            testCompile("error/CannotAssign1");
            fail("CannotAssign1");
        } catch (SemanticException e) {
            // ok
        }
        try {
            testCompile("error/CannotAssign2");
            fail("CannotAssign2");
        } catch (SemanticException e) {
            // ok
        }
    }

    @Test
    public void testIncompatibleOperands() throws Exception {
        try {
            testCompile("error/IncompatibleOperands1");
            fail("IncompatibleOperands1");
        } catch (SemanticException e) {
            // ok
        }
        try {
            testCompile("error/IncompatibleOperands2");
            fail("IncompatibleOperands2");
        } catch (SemanticException e) {
            // ok
        }
    }

    @Test
    public void testIncompatibleTypes() throws Exception {
        try {
            testCompile("error/IncompatibleTypes");
            fail("IncompatibleTypes");
        } catch (SemanticException e) {
            // ok
        }
    }

    @Test
    public void testIntegerVariableExpected() throws Exception {
        try {
            testCompile("error/IntegerVariableExpected");
            fail("IntegerVariableExpected");
        } catch (SemanticException e) {
            // ok
        }
    }
}
