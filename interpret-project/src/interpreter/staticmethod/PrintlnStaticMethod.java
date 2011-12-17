package interpreter.staticmethod;

import exception.EmptyStackPopException;
import interpreter.Bytecode;
import interpreter.Environment;
import interpreter.Stack;

public class PrintlnStaticMethod extends StaticMethod {

	public PrintlnStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException {
		Object o = s.pop();
		
		System.out.println(o.toString());
	}

}
