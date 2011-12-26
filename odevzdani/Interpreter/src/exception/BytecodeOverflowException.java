package exception;

public class BytecodeOverflowException extends Exception {
	
	private static final long serialVersionUID = 2650430838925453882L;

	public BytecodeOverflowException() {
		super();
	}
	
	public BytecodeOverflowException(String message) {
		super(message);
	}

}
