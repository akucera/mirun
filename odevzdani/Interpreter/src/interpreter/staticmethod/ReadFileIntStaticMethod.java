package interpreter.staticmethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
public class ReadFileIntStaticMethod extends StaticMethod {

	public ReadFileIntStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException, StackOverflowException, ProgramExecutionException {
		String filename = (String)s.pop();
		
		File f = new File(filename);
		if(!f.exists()) {
			Util.debugMsg("  File \""+filename+"\" does not exists.");
			throw new ProgramExecutionException("File \""+filename+"\" not found in calling function \""+getName()+"\".");
		}
		
		
		Scanner scanner;
		try {
			Integer result;
			scanner = new Scanner(f);
			if(scanner.hasNextInt()) {
				result = (Integer)scanner.nextInt();
				s.push(result);
			} else {
				Util.debugMsg("  File \""+filename+"\" does not contain any Integer.");
				throw new ProgramExecutionException("Bad input file (\""+filename+"\") provided to function \""+getName()+"\". No integer found");
			}
		} catch (FileNotFoundException e) {
			Util.debugMsg("  File \""+filename+"\" does not found.");
			throw new ProgramExecutionException("File \""+filename+"\" not found in calling function \""+getName()+"\".");
		}
	}

}
