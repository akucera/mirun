/*
 * Prekladac jazyka MiniPascal do Java assembleru.
 * Autor: Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.IOException;
import org.junit.Test;

/**
 * Test semantiky.
 */
public class SemanticTest {

    private Compiler compiler = new Compiler();

    /**
     * Prelozi soubor.
     */
    private void compile(String s) throws IOException {
        String in = "test/semantic/input/" + s + ".source";
        String out = "test/semantic/output/" + s + ".j";
        compiler.compile(in, out);
    }

    /**
     * Prelozi soubory. Dalsi kontrolu je potreba provest rucne.
     */
    @Test
    public void testSemantic() throws IOException {
        compile("FactorialMethod");
        compile("Ifs");
        compile("Operators");
        compile("Pi");
        compile("Squares");
    }
}
