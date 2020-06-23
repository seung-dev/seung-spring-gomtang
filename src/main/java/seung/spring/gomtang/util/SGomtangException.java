package seung.spring.gomtang.util;

public class SGomtangException extends Exception {

	private static final long serialVersionUID = 667955387365435519L;
	
	public SGomtangException(Throwable e) {
		super(e);
	}
	
	public SGomtangException(String message) {
		super(message);
	}
	
	public SGomtangException(String message, Throwable e) {
		super(message, e);
	}
	
}
