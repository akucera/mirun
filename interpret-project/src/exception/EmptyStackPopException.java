package exception;

public class EmptyStackPopException extends Exception {
	

	private static final long serialVersionUID = 1254905047547632000L;

	public EmptyStackPopException() {
		super();
	}

	public EmptyStackPopException(String message) {
		super(message);
	}
	
}
