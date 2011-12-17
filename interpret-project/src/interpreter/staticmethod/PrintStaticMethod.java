package interpreter.staticmethod;

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
		
		System.out.print(o.toString());
	}

}
