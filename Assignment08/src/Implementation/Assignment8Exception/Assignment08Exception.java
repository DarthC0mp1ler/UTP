package Implementation.Assignment8Exception;

public class Assignment08Exception extends Exception {

	private static final long serialVersionUID = -6901018906165154875L;

	public Assignment08Exception(String message, Throwable cause) {
		super(message, cause);
	}
	public Assignment08Exception(Exception cause) {
		super( cause);
	}
}