package interpreter.staticmethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
public class ReadFileIntArrStaticMethod extends StaticMethod {

	public ReadFileIntArrStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException, StackOverflowException, ProgramExecutionException {
		Object[] target = (Object[])s.pop();
		String filename = (String)s.pop();
		
		File f = new File(filename);
		if(!f.exists()) {
			Util.debugMsg("  File \""+filename+"\" does not exists.");
			throw new ProgramExecutionException("File \""+filename+"\" not found in calling function \""+getName()+"\".");
		}
		
		//Integer addr = (Integer)s.pop();
		
		
		byte[] bytes = new byte[(int) f.length()];
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			fis.read(bytes);
			fis.close();
			
			String[] valueStr = new String(bytes).trim().split("\\s+");
			
			//Integer[] arr = new Integer[valueStr.length];
			
			for (int i = 0; i < target.length; i++) 
			    target[i] = new Integer(Integer.parseInt(valueStr[i]));
			
			//target = arr;
			//env.printMemory();
			//env.setVariable(addr, arr);
		} catch (FileNotFoundException e) {
			Util.debugMsg("  File \""+filename+"\" does not exists.");
			throw new ProgramExecutionException("File \""+filename+"\" not found in calling function \""+getName()+"\".");
		} catch (IOException e) {
			Util.debugMsg("  File \""+filename+"\" does not exists.");
			throw new ProgramExecutionException("IO problem in calling function \""+getName()+"\":\n"+e.getLocalizedMessage());
		}
		
		
	}

}
