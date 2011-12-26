package exception;

public class InvalidArgumentsException extends Exception {

	private static final long serialVersionUID = -962578630632701372L;

	public InvalidArgumentsException() {
		super();
	}
	
	public InvalidArgumentsException(String message) {
		super(message);
	}
	
}
