package interpreter;

import java.io.PrintStream;

public class OutputContext {

	/**
     * Vystupni stream.
     */
    private PrintStream out;
	
	public OutputContext(PrintStream out) {
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
	
}
