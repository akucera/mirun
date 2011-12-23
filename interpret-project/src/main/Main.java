package main;

import interpreter.Bytecode;
import interpreter.Interpreter;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	
	public static final boolean DEBUG = false; 
	
	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.err.println("Interpreter error:\nBad input. Specify bytecode file as first parameter. Example:\n\tjava -jar RUN-Interpreter.jar program.btc [0-10 program arguments]");
		}
		
		File f = new File(args[0]);	// input binary file
		
		String[] programArgs = new String[args.length-1]; 
		System.arraycopy(args, 1, programArgs, 0, programArgs.length);
		
		//System.out.println("Args: "+Arrays.toString(programArgs));
		
		Bytecode bc = new Bytecode(f);
		Interpreter interpreter = new Interpreter(bc, programArgs);
		interpreter.run();
		
		
	}

}
