package blog.panditmandar.code.restap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author MandarPandit
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HTTPNotFoundException extends Exception {

	private static final long serialVersionUID = 7929086114499212397L;

	public HTTPNotFoundException(String message) {
		super(message);
	}

}
