/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import tree.Context;

/**
 * Kontext pouzity pri generovani kodu.
 */
public class ContextImpl implements Context {

    /**
     * Dalsi volne navesti.
     */
    private int nextLabel = 1;

    /**
     * Vystupni stream.
     */
    private PrintWriter out;

    /**
     * Atributy.
     */
    private Map<String, Object> attrs = new HashMap<String, Object>();

    public ContextImpl(PrintWriter out) {
        this.out = out;
    }

    public void print(String s) {
        out.print(s);
    }

    public void println(String s) {
        out.println(s);
    }

    public void printf(String f, Object... o) {
        out.printf(f, o);
    }

    public String nextLabel() {
        String s = "lab" + nextLabel;
        nextLabel++;
        return s;
    }

    public void addAttr(String n, Object v) {
        attrs.put(n, v);
    }

    public Object getAttr(String n) {
        return attrs.get(n);
    }

    public void removeAttr(String n) {
        attrs.remove(n);
    }
}
