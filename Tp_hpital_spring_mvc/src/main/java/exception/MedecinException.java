package exception;

public class MedecinException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public MedecinException() {
		
	}
	
	public MedecinException(String message) {
		super(message);
	}
}
