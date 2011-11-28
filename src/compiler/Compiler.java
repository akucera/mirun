/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import tree.Context;
import tree.ProgramTree;

/**
 * Prekladac.
 */
public class Compiler {

    static {
        Locale.setDefault(Locale.US);
    }
    /**
     * Pouzit optimalizator?
     */
    private boolean optimize;

    public Compiler() {
    }

    /**
     * Prelozi soubor source. Vystup zapise do souboru dest.
     */
    public void compile(String source, String dest) throws IOException {
        Lexer lex = new Lexer(new FileReader(source));
        Parser p = new Parser(lex);
        ProgramTree t = p.parse();
        SemanticAnalyzer sa = new SemanticAnalyzer();
        t.accept(sa);
        PrintWriter out = new PrintWriter(new FileWriter(dest));
        Context ctx = new ContextImpl(out);
        try {
            t.generate(ctx);
        } finally {
            out.close();
        }
    }
}
