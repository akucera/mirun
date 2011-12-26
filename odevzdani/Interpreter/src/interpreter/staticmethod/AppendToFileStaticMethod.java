package interpreter.staticmethod;

import java.io.File;
import java.io.IOException;

import utils.FileUtil;
import utils.Util;

import exception.EmptyStackPopException;
import exception.ProgramExecutionException;
import exception.StackOverflowException;
import interpreter.Bytecode;
import interpreter.Environment;
import interpreter.Stack;

/**
 * Funkce, ktera na zasobnik vlozi prvni Integer ze souboru. Nazev souboru je na vrcholu zasobniku.
 * 
 * Pokud se neco nepodari (napr soubor neexisuje, na zasobniku bude MAX_INT
 * @author lukaskukacka
 *
 */
public class AppendToFileStaticMethod extends StaticMethod {

	public AppendToFileStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException, StackOverflowException, ProgramExecutionException {
		String filename = (String)s.pop();
		Object o = s.pop();
		
		File f = new File(filename);
		/*
		if(!f.exists()) {
			Util.debugMsg("  File \""+filename+"\" does not exists.");
			throw new ProgramExecutionException("File \""+filename+"\" not found in calling function \""+getName()+"\".");
		}*/
		
		try {
			FileUtil.writeTextToFile(Util.toString(o, " "), f, true);
		} catch (IOException e1) {
			Util.debugMsg("  IOException in static method \""+getName()+"\"");
			throw new ProgramExecutionException("IOException in static method \""+getName()+"\" ("+e1.getLocalizedMessage()+")");
		}
		
		Util.debugMsg("  Object ("+o.toString()+") writen to file \""+filename+"\"");
		
	}

}
