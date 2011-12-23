package interpreter.staticmethod;

import exception.EmptyStackPopException;
import exception.ProgramExecutionException;
import exception.StackOverflowException;
import interpreter.Bytecode;
import interpreter.Environment;
import interpreter.Stack;

public class LengthStaticMethod extends StaticMethod {

	public LengthStaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		super(name, bc, s, env);
	}

	@Override
	public void perform() throws EmptyStackPopException,
			ProgramExecutionException, StackOverflowException {
		Object o = s.pop();

		if (o.getClass() != Object[].class || o.getClass() != Integer[].class)
			throw new ProgramExecutionException(
					"Can't get length of non-array object");

		s.push(((Object[]) o).length);
		
	}

}
