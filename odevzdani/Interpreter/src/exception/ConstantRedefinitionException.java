package exception;

public class ConstantRedefinitionException extends Exception {

	private static final long serialVersionUID = -962578630632701372L;

	public ConstantRedefinitionException() {
		super();
	}
	
	public ConstantRedefinitionException(String message) {
		super(message);
	}
	
}
