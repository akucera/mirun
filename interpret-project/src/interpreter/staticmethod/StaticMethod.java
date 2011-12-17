package interpreter.staticmethod;

import exception.EmptyStackPopException;
import exception.ProgramExecutionException;
import exception.StackOverflowException;
import interpreter.Stack;

import interpreter.Bytecode;
import interpreter.Environment;

public abstract class StaticMethod {

	protected String name;
	protected Bytecode bc;
	protected Stack s;
	protected Environment env;
	
	public StaticMethod(String name, Bytecode bc, Stack s, Environment env) {
		this.name = name;
		this.bc = bc;
		this.s = s;
		this.env = env;
	}
	
	public abstract void perform() throws EmptyStackPopException, StackOverflowException, ProgramExecutionException;

	public String getName() {
		return name;
	}
	
}
