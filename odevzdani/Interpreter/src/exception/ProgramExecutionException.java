package exception;

public class ProgramExecutionException extends Exception {

	private static final long serialVersionUID = -962578630632701372L;

	public ProgramExecutionException() {
		super();
	}
	
	public ProgramExecutionException(String message) {
		super(message);
	}
	
}
