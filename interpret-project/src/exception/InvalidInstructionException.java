package exception;

public class InvalidInstructionException extends Exception {

	private static final long serialVersionUID = -5377116945763529325L;

	public InvalidInstructionException() {
		super();
	}

	public InvalidInstructionException(String message) {
		super(message);
	}
	
}
