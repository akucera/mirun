package exception;

public class MethodRedefinitionException extends Exception {

	private static final long serialVersionUID = -962578630632701372L;

	public MethodRedefinitionException() {
		super();
	}
	
	public MethodRedefinitionException(String message) {
		super(message);
	}
	
}
