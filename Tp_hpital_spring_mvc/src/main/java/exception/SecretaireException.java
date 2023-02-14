package exception;

public class SecretaireException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SecretaireException() {
		
	}
	
	public SecretaireException(String message) {
		super(message);
	}
}
