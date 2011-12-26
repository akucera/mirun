/**
 * Prekladac jazyka JPShybrid do Java assembleru.
 * Autor: Adam Kucera, kucerad2@fel.cvut.cz
 * vyuzit kod prekladace MiniPascal, Zdenek Tronicek, tronicek@fel.cvut.cz
 */
package compiler;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bytecode.InstructionCompiler;

import tree.Context;
import tree.MethodDeclarationTree.ReturnType;
import tree.MethodTab;
import tree.ProgramTree;
import tree.Type;

/**
 * Prekladac.
 */
public class Compiler {

	private MethodTab staticMethodsTable;
	
	static {
		Locale.setDefault(Locale.US);
	}
	/**
	 * Pouzit optimalizator?
	 */
	//private boolean optimize;

	public Compiler() {
		this.staticMethodsTable = createBuiltInMethodsTable();
	}

	/**
	 * Prelozi soubor source. Vystup zapise do souboru dest.
	 */
	public void compile(String source, String dest) throws IOException {
		Lexer lex = new Lexer(new FileReader(source));
		// create parser with lexer and builtIn methods table
		Parser p = new Parser(lex, staticMethodsTable);
		ProgramTree t = p.parse();
		// SemanticAnalyzer sa = new SemanticAnalyzer();
		// t.accept(sa);
		// PrintWriter out = new PrintWriter(new FileWriter(dest));
		// PrintWriter out = new PrintWriter(System.out);

		// verze s prekladem do bytecode
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		Context ctx = new ContextImpl(out);
		try {
			t.generate(ctx);
		} finally {
			out.close();
		}

		// System.out.println(sw.toString());

		// zapis instrukci to souboru dest.instr
		PrintWriter pw = new PrintWriter(new FileWriter(dest+".instr"));
		pw.write(sw.toString());
		pw.close();
		// docasne vypis instrukce na konzoli
		//System.out.println(sw.toString());

		// vytvor compilator bytecodu z instrukci
		InstructionCompiler compiler = new InstructionCompiler(sw.toString(), staticMethodsTable);
		
		// zapis bytecode do souboru dest
		FileOutputStream fos = new FileOutputStream(dest);
		fos.write(compiler.generate());
		fos.close();

		System.out.println("Bytecode successfully built. Output file name: \""+dest+"\"");
	}

	// TODO IOC
	/**
	 * Vytvori tabulku statickych metod
	 * @return
	 */
	public MethodTab createBuiltInMethodsTable() {
		MethodTab builtInMethods = new MethodTab();
		List<Type> paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.ANY);
		builtInMethods.insert("print", 0, ReturnType.VOID, paramTypes);

		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.ANY);
		builtInMethods.insert("println", 1, ReturnType.VOID, paramTypes);

		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.STRINGVAR);
		builtInMethods.insert("readfileint", 10, ReturnType.INTVAR, paramTypes);

		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.STRINGVAR);
		builtInMethods
				.insert("readfileintarr", 11, ReturnType.ARRAYVAR, paramTypes);

		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.STRINGVAR);
		paramTypes.add(Type.ANY);
		builtInMethods
				.insert("writetofile", 20, ReturnType.VOID, paramTypes);
		
		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.STRINGVAR);
		paramTypes.add(Type.ANY);
		builtInMethods
				.insert("appendtofile", 21, ReturnType.VOID, paramTypes);
		
		paramTypes = new ArrayList<Type>();
		paramTypes.add(Type.ARRAYVAR);
		builtInMethods
				.insert("length", 100, ReturnType.INTVAR, paramTypes);
		
		return builtInMethods;
	}
}
