package exception;

public class StackOverflowException extends Exception {

	private static final long serialVersionUID = -5482989943162237218L;

	public StackOverflowException() {
		super();
	}
	
	public StackOverflowException(String message) {
		super(message);
	}

	
}
