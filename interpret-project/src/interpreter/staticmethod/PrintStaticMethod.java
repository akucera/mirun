package interpreter.staticmethod;

import java.util.Arrays;

import exception.EmptyStackPopException;
import interpreter.Bytecode;
import interpreter.Environment;
import interpreter.Stack;

/**
 * Tiskne znaky z vrcholu zasobniku do vystupniho streamu (bez odreadkovani)
 * @author lukaskukacka
 *
 */
public class PrintStaticMethod extends StaticMethod {

	public PrintStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException {
		Object o = s.pop();
		
		String output = "";
		
		//env.printMemory();
		
		if(o instanceof Object[]) {
			output = Arrays.toString((Object[])o);
		}
		
		output = o.toString();
		
		System.out.print(output);
	}

}
