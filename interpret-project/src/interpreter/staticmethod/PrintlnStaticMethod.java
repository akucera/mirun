package interpreter.staticmethod;

import exception.EmptyStackPopException;
import interpreter.Bytecode;
import interpreter.Environment;
import interpreter.OutputContext;
import interpreter.Stack;

public class PrintlnStaticMethod extends StaticMethod {
	
	private OutputContext ctx;

	public PrintlnStaticMethod(String name, OutputContext ctx, Bytecode bc, Stack s,
			Environment env) {
		super(name, bc, s, env);
		this.ctx = ctx;
	}

	@Override
	public void perform() throws EmptyStackPopException {
		Object o = s.pop();

		StringBuilder sb = new StringBuilder();
		String output = "";
		// env.printMemory();

		if(o == null) {
			output = "(NULL)";
		}
		else if (o instanceof Object[]) {
			Object[] pole = (Object[])o;
			sb.append("[");
			for (int i = 0; i < pole.length; i++) {
				if(i != 0) sb.append(", ");
				sb.append(pole[i].toString());
			}
			sb.append("]");
			output = sb.toString();
		}
		else {
			output = o.toString();
		}

		//output = o.toString();

		//System.out.println(output);
		ctx.println(output);
	}

}
