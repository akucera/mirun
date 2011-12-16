package main;

import interpreter.Bytecode;
import interpreter.Interpreter;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	
	public static final boolean DEBUG = true; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length < 1) {
			System.err.println("Interpreter error:\nBad input. Specify bytecode file as first parameter. Example:\n\tjava -jar RUN-Interpreter.jar program.btc");
		}
		
		File f = new File(args[0]);	// input binary file
		
		Bytecode bc = new Bytecode(f);
		Interpreter interpreter = new Interpreter(bc);
		interpreter.run();
		
		
	}

}
