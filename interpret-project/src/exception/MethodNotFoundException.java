package exception;

public class MethodNotFoundException extends Exception {

	private static final long serialVersionUID = -962578630632701372L;

	public MethodNotFoundException() {
		super();
	}
	
	public MethodNotFoundException(String message) {
		super(message);
	}
	
}
